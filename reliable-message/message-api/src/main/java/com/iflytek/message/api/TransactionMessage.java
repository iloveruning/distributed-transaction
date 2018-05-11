package com.iflytek.message.api;



import java.io.Serializable;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public class TransactionMessage extends Message implements Serializable {

    private static final long serialVersionUID = 1757377457814546156L;


    private Integer status;

    private Integer producerRetry;

    private Integer consumerRetry;

    private String pqueue;

    private String cqueue;


    public TransactionMessage(){
        super();
    }
    public TransactionMessage(String id,Object body,String producerQueue,String consumerQueue){
        this(id,body,producerQueue,consumerQueue,1,3);
    }

    public TransactionMessage(String id,Object body,String producerQueue,String consumerQueue,Integer producerRetry,Integer consumerRetry){
       this(id, body, producerQueue, consumerQueue,producerRetry,consumerRetry,MessageStatus.DEFAULT.getStatus());
    }

    public TransactionMessage(String id,Object body,String producerQueue,String consumerQueue,Integer producerRetry,Integer consumerRetry,Integer status){
        super(id,body);
        this.pqueue=producerQueue;
        this.cqueue=consumerQueue;
        this.producerRetry=producerRetry;
        this.consumerRetry=consumerRetry;
        this.status=status;
    }

    public Integer getProducerRetry() {
        return producerRetry;
    }

    public void setProducerRetry(Integer producerRetry) {
        this.producerRetry = producerRetry;
    }

    public Integer getConsumerRetry() {
        return consumerRetry;
    }

    public void setConsumerRetry(Integer consumerRetry) {
        this.consumerRetry = consumerRetry;
    }

    public String getPqueue() {
        return pqueue;
    }

    public void setPqueue(String pqueue) {
        this.pqueue = pqueue;
    }

    public String getCqueue() {
        return cqueue;
    }

    public void setCqueue(String cqueue) {
        this.cqueue = cqueue;
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





    @Override
    public String toString() {
        return "TransactionMessage{" +
                "id='" + getId() + '\'' +
                ", body='" + getBody() + '\'' +
                ", status=" + status +
                ", producerRetry=" + producerRetry +
                ", consumerRetry=" + consumerRetry +
                ", producerQueue='" + pqueue + '\'' +
                ", consumerQueue='" + cqueue + '\'' +
                '}';
    }
}
