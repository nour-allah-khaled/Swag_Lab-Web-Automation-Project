package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Login_Page {
    private final WebDriver driver;
    private final By username = By.cssSelector("#user-name");
    private final By password = By.cssSelector("#password");
    private final By loginbtn = By.cssSelector("#login-button");

    public Login_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Login_Page userlogin(String user) {
        Utilitie.sendKeys(driver, username, user);
        return this;
    }

    public Login_Page passlogin(String pass) {
        Utilitie.sendKeys(driver, password, pass);
        return this;
    }

    public Home_Page LoginBtn() {
        Utilitie.clicking(driver, loginbtn);
        return new Home_Page(driver);
    }

    public boolean assertLogin(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
}
