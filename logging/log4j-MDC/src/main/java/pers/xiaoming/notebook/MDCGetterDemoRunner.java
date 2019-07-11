package pers.xiaoming.notebook;

import org.apache.log4j.Logger;

public class MDCGetterDemoRunner implements Runnable {
    Logger logger = Logger.getLogger(MDCGetterDemoRunner.class);

    @Override
    public void run() {
        logger.info("Running");
    }
}
