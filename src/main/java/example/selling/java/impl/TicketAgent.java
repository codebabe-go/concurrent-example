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
            return Response.FAILED().warpper("request is null");
        }

        if (!(request instanceof Customer)) {
            return Response.FAILED().warpper("request is invalidate");
        }

        return Response.SUCCESS();
    }
}
