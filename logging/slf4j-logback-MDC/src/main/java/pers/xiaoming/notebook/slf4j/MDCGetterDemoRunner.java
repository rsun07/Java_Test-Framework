package pers.xiaoming.notebook.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.CountDownLatch;

public class MDCGetterDemoRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MDCGetterDemoRunner.class);


    private CountDownLatch countDownLatch;

    public MDCGetterDemoRunner(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        logger.info(String.format("MDCGetterDemoRunner is running, user_id is: %s, user_name is : %s", MDC.get("user_id"), MDC.get("user_name")));
        countDownLatch.countDown();
    }
}
