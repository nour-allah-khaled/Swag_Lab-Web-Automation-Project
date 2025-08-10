package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Page06_OverView {
    private WebDriver driver;
    private final By finishBtn = By.cssSelector("#finish");
    private final By cancleBtn = By.cssSelector("#cancel");
    public Page06_OverView(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Clicking on Finish button to complete the checkout process")
    public Page07_Finish clickOnFinshBtn() {
        Utilitie.clicking(driver, finishBtn);
        return new Page07_Finish(driver);
    }
    @Step("Clicking on Cancel button to return to Checkout page")
    public Page05_Checkout clickOnCancleBtn() {
        Utilitie.clicking(driver, cancleBtn);
        return new Page05_Checkout(driver);
    }
    public String assert_URL() {
        return driver.getCurrentUrl();
    }
}