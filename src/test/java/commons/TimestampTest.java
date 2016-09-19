package commons;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * author: code.babe
 * date: 2016-09-17 13:35
 */
public class TimestampTest {

    private Timestamp now;

    @Before
    public void before() {
        now = new Timestamp(System.currentTimeMillis());
    }

    @Test
    public void test() {
        System.out.println(String.format("%s", now.toString()));
    }

}
