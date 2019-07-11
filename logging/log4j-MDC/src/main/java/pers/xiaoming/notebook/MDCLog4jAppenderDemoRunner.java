package pers.xiaoming.notebook;

import org.apache.log4j.Logger;

public class MDCLog4jAppenderDemoRunner implements Runnable {
    Logger logger = Logger.getLogger(MDCLog4jAppenderDemoRunner.class);

    @Override
    public void run() {
        logger.info("MDCLog4jAppenderDemoRunner is Running");
    }
}
