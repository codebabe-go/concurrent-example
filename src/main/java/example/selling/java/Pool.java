package example.selling.java;

/**
 * author: code.babe
 * date: 2016-09-18 12:47
 */
public interface Pool<T> {

    int size();

    boolean isEmpty();

    /**
     * 获取队列头部的元素, 防止被非指定的消费者操作
     * @return
     */
    T lock();

    /**
     * 放回去, 或成功消费, 或消费失败
     * @param ele
     * @return
     */
    boolean unlock(T ele);

    T pop();

    void push(T ele);

}
