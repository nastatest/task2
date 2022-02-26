import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;
    static String projectPath = System.getProperty("user.dir");

    public PropertyReader(String propertyFileName) {
        String fileName = projectPath + "/src/test/resources/" + propertyFileName;

        properties = new Properties();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't read properties");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties file not found at " + fileName);
        }
    }

    public String getChromeDriverPath() {
        return properties.getProperty("chromeDriverPath");
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public int getTimeout() {
        return Integer.valueOf(properties.getProperty("timeout"));
    }
}