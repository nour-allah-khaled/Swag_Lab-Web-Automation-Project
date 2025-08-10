package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Page01_Login {
    private final WebDriver driver;
    private final By username = By.cssSelector("#user-name");
    private final By password = By.cssSelector("#password");
    private final By loginbtn = By.cssSelector("#login-button");

    public Page01_Login(WebDriver driver) {
        this.driver = driver;
    }
    @Step("User login with username: {user}")
    public Page01_Login userlogin(String user) {
        Utilitie.sendKeys(driver, username, user);
        return this;
    }
    @Step("User login with password: {pass}")
    public Page01_Login passlogin(String pass) {
        Utilitie.sendKeys(driver, password, pass);
        return this;
    }
    @Step("Clicking on login button")
    public Page02_Home LoginBtn() {
        Utilitie.clicking(driver, loginbtn);
        return new Page02_Home(driver);
    }

    public boolean assertLogin(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
}
