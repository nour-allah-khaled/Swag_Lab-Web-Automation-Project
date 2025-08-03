package utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v135.page.model.Screenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Utilitie {
    private static final String SCREENSHOTS_PATH = "Test_out/Screenshots/";
    public static void clicking(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        System.out.println("Clicked on element: " + locator.toString());
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public static void acceptAlertIfPresent(WebDriver driver, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert was present.");
        }
    }
    public static String getErrorMessageFromPage(WebDriver driver, By locator) {

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        generalWait(driver).until(ExpectedConditions.alertIsPresent());
        return driver.findElement(locator).getText();
    }
    public static WebDriverWait generalWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",findElement(driver, locator));
    }
    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
    public static void selectDropDown(WebDriver driver, By locator, String option) {
        new Select(findElement(driver, locator)).selectByVisibleText(option);
    }
    public static String GetTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }
    public static void Screen_shot(WebDriver driver, String screenshotName) {
        try{
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName+"-"+GetTimeStamp()+ ".png");
            FileUtils.copyFile(screenshot, screenshotFile);

            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<WebElement> findElements(WebDriver driver, By locator) {
        return driver.findElements(locator);
    }


}

