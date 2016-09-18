package example.selling.java.impl;

import example.selling.java.PrintOffice;
import example.selling.java.RequestPool;
import example.selling.java.Ticket;

import java.util.List;

/**
 * author: code.babe
 * date: 2016-09-17 00:55
 * 电影院
 */
public class Cinema implements PrintOffice {

    private RequestPool pool;

    public Cinema(RequestPool pool) {
        this.pool = pool;
    }

    public boolean isEnough() {
        return pool.isEmpty();
    }

    public List<Ticket> allocateTickets() {
        return null;
    }

    // 完成打印后将这个ticket取出, 这样符合实际情况
    public void printTicket() {
        Ticket ticket = (Ticket) pool.get();
        if (ticket == null) {
            return;
        }
        System.out.println(String.format("ticket name = %s is printed", ticket.getName()));
        try {
            Thread.sleep(500);
            pool.pop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
