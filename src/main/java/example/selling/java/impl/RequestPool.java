package example.selling.java.impl;

import example.selling.java.AbsPool;
import example.selling.java.DB;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: code.babe
 * date: 2016-09-18 13:09
 */
public class RequestPool<T> extends AbsPool<T> {

    private AtomicInteger cachedSize = new AtomicInteger(0);

    public RequestPool() {
        super();
    }

    public RequestPool(BlockingDeque<T> requestPool) {
        super(requestPool);
    }

    @Override
    public T lock() {
        cachedSize.set(increase(1, DB.Type.CACHED));
        T ele = get();
        return ele;
    }

    @Override
    public boolean unlock(T ele) {
        cachedSize.set(increase(-1, DB.Type.CACHED));
        try {
            put(ele);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int cacheSize() {
        return cachedSize.intValue();
    }

}
