package example.selling.java;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: code.babe
 * date: 2016-09-17 15:17
 * 使用单例模式来模拟db
 */
public final class DB {

    // TODO: 16/9/18 确定最优的数据结构
    private DB() {
        db = new LinkedBlockingDeque<>(MAX_CAPACITY);
        initDB(1000);
    }

    public interface Type {
        int ACTUALLY = 0;
        int CACHED = 1;
    }

    private final static Object LOCK = new Object();

    private final static int MAX_CAPACITY = 1 << 15;

    private final static ReentrantLock lock = new ReentrantLock();

    // 这个长度才是实际的长度, 而不是db.size()
    private static int actuallySize;

    private static int cachedSize;

    // 同一个包下 但是不对其他开放, 使用双向队列, 便于做锁定处理
    BlockingDeque db;

    private static DB INSTANCE = null;

    /**
     * TODO 16/9/20 这里设计的有问题 不应该是在内部处理的 以后可以稍作改动
     * 初始化 这个是内部方法自己做处理
     * @param number 票的数目
     */
    private final void initDB(int number) {
        System.out.println(String.format("[DB.initDB] info - init db size = %d", number));
        for (int i = 0; i < number; i++) {
            try {
                db.putLast(new Ticket());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        actuallySize = number;
    }

    public static DB singleton() {
        if (null == INSTANCE) {
            synchronized (LOCK) {
                if (null == INSTANCE) {
                    INSTANCE = new DB();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * @param count 可正可负
     * @return
     */
    static int increase(int count, int type) {
        lock.tryLock();
        try {
            if (Type.ACTUALLY == type) {
                actuallySize += count;
                return actuallySize;
            }
            if (Type.CACHED == type) {
                cachedSize += count;
                return cachedSize;
            }
            // 错误操作返回-1
            return -1;
        } finally {
            if (lock.hasQueuedThreads()) {
                lock.unlock();
            }
        }
    }


    static int currentSize() {
        lock.tryLock();
        try {
            return actuallySize;
        } finally {
            if (lock.hasQueuedThreads()) {
                lock.unlock();
            }
        }
    }

    static int currentCached() {
        lock.tryLock();
        try {
            return cachedSize;
        } finally {
            // TODO: 16/9/20 可能存在问题  
            if (lock.hasQueuedThreads()) {
                lock.unlock();
            }
        }
    }
}
