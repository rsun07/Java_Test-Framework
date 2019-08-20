package pers.xiaoming.notebook.testng.lifecycle;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pers.xiaoming.notebook.testng.Singleton;

public class SuiteLevelTest {
    @BeforeSuite
    public static void initResource() {
        String[]singleton = Singleton.getSingleton();
        System.out.println("Running before Suite: \t\t Initialize Singleton: " + singleton);
    }

    @AfterSuite
    public static void destroyResource() {
        System.out.println("Running after Suite.");
    }
}
