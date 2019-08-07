package pers.xiaoming.notebook.junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestWatcher;
import org.junit.rules.Verifier;
import org.junit.runner.Description;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class RuleAndClassRuleDemo {

    /**
     * If there are multiple
     *  annotated {@link ClassRule}s on a class, they will be applied in an order
     *  that depends on your JVM's implementation of the reflection API, which is
     *  undefined, in general. However, Rules defined by fields will always be applied
     *  before Rules defined by methods.
     */
    @ClassRule
    public static EmbeddedStorage embeddedMySql = new EmbeddedMySql();

    @ClassRule
    public static EmbeddedStorage embeddedRedis = new EmbeddedRedis();

    public static EmbeddedStorage embeddedOracle = new EmbeddedOracle();

    private static abstract class EmbeddedStorage extends ExternalResource {
        protected abstract String getName();

        @Override
        protected void before() throws Throwable {
            System.out.println("Set up embedded " + getName());
        }

        @Override
        protected void after() {
            System.out.println("Shutdown embedded " + getName());
        }

        public void prepareData() {
            System.out.println("Prepare Data for " + getName());
        }
    }

    private static class EmbeddedMySql extends EmbeddedStorage {
        @Override
        protected String getName() {
            return "MySQL";
        }
    }

    private static class EmbeddedRedis extends EmbeddedStorage {
        @Override
        protected String getName() {
            return "Redis";
        }
    }

    private static class EmbeddedOracle extends EmbeddedStorage {
        @Override
        protected String getName() {
            return "Oracle";
        }
    }

    @BeforeClass
    public static void prepareData() {
        System.out.println("\n@BeforeClass");
        embeddedMySql.prepareData();
        embeddedRedis.prepareData();
        embeddedOracle.prepareData();
        System.out.println();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("\n@AfterClass");
    }

    @Rule
    public Verifier verifier = new Verifier() {
        @Override
        protected void verify() throws Throwable {
            System.out.println("Verify Result");
        }
    };

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        long startEpoc;
        @Override
        protected void starting(Description description) {
            startEpoc = System.currentTimeMillis();
        }

        @Override
        protected void finished(Description description) {
            System.out.println("Time cost is " + (System.currentTimeMillis() - startEpoc));
        }
    };

    @Before
    public void beforeMethod() {
        System.out.println("\n@Before");
    }

    @After
    public void afterMethod() {
        System.out.println("\n@After");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 is running");
    }

    @Test
    public void test2() {
        System.out.println("Test 2 is running");
    }
}
