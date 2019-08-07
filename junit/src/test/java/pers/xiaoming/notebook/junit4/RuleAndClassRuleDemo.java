package pers.xiaoming.notebook.junit4;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

public class RuleAndClassRuleDemo {

    /**
     * If there are multiple
     *  annotated {@link ClassRule}s on a class, they will be applied in an order
     *  that depends on your JVM's implementation of the reflection API, which is
     *  undefined, in general. However, Rules defined by fields will always be applied
     *  before Rules defined by methods.
     */
    @ClassRule
    public static EmbeddedMySql embeddedMySql = new EmbeddedMySql();

    @ClassRule
    public static EmbeddedRedis embeddedRedis = new EmbeddedRedis();

    private static class EmbeddedMySql extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("Set up embedded MySql!");
        }

        @Override
        protected void after() {
            System.out.println("Shutdown embedded MySql!");
        }

        public void prepareData() {
            System.out.println("Prepare MySql Data");
        }
    }

    private static class EmbeddedRedis extends ExternalResource {
        @Override
        protected void before() throws Throwable {
            System.out.println("Set up embedded Redis!");
        }

        @Override
        protected void after() {
            System.out.println("Shutdown embedded Redis!");
        }

        public void prepareData() {
            System.out.println("Prepare Redis Data");
        }
    }

    @BeforeClass
    public static void prepareData() {
        embeddedMySql.prepareData();
        embeddedRedis.prepareData();
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
