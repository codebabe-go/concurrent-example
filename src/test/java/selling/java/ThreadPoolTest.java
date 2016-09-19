package selling.java;

import example.selling.java.util.TaskManager;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: code.babe
 * date: 2016-09-19 15:50
 */
public class ThreadPoolTest {

    private ExecutorService threadPool1;
    private ExecutorService threadPool2;

    @Before
    public void before() {
        threadPool1 = TaskManager.getService();
        threadPool2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    @Test
    public void test() {
    }

}
