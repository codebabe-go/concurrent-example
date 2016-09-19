package example.selling.java.impl;

import com.sun.xml.internal.bind.v2.TODO;
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
        // TODO: 16/9/19 是否另起一个线程来处理阻塞情况有待商榷, 可以用递归来简化代码(这样代码可读性不高)
        Response response = agent.dispatchRequest(this);
        if (response != null && Response.ReturnCode.SUCCESS == response.getCode()) {
            super.setType(EventType.LOCK.getType());
            agent.dispatchRequest(this);
            if (response != null && Response.ReturnCode.SUCCESS == response.getCode()) {
                super.setType(EventType.PAY.getType());
                agent.dispatchRequest(this);
                if (response != null && Response.ReturnCode.SUCCESS == response.getCode()) {
                    
                }
            }
        }
    }
}