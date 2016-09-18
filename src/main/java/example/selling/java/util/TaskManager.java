package example.selling.java.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: code.babe
 * date: 2016-09-14 14:27
 */
public class TaskManager {

    private static AtomicInteger counter = new AtomicInteger(0);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2, new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread thread = new Thread();
            // set name
            thread.setName(String.format("thread - %d", counter.incrementAndGet()));
            return thread;
        }
    });

    public static ExecutorService getService() {
        return executorService;
    }

    public static void shutdown() {
        executorService.shutdown();
    }

}
