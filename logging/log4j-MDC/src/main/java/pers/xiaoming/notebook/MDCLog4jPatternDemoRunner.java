package pers.xiaoming.notebook;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class MDCLog4jPatternDemoRunner implements Runnable {
    private static final Logger logger = Logger.getLogger(MDCLog4jPatternDemoRunner.class);

    private CountDownLatch countDownLatch;

    public MDCLog4jPatternDemoRunner(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        logger.info("MDCLog4jPatternDemoRunner is Running");
        countDownLatch.countDown();
    }
}
