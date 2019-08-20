package pers.xiaoming.notebook.testng;

public class Singleton implements AutoCloseable {
    private static String[] singleton;

    public static String[] getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new String[10];
            }
        }
        return singleton;
    }

    @Override
    public void close() throws Exception {
        singleton = null;
    }
}
