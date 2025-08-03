package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(String browser) {
        switch (browser.toLowerCase())
        {
            case "chrome":
                ChromeOptions Chromeoptions = new ChromeOptions();
                Chromeoptions.addArguments("--start-maximized");
                driver.set(new ChromeDriver(Chromeoptions));
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                driver.set(new EdgeDriver(options));
        }
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void quit()
    {
        getDriver().quit();
        driver.remove();
    }
}
