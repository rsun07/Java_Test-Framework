package pers.xiaoming.notebook;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;
import org.apache.log4j.Logger;
import pers.xiaoming.notebook.utils.PathUtils;

public class Helloworld {

    private static Logger logger = Logger.getLogger(Helloworld.class);

    @Test
    public void testXml() {
        DOMConfigurator.configure(PathUtils.TEST_RESOURCE_DIR + "/helloworld.xml");
        logger.debug("Hello world Log4j XML");
    }

    @Test
    public void testProperties() {
        PropertyConfigurator.configure(PathUtils.TEST_RESOURCE_DIR + "/helloworld.properties");
        logger.debug("Hello world Log4j Properties");
    }
}
