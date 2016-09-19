package example.selling.java.impl;

import example.selling.java.Request;
import example.selling.java.RequestPorxy;
import example.selling.java.Response;

/**
 * author: code.babe
 * date: 2016-09-17 13:46
 */
public class TicketAgent implements RequestPorxy {

    private Cinema cinema;

    public TicketAgent(Cinema cinema) {
        this.cinema = cinema;
    }

    public Response dispatchRequest(Request request) {
        if (null == request) {
            return Response.FAILED().wrapper("request is null");
        }

        if (!(request instanceof Customer)) {
            return Response.FAILED().wrapper("request is invalidate");
        }

        Customer customer = (Customer) request;
        int type = customer.getType();

        if (type > Request.EventType.PAY.getType()) {
            return Response.FAILED().wrapper("The flow is over, you've already ticket an ticket");
        }

        if (Request.EventType.EMPTY.getType() == type) {
            // TODO: 16/9/19 空请求不做操作
        } else if (Request.EventType.LOCK.getType() == type) {
            
        } else if (Request.EventType.PAY.getType() == type) {

        } else {
            return Response.FAILED().wrapper("no match type");
        }

        return Response.SUCCESS();
    }
}
