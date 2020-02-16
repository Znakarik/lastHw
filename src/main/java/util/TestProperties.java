package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties = new Properties();
    private static TestProperties INSTANCE = null;
    File prop = new File("./src/main/resources/app.properties");

    public TestProperties() {
        try {
            properties.load(new FileInputStream(prop));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TestProperties gitInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}
