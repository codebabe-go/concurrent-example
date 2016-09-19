package example.selling.java.impl;

import example.selling.java.*;

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
        return pool.actuallySize() - pool.cachedSize() > 0;
    }

    public Ticket allocateTickets(Request request) {
        return request instanceof Customer ? bind(request) : null;
    }

    // 完成打印后将这个ticket取出, 这样符合实际情况
    public void printTicket(Ticket ticket) {
        if (ticket == null) {
            return;
        }
        printing();
        System.out.println(String.format("ticket name = %s is printed", ticket.getName()));

    }

    private void printing() {
        try {
            Thread.sleep(500);
            // TODO: 16/9/18 可以增加一些处理 这里做简单的线程阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定请求
     * @param request
     * @return
     */
    private Ticket bind(Request request) {
        // 名字统一规定为发送请求的时候
        Ticket ticket = new Ticket(request, request.getPostTime().toString());
        return ticket;
    }
}
