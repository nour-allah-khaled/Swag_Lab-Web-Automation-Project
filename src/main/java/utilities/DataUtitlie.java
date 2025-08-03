package utilities;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class DataUtitlie {
    public static final String TEST_DATA_PATH = "src/test/resources/TestData/";
    public static String getJsonData(String jsonFilename, String field) {
        try {
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }
}
