package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Checkout_Page {
    private final WebDriver driver;
    private final By cancelBtn = By.cssSelector("#cancel");
    private final By continueBtn = By.cssSelector("#continue");
    private final By firstname = By.cssSelector("#first-name");
    private final By lastname = By.cssSelector("#last-name");
    private final By postalcode = By.cssSelector("#postal-code");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    public Checkout_Page(WebDriver driver) {
        this.driver = driver;
    }
    public AddToCart_Page Click_Cancel()
    {
        Utilitie.clicking(driver, cancelBtn);
        return new AddToCart_Page(driver);
    }
    public Checkout_Page First_Name(String first)
    {
        Utilitie.sendKeys(driver,firstname,first);
        return this;
    }
    public Checkout_Page Last_Name(String last)
    {
        Utilitie.sendKeys(driver,lastname,last);
        return this;
    }
    public Checkout_Page Postal_Code(String postal)
    {
        Utilitie.sendKeys(driver,postalcode,postal);
        return this;
    }
    public OverView_Page Click_Continue()
    {
        Utilitie.clicking(driver, continueBtn);
        return new OverView_Page(driver);
    }
    public String assert_URL() {
        return driver.getCurrentUrl();
    }
    public boolean assertErrorMessage(String expectedMessage) {
        return driver.findElement(errorMessage).getText().equals(expectedMessage);
    }
}
