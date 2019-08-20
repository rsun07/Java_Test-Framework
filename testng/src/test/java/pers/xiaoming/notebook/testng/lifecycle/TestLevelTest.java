package pers.xiaoming.notebook.testng.lifecycle;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pers.xiaoming.notebook.testng.Singleton;

public class TestLevelTest {
    @BeforeTest
    public void beforeTest() {
        System.out.println("--Running before Test: \t\t Get already initialized Singleton: " + Singleton.getSingleton());
    }

    @AfterTest
    public void afterTest() {
        System.out.println("--Running after Test.");
    }
}
