package example.selling.java.impl;

import example.selling.java.*;
import example.selling.java.util.ThreadUtil;

/**
 * author: code.babe
 * date: 2016-09-17 00:55
 * 电影院
 */
public class Cinema implements PrintOffice {

    private Pool pool;

    public Cinema(Pool pool) {
        this.pool = pool;
    }

    public boolean isEnough() {
        // 实际的大小是否大于缓存(开始流程)中的大小即为是否有充足的票源
        System.out.println(String.format("[Cinema.isEnough] actually size = %d, cached size = %d", pool.actuallySize(), pool.cachedSize()));
        return pool.actuallySize() - pool.cachedSize() > 0;
    }

    public Ticket allocateTickets(Request request) {
        Ticket src = (Ticket) pool.lock();
        return request instanceof Customer ? bind(request, src) : null;
    }

    // 完成打印后将这个ticket取出, 这样符合实际情况
    public Response printTicket(Ticket ticket) {
        if (ticket == null) {
            Response.FAILED().wrapper("parameter is null");
        }
        String name = ticket.getName();
        printing(name);
        if(!pool.unlock(ticket)) {
            Response.FAILED().wrapper("unlock failed because of unknown error");
        }
        System.out.println(String.format("[printTicket]ticket name = %s is printed", name));
        return Response.SUCCESS();
    }

    private void printing(String name) {
        // TODO: 16/9/18 可以增加一些处理 这里做简单的线程阻塞
        System.out.println(String.format("[printTicket]ticket is printing, name = %s", name));
        ThreadUtil.sleep(500);
    }

    /**
     * 绑定请求
     * @param sender
     * @return
     */
    private Ticket bind(Request sender, Ticket src) {
        if (src == null || sender == null) {
            return null;
        }
        // 名字统一规定为请求发送的时间
        src = src.builder(sender.getPostTime().toString(), sender);
        System.out.println(String.format("binding, ticket = %s", src.toString()));
        return src;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "pool=" + pool +
                '}';
    }
}
