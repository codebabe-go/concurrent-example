package selling.java;

import example.selling.java.Request;
import example.selling.java.impl.Customer;
import example.selling.java.util.TaskManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * author: code.babe
 * date: 2016-09-19 15:08
 */
public class Main {

    private ExecutorService requestPool;
    private ExecutorService solvePool;
    private List<Customer> customers;

    @Before
    public void before() {
        requestPool = TaskManager.getService();
        solvePool = TaskManager.getService();
        customers = new ArrayList<>();
        // 生成10,000个请求, 模拟高并发情况
        for (int i = 0; i <= 10000; i++) {
            customers.add(new Customer(Request.EventType.EMPTY.getType()));
        }
    }

    @Test
    public void test() {
        for (Customer customer : customers) {
            requestPool.execute(new Thread(customer));
        }
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
