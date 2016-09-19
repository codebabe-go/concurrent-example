package example.selling.java.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: code.babe
 * date: 2016-09-14 14:27
 */
public class TaskManager {

    private static AtomicInteger counter = new AtomicInteger(0);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static ExecutorService getService() {
        return executorService;
    }

    public static void shutdown() {
        executorService.shutdown();
    }

}
