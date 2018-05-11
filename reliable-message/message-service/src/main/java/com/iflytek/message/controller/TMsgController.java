package com.iflytek.message.controller;


import com.alibaba.fastjson.JSON;
import com.iflytek.message.api.MessageBizException;
import com.iflytek.message.api.MessageClient;
import com.iflytek.message.api.TransactionMessage;
import com.iflytek.message.api.TransactionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author llchen12
 * @since 2018-05-08
 */
@RestController
@RequestMapping("/tMsg")
public class TMsgController  {


    private TransactionMessageService transactionMessageService;

    @Autowired
    protected TMsgController(TransactionMessageService transactionMessageService) {
        this.transactionMessageService = transactionMessageService;
    }

    @RequestMapping(value = "/prepare",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public boolean sendPrepareMessage(@RequestBody TransactionMessage message) throws MessageBizException {
        System.out.println(message);
       // TransactionMessage message1 = JSON.parseObject(message, TransactionMessage.class);
        return transactionMessageService.sendPrepareMessage(message);
    }

    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    public boolean confirmMessage(@RequestParam("msgId") String msgId) throws MessageBizException {
        return transactionMessageService.confirmMessage(msgId);
    }


    @RequestMapping(value = "/direct",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public void sendDirectMessage(@RequestBody TransactionMessage message) throws MessageBizException {
        transactionMessageService.sendDirectMessage(message);
    }

    @PostMapping(value = "/mark")
    public void markDeadMessage(@RequestParam("msgId") String msgId) throws MessageBizException {
        transactionMessageService.markDeadMessage(msgId);
    }
}

