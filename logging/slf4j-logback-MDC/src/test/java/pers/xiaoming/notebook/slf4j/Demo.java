package pers.xiaoming.notebook.slf4j;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

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
            MDC.put("user_id", String.valueOf(i));
            MDC.put("user_name", "myname" + i);

            MDCGetterDemoRunner runner = new MDCGetterDemoRunner(countDownLatch);
            executor.submit(runner);
        }

        countDownLatch.await();
    }

    @Test
    public void MDClog4JPatternDemo() throws InterruptedException {
        for (int i = 0; i < NUM_THREADS; i++) {
            MDC.put("user.id", String.valueOf(i));
            MDC.put("user.name", "myname" + i);

            MDCPatternDemoRunner runner = new MDCPatternDemoRunner(countDownLatch);
            executor.submit(runner);
        }

        countDownLatch.await();
    }
}
