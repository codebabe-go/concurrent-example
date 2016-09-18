package scala.akka.task;

import scala.akka.task.job.TestTask;

import java.util.ArrayList;
import java.util.List;

/**
 * author: code.babe
 * date: 2016-09-12 12:59
 */
public class MainTask {

    private List<ScalaTask> tasks = new ArrayList<ScalaTask>();

    public void add(ScalaTask task) {
        tasks.add(task);
    }

    public void justDoIt() {
        for (ScalaTask scalaTask : tasks) {
            scalaTask.run();
        }
    }

    public static void main(String[] args) {
        MainTask mainTask = new MainTask();
        mainTask.add(new TestTask());

        mainTask.justDoIt();
    }
}
