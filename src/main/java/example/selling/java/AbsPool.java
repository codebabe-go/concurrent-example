package example.selling.java;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * author: code.babe
 * date: 2016-09-14 17:20
 * 使用双向队列来, 统一规定为左进右出, 同时对DB中的一些方法进行组合封装
 */
public abstract class AbsPool<T> implements Pool<T> {

    public AbsPool() {
        this.requestPool = DB.singleton().db;
    }

    public AbsPool(BlockingDeque<T> requestPool) {
        this.requestPool = requestPool;
    }

    // 超时时间, 单位ms, 应该保证超时时间比打印时间要久
    private static final int TIMEOUT = 1000;

    private BlockingDeque<T> requestPool;

    @Override
    public int size() {
        return requestPool.size();
    }

    @Override
    public int actuallySize() {
        return DB.currentSize();
    }

    @Override
    public int cachedSize() {
        return DB.currentCached();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T pop() {
        increase(-1, DB.Type.ACTUALLY);
        return get();
    }

    @Override
    public void push(T ele) {
        try {
            put(ele);
            increase(1, DB.Type.ACTUALLY);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(String.format("[AbsPool]error"));
        }
    }

    @Override
    public int increase(int count, int type) {
        return DB.increase(count, type);
    }

    protected T get() {
        try {
            return requestPool.pollFirst(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(String.format("[AbsPool]error - get element timeout"));
        }
        return null;
    }

    protected void put(T ele) throws InterruptedException {
        requestPool.offerLast(ele, TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
