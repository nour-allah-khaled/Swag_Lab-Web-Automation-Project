package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Page07_Finish {
    private WebDriver driver;
    private final By back = By.cssSelector("#back-to-products");
    public Page07_Finish(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Clicking on the Back button to return to the Home page after successfully finishing the checkout process")
    public Page02_Home clickBack() {
        Utilitie.clicking(driver, back);
        return new Page02_Home(driver);
    }
    public String assert_URL() {
        return driver.getCurrentUrl();
    }
}