package example.selling.java.impl;

import example.selling.java.Request;
import example.selling.java.Response;

/**
 * author: code.babe
 * date: 2016-09-17 13:45
 */
public class Customer extends Request {

    private TicketAgent agent;

    public Customer(int type) {
        super(type);
    }

    public Customer(int type, TicketAgent agent) {
        this(type);
        this.agent = agent;
    }

    @Override
    public void run() {
        if (EventType.EMPTY.getType() == getType()) {
            while (true) { // 构建一个死循环来模拟一个长连接
                Response response = agent.dispatchRequest(this.setType(getType() + 1));
                if (response == null || Response.ReturnCode.SUCCESS != response.getCode()) {
                    return;
                }
            }
        } else {
            return;
        }
    }
}