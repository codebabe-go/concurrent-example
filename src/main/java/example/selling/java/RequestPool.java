package example.selling.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: code.babe
 * date: 2016-09-14 17:20
 * 以队列的形式来消费, 开放购物车功能, 支持抢购
 */
public class RequestPool<T> {

    // 超时时间, 单位ms
    private static final int TIMEOUT = 500;

    // 基本没有上线
    BlockingQueue<T> requestPool = DB.singleton().db;

    private AtomicInteger size;

    public int size() {
        return requestPool.size() == size.get() ? size.get() : requestPool.size();
    }

    public void put(T e) {
        try {
            requestPool.offer(e, TIMEOUT, TimeUnit.MILLISECONDS);
            size.incrementAndGet();
        } catch (InterruptedException e1) {
        }
    }

    public T pop() {
        try {
            requestPool.poll(TIMEOUT, TimeUnit.MILLISECONDS);
            size.decrementAndGet();
        } catch (InterruptedException e) {
        }
        return null;
    }

    // get first element
    public T get() {
        return requestPool.element();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
