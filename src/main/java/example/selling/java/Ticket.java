package example.selling.java;

/**
 * author: code.babe
 * date: 2016-09-14 19:35
 * 一张票由一个线程来处理
 */
public class Ticket {

    private String id;
    private String name;
    private Thread sender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Thread getSender() {
        return sender;
    }

    public void setSender(Thread sender) {
        this.sender = sender;
    }
}
