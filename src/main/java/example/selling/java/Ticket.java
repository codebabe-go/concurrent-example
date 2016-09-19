package example.selling.java;

import java.sql.Timestamp;

/**
 * author: code.babe
 * date: 2016-09-14 19:35
 */
public class Ticket {

    public Ticket(Request sender, String name) {
        this.id = new Timestamp(System.currentTimeMillis());
        this.sender = sender;
        this.name = name;
    }

    private Timestamp id;
    private String name;
    private Request sender;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Request getSender() {
        return sender;
    }

    public void setSender(Request sender) {
        this.sender = sender;
    }
}
