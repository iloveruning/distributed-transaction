package com.iflytek.message.sample.producer.controller;

import com.iflytek.message.api.Message;
import com.iflytek.message.api.TransactionMessage;
import com.iflytek.message.sample.producer.client.MsgClient;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author llchen12
 * @date 2018/5/9
 */
@RestController
@RabbitListener(queues = {ProducerController.QUEUE})
@RequestMapping("/producer")
public class ProducerController {

    public static final String QUEUE="pqueue";

    @Autowired
    private MsgClient msgClient;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    @GetMapping("/test")
    public String test(){

        String msgId="producer"+Math.random();
        Map<String,Object> content=new HashMap<>();
        content.put("key","value");
        TransactionMessage message=new TransactionMessage(msgId,content,"pqueue","cqueue");
        System.out.println(message);
        boolean b = msgClient.sendPrepareMessage(message);
        if (b){
            //执行本地事务
            System.out.println("执行本地事务");

            //事务执行成功后，确认消息，如果确认失败，可以重试多次
           // msgClient.confirmMessage(msgId);
        }

        return "success";
    }


    @RabbitHandler
    public void handPrepareCallbackMsg(Message msg){
        System.out.println("收到回调消息："+msg);
        //核查本地事务是否成功
        System.out.println("核查本地事务是否成功");
        //确认消息
        msgClient.confirmMessage(msg.getId());
    }


}
