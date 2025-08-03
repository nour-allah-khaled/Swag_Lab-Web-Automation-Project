package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class OverView_Page  {
    private WebDriver driver;
    private final By finishBtn = By.cssSelector("#finish");
    private final By cancleBtn = By.cssSelector("#cancel");
    public OverView_Page(WebDriver driver) {
        this.driver = driver;
    }
    public Finish_Page clickOnFinshBtn() {
        Utilitie.clicking(driver, finishBtn);
        return new Finish_Page(driver);
    }
    public Checkout_Page clickOnCancleBtn() {
        Utilitie.clicking(driver, cancleBtn);
        return new Checkout_Page(driver);
    }
    public String assert_URL() {
        return driver.getCurrentUrl();
    }
}