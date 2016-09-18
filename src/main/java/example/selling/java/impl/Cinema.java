package example.selling.java.impl;

import example.selling.java.Pool;
import example.selling.java.PrintOffice;
import example.selling.java.AbsPool;
import example.selling.java.Ticket;

import java.util.List;

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
        return pool.isEmpty();
    }

    public List<Ticket> allocateTickets() {
        return null;
    }

    // 完成打印后将这个ticket取出, 这样符合实际情况
    public void printTicket() {
        Ticket ticket = null;
        if (ticket == null) {
            return;
        }
        System.out.println(String.format("ticket name = %s is printed", ticket.getName()));
        try {
            Thread.sleep(500);
            // TODO: 16/9/18
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
