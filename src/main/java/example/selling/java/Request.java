package example.selling.java;

import java.sql.Timestamp;

/**
 * author: code.babe
 * date: 2016-09-14 13:54
 */
public class Request implements Runnable {

    public Request(Timestamp postTime, int type) {
        this.postTime = postTime;
        this.type = type;
    }

    public interface EnventType {
        // 空请求
        int EMPTY = -1;
        // 锁定
        int LOCK = 0;
        // 支付
        int PAY = 1;
    }

    private Timestamp postTime;

    private int type;

    public void run() {
        System.out.println(String.format("I was posted in %s", postTime));
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

    public void setType(int type) {
        this.type = type;
    }
}
