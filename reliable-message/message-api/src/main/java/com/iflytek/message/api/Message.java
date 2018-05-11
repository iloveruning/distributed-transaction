package com.iflytek.message.api;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1757377434564546156L;


    private String id;
    private String body;

    public Message() {
    }

    public Message(String id, Object body) {
        this.id = id;
        if (body instanceof String) {
            this.body = (String) body;
        } else {
            this.body = JSON.toJSONString(body);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public <T> T convert(Class<T> clazz) {
        return JSON.parseObject(this.body, clazz);
    }
}
