package example.selling.java;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: code.babe
 * date: 2016-09-14 17:20
 * 使用双向队列来, 统一规定为左进右出
 */
public abstract class AbsPool<T> implements Pool<T> {

    public AbsPool() {
        this.requestPool = DB.singleton().db;
    }

    public AbsPool(BlockingDeque<T> requestPool) {
        this.requestPool = requestPool;
    }

    // 超时时间, 单位ms
    private static final int TIMEOUT = 500;

    protected BlockingDeque<T> requestPool;

    protected AtomicInteger size;

    @Override
    public int size() {
        return requestPool.size() == size.get() ? size.get() : requestPool.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T pop() {
        try {
            return requestPool.pollFirst(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        }
        return null;
    }

    @Override
    public void push(T ele) {
        try {
            requestPool.offerLast(ele, TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        }
    }
}
