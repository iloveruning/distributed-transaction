package com.iflytek.message.api;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public enum MessageStatus {

    DEFAULT(0, "默认状态"),

    PREPARE(1,"待确认状态"),

    SENDING(2,"发送状态"),

    DEAD(3,"死亡状态");


    private Integer status;
    private String desc;

    MessageStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
