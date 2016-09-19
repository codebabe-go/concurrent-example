package commons;

import org.apache.commons.cli.*;
import org.junit.Before;
import org.junit.Test;

/**
 * author: code.babe
 * date: 2016-09-19 14:19
 */
public class CommandTest {

    private CommandLineParser parser;
    private Options options;

    @Before
    public void before() throws ParseException {
        parser = new BasicParser();
        options = new Options();
    }

    @Test
    public void test() {
    }

}
