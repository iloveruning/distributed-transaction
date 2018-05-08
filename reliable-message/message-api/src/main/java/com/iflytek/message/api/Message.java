package com.iflytek.message.api;

import java.io.Serializable;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public class Message implements Serializable {

    private String id;
    private String body;

    public Message(String id, String body) {
        this.id = id;
        this.body = body;
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
}
