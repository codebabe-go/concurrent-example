package example.selling.java.util;

/**
 * author: code.babe
 * date: 2016-09-20 16:36
 */
public class ThreadUtil {
    public static void sleep(long mills) {
        try {
            Thread.currentThread().sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
