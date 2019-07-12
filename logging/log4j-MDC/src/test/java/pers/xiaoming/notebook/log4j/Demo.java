package pers.xiaoming.notebook.log4j;

import org.apache.log4j.MDC;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    private static final int NUM_THREADS = 5;
    private ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    private CountDownLatch countDownLatch;

    @Before
    public void setup() {
        countDownLatch = new CountDownLatch(NUM_THREADS);
    }

    @Test
    public void MDCGetterDemo() throws InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {

            // It is confusing, because here is the main/test thread put the value
            // the executor thread shouldn't have access to the main/test thread's MDC which is threadlocal
            MDC.put("user_id", i);
            MDC.put("user_name", "myname" + i);

            MDCGetterDemoRunner runner = new MDCGetterDemoRunner(countDownLatch);
            executor.submit(runner);
        }

        countDownLatch.await();
    }

    @Test
    public void MDCGetterCorrectDemo() throws InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {

            final int index = i;
            MDCGetterDemoRunner runner = new MDCGetterDemoRunner(countDownLatch);
            executor.submit(() -> {
                MDC.put("user_id", index);
                MDC.put("user_name", "myname" + index);
                runner.run();
            });
        }

        countDownLatch.await();
    }

    @Test
    public void MDCGetterPutTwiceTest() throws InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {
            MDC.put("user_id", "main/test:" + i);
            MDC.put("user_name", "main/test:" + i);

            final int index = i;
            MDCGetterDemoRunner runner = new MDCGetterDemoRunner(countDownLatch);
            executor.submit(() -> {
                MDC.put("user_id", Thread.currentThread().getName() + ":" + index);
                MDC.put("user_name", Thread.currentThread().getName() + ":" + index);
                runner.run();
            });
        }

        countDownLatch.await();
    }

    @Test
    public void MDClog4JPatternDemo() throws InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {
            MDC.put("user.id", i);
            MDC.put("user.name", "myname" + i);

            MDCPatternDemoRunner runner = new MDCPatternDemoRunner(countDownLatch);
            executor.submit(runner);
        }

        countDownLatch.await();
    }
}
