package com.iflytek.message.api;

import org.springframework.web.bind.annotation.*;

/**
 * @author llchen12
 * @date 2018/5/9
 */
@RequestMapping(value = "/tMsg")
public interface MessageClient {

    /**
     * 发送prepare消息
     * @param message 事务消息
     * @return 是否成功
     * @throws MessageBizException 消息异常
     */
    @RequestMapping(value = "/prepare",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    boolean sendPrepareMessage(@RequestBody TransactionMessage message) throws MessageBizException;

    /**
     * 确认prepare消息
     * @param msgId prepare消息Id
     * @return 否成功
     * @throws MessageBizException 消息异常
     */
    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    boolean confirmMessage(@RequestParam(value = "msgId") String msgId) throws MessageBizException;

    /**
     * 直接发送消息
     * @param message 消息
     * @throws MessageBizException 消息异常
     */
    @PostMapping(value = "/direct",produces = "application/json",consumes = "application/json")
    void sendDirectMessage(@RequestBody TransactionMessage message) throws MessageBizException;

    /**
     * 将消息标记为死亡消息.
     * @param msgId 消息ID
     * @throws MessageBizException 消息异常
     */
    @PostMapping(value = "/mark")
    void markDeadMessage(@RequestParam(value = "msgId") String msgId) throws MessageBizException;

}
