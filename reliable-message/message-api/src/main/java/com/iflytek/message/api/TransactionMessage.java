package com.iflytek.message.api;

import java.io.Serializable;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public class TransactionMessage extends Message implements Serializable {

    private static final long serialVersionUID = 1757377457814546156L;

    private Integer status;
    private String consumerQueue;

    public TransactionMessage(String id,String body,String consumerQueue){
        super(id,body);
        this.consumerQueue=consumerQueue;
        this.status=MessageStatus.SENDING.getStatus();
    }

    public Message ToMsg(){
        return new Message(this.getId(),this.getBody());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String queue) {
        this.consumerQueue = queue;
    }

    @Override
    public String toString() {
        return "TransactionMessage{" +
                "id='" + getId() + '\'' +
                ", body='" + getBody() + '\'' +
                ", status=" + status +
                ", consumerQueue='" + consumerQueue + '\'' +
                '}';
    }
}
