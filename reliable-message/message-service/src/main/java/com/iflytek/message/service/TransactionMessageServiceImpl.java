package com.iflytek.message.service;

import com.iflytek.message.api.MessageBizException;
import com.iflytek.message.api.MessageStatus;
import com.iflytek.message.api.TransactionMessage;
import com.iflytek.message.api.TransactionMessageService;
import com.iflytek.message.dao.TMsgMapper;
import com.iflytek.message.entity.TMsg;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@Service
public class TransactionMessageServiceImpl implements TransactionMessageService {


    private RabbitMessagingTemplate rabbitMq;

    private TMsgMapper tMsgMapper;

    @Autowired
    protected TransactionMessageServiceImpl(RabbitMessagingTemplate rabbitMessagingTemplate,
                                            TMsgMapper tMsgMapper) {
        this.rabbitMq = rabbitMessagingTemplate;
        this.tMsgMapper = tMsgMapper;

    }

    @Override
    public boolean sendPrepareMessage(TransactionMessage message) throws MessageBizException {

       checkMessage(message);

        TMsg tMsg=new TMsg(message);
        tMsg.setStatus(MessageStatus.PREPARE.getStatus());

        return tMsgMapper.insert(tMsg)==1;
    }

    @Override
    public boolean confirmMessage(String msgId) throws MessageBizException {

        final TransactionMessage msg = getMessageById(msgId);
        if (msg==null){
            throw new MessageBizException("根据消息id查找的消息为空");
        }
        TMsg tMsg=new TMsg(msg);
        tMsg.setStatus(MessageStatus.CONFIRM.getStatus());
        Integer res=tMsgMapper.updateById(tMsg);

        if (res==1){
            rabbitMq.convertAndSend(tMsg.getQueue(),tMsg.toMsg());
            return true;
        }

        return false;
    }

    @Override
    public void sendDirectMessage(TransactionMessage message) throws MessageBizException {
        checkMessage(message);
        this.rabbitMq.convertAndSend(message.getConsumerQueue(), message.ToMsg());

    }

    @Override
    public void markDeadMessage(String msgId) throws MessageBizException {

        final TransactionMessage msg = getMessageById(msgId);
        if (msg==null){
            throw new MessageBizException( "根据消息id查找的消息为空");
        }

        TMsg tMsg=new TMsg(msg);
        tMsg.setStatus(MessageStatus.DEAD.getStatus());

        tMsgMapper.updateById(tMsg);
    }

    @Override
    public TransactionMessage getMessageById(String msgId) {

        TMsg tMsg = tMsgMapper.selectById(msgId);
        if (tMsg==null){
            return null;
        }
        return tMsg.toTranMsg();
    }

    @Override
    public boolean deleteMessageById(String msgId) {
        return tMsgMapper.deleteById(msgId)==1;
    }

    private void checkMessage(TransactionMessage message) throws MessageBizException{
        if (message == null) {
            throw new MessageBizException("保存的消息为空");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException("消息的消费队列不能为空");
        }
    }
}
