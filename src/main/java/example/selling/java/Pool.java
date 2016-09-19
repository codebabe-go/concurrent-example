package example.selling.java;

/**
 * author: code.babe
 * date: 2016-09-18 12:47
 */
public interface Pool<T> {

    int size();

    int actuallySize();

    boolean isEmpty();

    /**
     * 获取队列头部的元素, 防止被非指定的消费者操作
     * @return
     */
    T lock();

    /**
     * 被锁住的大小, 这是一个共享数据
     * TODO 如何定义这个方法 有待商榷
     * @return
     */
    int cachedSize();

    /**
     * 放回去, 或成功消费, 或消费失败
     * @param ele
     * @return
     */
    boolean unlock(T ele);

    T pop();

    void push(T ele);

    int increase(int count, int type);

}
