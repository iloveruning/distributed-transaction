package com.iflytek.message.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import com.iflytek.message.api.Message;
import com.iflytek.message.api.TransactionMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author llchen12
 * @since 2018-05-08
 */
@Data
@Accessors(chain = true)
public class TMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 消息ID
     */
    private String msgId;
    /**
     * 消息体
     */
    private String body;

    /**
     * 生产者回调次数
     */
    private Integer producerTry;
    /**
     * 消费者回调次数
     */
    private Integer consumerTry;

    /**
     * 消息状态
     */
    private Integer status;

    /**
     * 允许生产者重试次数
     */
    private Integer producerRetry;
    /**
     * 允许消费者重试次数
     */
    private Integer consumerRetry;

    /**
     * 消息生产者队列
     */
    private String producerQueue;
    /**
     * 消费队列（消息目的地）
     */
    private String consumerQueue;
    /**
     * 消息死亡原因
     */
    private String reason;
    /**
     * 消息创建时间
     */
    private Date createTime;


    public TMsg() {
    }

    public TMsg(TransactionMessage message) {
        this.msgId = message.getId();
        this.body = message.getBody();
        this.status = message.getStatus();
        this.producerQueue = message.getPqueue();
        this.consumerQueue = message.getCqueue();
        this.producerRetry = message.getProducerRetry();
        this.consumerRetry = message.getConsumerRetry();
        this.createTime = new Date();
    }


    public Message toMsg() {
        return new Message(this.msgId, this.body);
    }


    public TransactionMessage toTranMsg() {
        return new TransactionMessage(this.msgId, this.body, this.producerQueue, this.consumerQueue, this.producerRetry,this.consumerRetry);
    }


}
