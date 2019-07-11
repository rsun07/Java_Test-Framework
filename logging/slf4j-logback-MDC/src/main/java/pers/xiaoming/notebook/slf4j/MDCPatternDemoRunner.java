package pers.xiaoming.notebook.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class MDCPatternDemoRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MDCPatternDemoRunner.class);

    private CountDownLatch countDownLatch;

    public MDCPatternDemoRunner(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        logger.info("MDCPatternDemoRunner is Running");
        countDownLatch.countDown();
    }
}
