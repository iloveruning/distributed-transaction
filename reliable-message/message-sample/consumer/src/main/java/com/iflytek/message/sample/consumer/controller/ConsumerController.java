package com.iflytek.message.sample.consumer.controller;

import com.iflytek.message.api.Message;
import com.iflytek.message.api.TransactionMessage;

import com.iflytek.message.sample.consumer.client.MsgClient;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author llchen12
 * @date 2018/5/9
 */
@RestController
@RabbitListener(queues = ConsumerController.QUEUE)
@RequestMapping("/consumer")
public class ConsumerController {

    public static final String QUEUE = "cqueue";

    @Autowired
    private MsgClient msgClient;


//    @GetMapping("/test")
//    public String test(){
//
//        String msgId="producer"+Math.random();
//        TransactionMessage message=new TransactionMessage(msgId,"hello","pqueue","cqueue");
//        boolean b = msgClient.sendPrepareMessage(message);
//        if (b){
//            //执行本地事务
//            System.out.println("执行本地事务");
//
//            //事务执行成功后，确认消息，如果确认失败，可以重试多次
//            msgClient.confirmMessage(msgId);
//        }
//
//        return "success";
//    }

    int count = 1;

    /**
     * 该方法要满足幂等性
     *
     * @param msg 接收的消息
     */
    @RabbitHandler
    public void handPrepareCallbackMsg(Message msg) {
        HashMap content = msg.convert(HashMap.class);
        System.out.println("第 "+count+" 次收到回调消息：" + msg);
        //核查本地事务是否成功
        System.out.println("核查本地事务是否成功");
        //if (count++ == 4) {
            //确认消息
            msgClient.confirmMessage(msg.getId());
       // }


    }


}
