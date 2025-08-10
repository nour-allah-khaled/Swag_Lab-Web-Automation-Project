package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Page05_Checkout {
    private final WebDriver driver;
    private final By cancelBtn = By.cssSelector("#cancel");
    private final By continueBtn = By.cssSelector("#continue");
    private final By firstname = By.cssSelector("#first-name");
    private final By lastname = By.cssSelector("#last-name");
    private final By postalcode = By.cssSelector("#postal-code");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    public Page05_Checkout(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Clicking on Cancel button to return to Add to Cart page")
    public Page04_AddToCart Click_Cancel()
    {
        Utilitie.clicking(driver, cancelBtn);
        return new Page04_AddToCart(driver);
    }
    @Step("Entering First Name: {first}")
    public Page05_Checkout First_Name(String first)
    {
        Utilitie.sendKeys(driver,firstname,first);
        return this;
    }
    @Step("Entering Last Name: {last}")
    public Page05_Checkout Last_Name(String last)
    {
        Utilitie.sendKeys(driver,lastname,last);
        return this;
    }
    @Step("Entering Postal Code: {postal}")
    public Page05_Checkout Postal_Code(String postal)
    {
        Utilitie.sendKeys(driver,postalcode,postal);
        return this;
    }
    @Step("Clicking on Continue button to proceed to Overview page")
    public Page06_OverView Click_Continue()
    {
        Utilitie.clicking(driver, continueBtn);
        return new Page06_OverView(driver);
    }
    public String assert_URL() {
        return driver.getCurrentUrl();
    }
    public boolean assertErrorMessage(String expectedMessage) {
        return driver.findElement(errorMessage).getText().equals(expectedMessage);
    }
}
