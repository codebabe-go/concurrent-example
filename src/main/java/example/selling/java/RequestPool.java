package example.selling.java;

import java.util.concurrent.BlockingDeque;

/**
 * author: code.babe
 * date: 2016-09-18 13:09
 */
public class RequestPool<T> extends AbsPool<T> {

    private boolean isLocked;

    private T lockedNode;

    public RequestPool() {
        super();
    }

    public RequestPool(BlockingDeque<T> requestPool) {
        super(requestPool);
    }

    @Override
    public T lock() {
        return null;
    }

    @Override
    public boolean unlock(T ele) {
        return false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public T getLockedNode() {
        return lockedNode;
    }
}
