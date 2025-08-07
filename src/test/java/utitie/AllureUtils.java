package utitie;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;

public class AllureUtils {
    public static void cleanAllureResults() {
        FileUtils.deleteQuietly(new File("target/allure-results"));
    }

    public static void addScreenshotToAllure(String screenName, String screenshotPath) {
        try {
            Allure.addAttachment(screenName, newInputStream(Path.of(screenshotPath)));
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
