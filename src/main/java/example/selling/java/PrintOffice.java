package example.selling.java;


import java.util.List;

/**
 * author: code.babe
 * date: 2016-09-14 13:59
 */
public interface PrintOffice {

    boolean isEnough();

    List<Ticket> allocateTickets();

    void printTicket();

}
