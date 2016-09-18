package example.selling.java;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * author: code.babe
 * date: 2016-09-17 15:17
 * 使用单例模式来模拟db
 */
public final class DB {

    private DB() {
        db = new LinkedBlockingDeque<>(MAX_CAPACITY);
    }

    private final static Object LOCK = new Object();

    private static final int MAX_CAPACITY = 1 << 15;

    // 同一个包下 但是不对其他开放, 使用双向队列, 便于做锁定处理
    BlockingDeque db;

    private static DB INSTANCE = null;

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
}
