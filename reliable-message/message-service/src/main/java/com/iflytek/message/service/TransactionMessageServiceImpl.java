package com.iflytek.message.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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

        TMsg tMsg = new TMsg(message);
        tMsg.setStatus(MessageStatus.PREPARE.getStatus());

        return tMsgMapper.insert(tMsg) == 1;
    }

    @Override
    public boolean confirmMessage(String msgId) throws MessageBizException {

        final TMsg msg = getTMsgByMsgId(msgId);
        if (msg == null) {
            throw new MessageBizException("根据消息id查找的消息为空");
        }
        int status = msg.getStatus();
        if (status == MessageStatus.PREPARE.getStatus()) {
            msg.setStatus(MessageStatus.SENDING.getStatus());
            Integer res = tMsgMapper.updateById(msg);
            if (res == 1) {
                rabbitMq.convertAndSend(msg.getConsumerQueue(), msg.toMsg());
                return true;
            }
        } else if (status == MessageStatus.SENDING.getStatus()) {
            return tMsgMapper.deleteById(msg.getId()) == 1;
        }

        return false;
    }

    @Override
    public void sendProducerCallbackMessage(TransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException("发送的消息为空");
        }

        if (StringUtils.isEmpty(message.getPqueue())) {
            throw new MessageBizException("回调的生产者队列不能为空");
        }

        this.rabbitMq.convertAndSend(message.getPqueue(), message.ToMsg());
    }

    @Override
    public void sendConsumerCallbackMessage(TransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException("发送的消息为空");
        }

        if (StringUtils.isEmpty(message.getCqueue())) {
            throw new MessageBizException("回调的消费者队列不能为空");
        }

        this.rabbitMq.convertAndSend(message.getCqueue(), message.ToMsg());
    }

    @Override
    public void sendDirectMessage(TransactionMessage message) throws MessageBizException {
        checkMessage(message);
        this.rabbitMq.convertAndSend(message.getCqueue(), message.ToMsg());

    }

    @Override
    public void markDeadMessage(String msgId) throws MessageBizException {

        final TransactionMessage msg = getMessageById(msgId);
        if (msg == null) {
            throw new MessageBizException("根据消息id查找的消息为空");
        }

        TMsg tMsg = new TMsg(msg);
        tMsg.setStatus(MessageStatus.DEAD.getStatus());

        tMsgMapper.updateById(tMsg);
    }

    public void markDeadMessage(TMsg tMsg) throws MessageBizException {

        tMsg.setStatus(MessageStatus.DEAD.getStatus());

        tMsgMapper.updateById(tMsg);
    }

    @Override
    public TransactionMessage getMessageById(String msgId) {

        TMsg tMsg = tMsgMapper.findByMsgId(msgId);
        if (tMsg == null) {
            return null;
        }
        return tMsg.toTranMsg();
    }


    private TMsg getTMsgByMsgId(String msgId) {
        return tMsgMapper.findByMsgId(msgId);
    }

    @Override
    public boolean deleteMessageById(String msgId) {
        return tMsgMapper.delete(new EntityWrapper<TMsg>().eq("msg_id", msgId)) == 1;
    }

    private void checkMessage(TransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException("保存的消息为空");
        }

        if (StringUtils.isEmpty(message.getCqueue())) {
            throw new MessageBizException("消息的消费队列不能为空");
        }
    }
}
