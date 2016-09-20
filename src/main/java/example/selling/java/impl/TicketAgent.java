package example.selling.java.impl;

import example.selling.java.Request;
import example.selling.java.RequestPorxy;
import example.selling.java.Response;
import example.selling.java.Ticket;

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

        // 每次都是完整的从客户发来的请求, 所以不赞成在agent端模拟长连接
        Ticket ticket = null;
        if (Request.EventType.EMPTY.getType() == type) {
            // 判断是否还有票源
            if (!cinema.isEnough()) {
                return Response.FAILED().wrapper(String.format("there is not enough tickets to sell, post time = %s", request.getPostTime().toString()));
            }
        } else if (Request.EventType.LOCK.getType() == type) {
            ticket = cinema.allocateTickets(request);
            if (null == ticket) {
                return Response.FAILED().wrapper(String.format("cannot get this ticket, interrupt the long connection, post time = %s", request.getPostTime().toString()));
            }
        } else if (Request.EventType.PAY.getType() == type) {
            Response response = cinema.printTicket(ticket);
            // 通常不会失败, 这里只是完整逻辑闭环
            if (Response.ReturnCode.FAILED == response.getCode()) {
                return Response.FAILED().wrapper("pay failed");
            }
        } else {
            return Response.FAILED().wrapper("no match type");
        }

        return Response.SUCCESS().wrapper(String.format("solve success %s", request.toString()));
    }
}
