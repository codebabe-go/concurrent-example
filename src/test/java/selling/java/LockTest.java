package selling.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author: code.babe
 * date: 2016-09-20 21:14
 * 锁测试
 */
public class LockTest {

    private final static ReentrantLock LOCK = new ReentrantLock();

    @Test
    public void test() {
        try {
//            LOCK.tryLock();
            System.out.println("hello");
        } catch (Exception e) {
        } finally {
            if (LOCK.hasQueuedThreads()) {
                LOCK.unlock();
            }
        }
    }

}
