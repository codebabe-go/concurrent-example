package example.selling.java.impl;

import example.selling.java.Request;
import example.selling.java.RequestPorxy;
import example.selling.java.Response;

/**
 * author: code.babe
 * date: 2016-09-17 13:46
 */
public class TicketAgent implements RequestPorxy {

    public Response dispatchRequest(Request request) {
        if (null == request) {
            return Response.FAILED().wrapper("request is null");
        }

        if (!(request instanceof Customer)) {
            return Response.FAILED().wrapper("request is invalidate");
        }

        Customer customer = (Customer) request;
        int type = customer.getType();
        if (Request.EventType.EMPTY.getType() == type) {

        } else if (Request.EventType.LOCK.getType() == type) {

        } else if (Request.EventType.PAY.getType() == type) {

        } else {
            return Response.FAILED().wrapper("no match type");
        }

        return Response.SUCCESS();
    }
}
