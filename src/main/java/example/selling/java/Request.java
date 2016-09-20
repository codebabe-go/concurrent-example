package example.selling.java;

import java.sql.Timestamp;

/**
 * author: code.babe
 * date: 2016-09-14 13:54
 */
public class Request implements Runnable {

    public Request(int type) {
        this.postTime = new Timestamp(System.currentTimeMillis());
        this.type = type;
    }

    public enum EventType {
        // 空请求 测试用
        EMPTY(-1, "EMPTY"),
        // 锁定
        LOCK(0, "LOCK"),
        // 支付
        PAY(1, "PAY")
        ;

        private String name;
        private int type;

        EventType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public static String name(int type) {
            for (EventType e : EventType.values()) {
                if (type == e.getType()) {
                    return e.getName();
                }
            }
            return "";
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }
    }

    private Timestamp postTime;

    private int type;

    @Override
    public void run() {
        System.out.println(String.format("I was posted in %s, my type is %s", postTime, EventType.name(type)));
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public int getType() {
        return type;
    }

    public Request setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Request{" +
                "postTime=" + postTime +
                ", type=" + type +
                '}';
    }
}
