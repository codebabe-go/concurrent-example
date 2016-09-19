package example.selling.java;

/**
 * author: code.babe
 * date: 2016-09-14 13:59
 */
public interface PrintOffice {

    boolean isEnough();

    /**
     * 为了简化模型, 这里规定一个人只能买一张票
     * @param request
     * @return
     */
    Ticket allocateTickets(Request request);

    Response printTicket(Ticket ticket);

}
