package example.selling.java.impl;

import example.selling.java.Request;
import example.selling.java.Response;
import example.selling.java.Ticket;

/**
 * author: code.babe
 * date: 2016-09-17 13:45
 * 初始化的任务一般放在这个类进行, 为runnable的子类, 目的是为了主动按顺序发起请求
 * TODO 这样可能会让这个类显得比较重
 */
public class Customer extends Request {

    private long id;
    private TicketAgent agent;
    // 设置关联, 在"长连接中"防止失效
    private Ticket ticket;

    // 在这个例子中将会调用这个构造方法, 全局只有一个requestPool
    public Customer(long id, int type, RequestPool<Ticket> requestPool) {
        super(type);
        this.id = id;
        this.agent = new TicketAgent(new Cinema(requestPool));
    }

    // 这里最好指定一个电影院, 不可能让customer : ticketAgent : cinema = 1 : 1 : 1
    public Customer(int type, Cinema cinema) {
        super(type);
        // TODO: 16/9/19 这里需要初始化agent, 需要注意的是agent的构造函数中还包含需要初始化的cinema
        // 为了逻辑简单, 每个售票员只服务一个顾客
        this.agent = new TicketAgent(cinema);
    }

    public Customer(int type, TicketAgent agent) {
        super(type);
        this.agent = agent;
    }

    @Override
    public void run() {
        if (EventType.EMPTY.getType() == getType()) {
            int type = getType();
            System.out.println(String.format("[Customer.run()] info - customer id = %d is requesting now", id));
            while (true) { // 构建一个死循环来模拟一个长连接
                System.out.println(String.format("request type = %s, id = %d", Request.EventType.name(type), id));
                Response response = agent.dispatchRequest(setType(type++));
                if (response == null || Response.ReturnCode.SUCCESS != response.getCode()) {
                    System.out.println(String.format("[Customer.run()] error - id = %d, error message = %s", id, response.getData()));
                    return;
                }

                if (response != null && Response.ReturnCode.SUCCESS == response.getCode() && response.getData() instanceof Ticket) {
                    setTicket((Ticket) response.getData());
                }

                // 结束完整的一次请求
                // 期间返回的data类型不一样 所以这里处理起来比较复杂
                if (response != null && Response.ReturnCode.SUCCESS == response.getCode() && response.getData() == null) {
                    System.out.println(String.format("[Customer.run()] info - solve success, completed customer id = %d", id));
                    return;
                }
            }
        } else {
            return;
        }
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", agent=" + agent +
                "} " + super.toString();
    }
}