package Pages;

import Utities.Utilitie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Finish_Page {
    private WebDriver driver;
    private final By back = By.cssSelector("#back-to-products");
    public Finish_Page(WebDriver driver) {
        this.driver = driver;
    }
    public Home_Page clickBack() {
        Utilitie.clicking(driver, back);
        return new Home_Page(driver);
    }
    public String assert_URL() {
        return driver.getCurrentUrl();
    }
}