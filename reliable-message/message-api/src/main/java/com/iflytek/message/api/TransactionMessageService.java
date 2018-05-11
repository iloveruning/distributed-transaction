package com.iflytek.message.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public interface TransactionMessageService {

    /**
     * 发送prepare消息
     * @param message 事务消息
     * @return 是否成功
     * @throws MessageBizException 消息异常
     */
    boolean sendPrepareMessage(TransactionMessage message) throws MessageBizException;

    /**
     * 确认prepare消息
     * @param msgId prepare消息Id
     * @return 否成功
     * @throws MessageBizException 消息异常
     */
   boolean confirmMessage(String msgId) throws MessageBizException;


    /**
     * 发送生产者回调消息
     * @param message 消息
     * @throws MessageBizException 消息异常
     */
    void sendProducerCallbackMessage(TransactionMessage message) throws MessageBizException;

    /**
     * 发送消费者回调消息
     * @param message 消息
     * @throws MessageBizException 消息异常
     */
    void sendConsumerCallbackMessage(TransactionMessage message) throws MessageBizException;
    /**
     * 直接发送消息
     * @param message 消息
     * @throws MessageBizException 消息异常
     */
    void sendDirectMessage(TransactionMessage message) throws MessageBizException;

    /**
     * 将消息标记为死亡消息.
     * @param msgId 消息ID
     * @throws MessageBizException 消息异常
     */
    void markDeadMessage(String msgId) throws MessageBizException;

    /**
     * 根据消息ID获取消息
     * @param msgId 消息ID
     * @return 消息
     */
    TransactionMessage getMessageById(String msgId);


    /**
     * 根据消息ID删除消息
     * @param msgId 消息ID
     * @return 是否成功
     */
    boolean deleteMessageById(String msgId);


}
