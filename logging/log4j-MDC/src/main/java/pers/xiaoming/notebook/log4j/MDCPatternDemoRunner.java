package pers.xiaoming.notebook.log4j;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class MDCPatternDemoRunner implements Runnable {
    private static final Logger logger = Logger.getLogger(MDCPatternDemoRunner.class);

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
