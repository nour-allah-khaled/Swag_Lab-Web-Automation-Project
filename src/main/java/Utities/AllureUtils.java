package Utities;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class AllureUtils {
    public static void cleanAllureResults()
    {
        FileUtils.deleteQuietly(new File("Test_out/allure-results"));
    }
}
