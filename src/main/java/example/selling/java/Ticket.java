package example.selling.java;

import java.sql.Timestamp;

/**
 * author: code.babe
 * date: 2016-09-14 19:35
 */
public class Ticket {

    public Ticket() {
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

    public Ticket builder(String name, Request sender) {
        this.id = new Timestamp(System.currentTimeMillis());
        this.name = name;
        this.sender = sender;
        return this;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sender=" + sender +
                '}';
    }
}
