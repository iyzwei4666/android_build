package com.example.chen.atguigucode.commom.envetbus.event;

/**
 * Created by chen on 2017/8/15.
 */

public  class MessageEvent {
    public   String name;
    public String password;

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
