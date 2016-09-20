package aqs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import example.selling.java.util.TaskManager;

import java.util.concurrent.*;

/**
 * author: code.babe
 * date: 2016-09-14 14:25
 */
public class AqsTest {

    private ExecutorService executorService;

    @Before
    public void before() {
        executorService = TaskManager.getService();
    }

    @Test
    public void test() {
        final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
        Assert.assertTrue(blockingQueue.size() == 0);
        FutureTask<Integer> futureTask = new FutureTask(new Callable() {
            public Integer call() throws Exception {
                return blockingQueue.take();
            }
        });
        executorService.submit(futureTask);
        Exception exception = null;
        try {
            futureTask.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            exception = e;
        } catch (ExecutionException e) {
            exception = e;
        } catch (TimeoutException e) {
            exception = e;
        } finally {
            TaskManager.shutdown();
        }
        Assert.assertTrue(exception instanceof TimeoutException);
    }

    @Test
    public void testDeque() {
        BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
        blockingDeque.offerLast("hello");
        Assert.assertTrue(blockingDeque.poll().equals("hello"));
    }
}
