package selling.java;

import example.selling.java.Request;
import example.selling.java.Ticket;
import example.selling.java.impl.Customer;
import example.selling.java.impl.RequestPool;
import example.selling.java.util.TaskManager;
import example.selling.java.util.ThreadUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * author: code.babe
 * date: 2016-09-19 15:08
 * java该模块的测试用例
 */
public class Main {

    private ExecutorService requestPool;
    private ExecutorService solvePool;
    private List<Customer> customers;
    private RequestPool<Ticket> pool;
    // 线程数量自己规划 可以慢慢加大
    private int size;

    // 设置成五分钟进行调试
    private final static long DEBUG_TIME = 1000 * 60 * 5;
    // 常规的睡眠时间
    private final static long SLEEP_TIME = 1000 * 30;

    @Before
    public void before() {
        size = 1000;
        requestPool = TaskManager.getService();
        solvePool = TaskManager.getService();
        customers = new ArrayList<>();
        pool = new RequestPool<>();
        // 生成10,000个请求, 模拟高并发情况
        for (int i = 0; i < size; i++) {
            customers.add(new Customer(i, Request.EventType.EMPTY.getType(), pool));
        }
    }

    @Test
    public void test() {
        // 直接多线程开始执行
        for (Customer customer : customers) {
            requestPool.execute(customer);
        }

        // 主线程sleep半分钟, 保证主线程不会马上终止
        ThreadUtil.sleep(SLEEP_TIME);
    }
}
