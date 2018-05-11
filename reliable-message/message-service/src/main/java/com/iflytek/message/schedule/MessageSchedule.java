package com.iflytek.message.schedule;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.iflytek.message.api.MessageStatus;
import com.iflytek.message.api.TransactionMessageService;
import com.iflytek.message.dao.TMsgMapper;
import com.iflytek.message.entity.TMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息回调确认定时任务
 *
 * @author llchen12
 * @date 2018/5/9
 */
@Slf4j
@Component
public class MessageSchedule {

    private TransactionMessageService transactionMessageService;

    private TMsgMapper tMsgMapper;

    @Autowired
    protected MessageSchedule(TransactionMessageService transactionMessageService, TMsgMapper tMsgMapper){
        this.tMsgMapper=tMsgMapper;
        this.transactionMessageService=transactionMessageService;
    }

    @Scheduled(fixedRate = 60000,initialDelay = 30000)
    public void handlePrepareMsg(){
        List<TMsg> msgList = tMsgMapper.selectList(new EntityWrapper<TMsg>()
                .eq("status", MessageStatus.PREPARE.getStatus()));

        if (msgList==null){
            return;
        }
        System.out.println("开始处理[prepare]状态的消息,总条数["+msgList.size()+"]");

        log.debug("开始处理[prepare]状态的消息,总条数[" + msgList.size() + "]");
        for (TMsg msg:msgList){

            Integer tryCount = msg.getProducerTry()+1;

            if (tryCount>msg.getProducerRetry()){
                msg.setStatus(MessageStatus.DEAD.getStatus());
                msg.setReason("[prepare]消息"+msg.getMsgId()+"---->回调生产者次数超过上限");
                tMsgMapper.updateById(msg);
                log.warn("[prepare]消息{} 重试次数超过上限！",msg.getMsgId());
                return;
            }
            tMsgMapper.updateProducerTry(msg.getId(),tryCount);
            transactionMessageService.sendProducerCallbackMessage(msg.toTranMsg());
        }
    }


    @Scheduled(fixedRate = 60000,initialDelay = 60000)
    public void handleSendingMsg(){
        List<TMsg> msgList = tMsgMapper.selectList(new EntityWrapper<TMsg>()
                .eq("status", MessageStatus.SENDING.getStatus()));
        if (msgList==null){
            return;
        }
        System.out.println("开始处理[sending]状态的消息,总条数["+msgList.size()+"]");
        log.debug("开始处理[sending]状态的消息,总条数[{}]",msgList.size());
        for (TMsg msg:msgList){

            Integer tryCount = msg.getConsumerTry()+1;

            if (tryCount>msg.getConsumerRetry()){
                msg.setStatus(MessageStatus.DEAD.getStatus());
                msg.setReason("[sending]消息"+msg.getMsgId()+"---->回调消费者次数超过上限");
                tMsgMapper.updateById(msg);
                log.warn("[sending]消息{} 重试次数超过上限！",msg.getMsgId());
                return;
            }

            tMsgMapper.updateConsumerTry(msg.getId(),tryCount);
            transactionMessageService.sendConsumerCallbackMessage(msg.toTranMsg());
        }
    }




}
