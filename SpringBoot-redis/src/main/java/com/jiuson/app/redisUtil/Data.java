package com.jiuson.app.redisUtil;

/**
 * 存入redis缓存的对象
 */
public class Data {

    private int id;
    private String messageKey;
    private String messageValue;

    public Data() {
    }

    public Data(int id, String messageKey, String messageValue) {
        this.id = id;
        this.messageKey = messageKey;
        this.messageValue = messageValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String messageValue) {
        this.messageValue = messageValue;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", messageKey='" + messageKey + '\'' +
                ", messageValue='" + messageValue + '\'' +
                '}';
    }
}
