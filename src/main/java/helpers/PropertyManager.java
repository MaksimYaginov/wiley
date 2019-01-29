package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/data.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return properties.getProperty(propertyName);
    }
}
