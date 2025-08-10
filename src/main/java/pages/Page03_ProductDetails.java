package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utilities.Utilitie;

public class Page03_ProductDetails {
    private WebDriver driver;
    private final By addTocartBtn  = By.cssSelector("#add-to-cart");
    private final By back  = By.cssSelector("#back-to-products");
    private final By remove = By.cssSelector("#remove");
    private final By carticon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    public Page03_ProductDetails(WebDriver driver) {
        this.driver = driver;
    }
    public String getCartBadgeNumber() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
    public String getRemoveButtonText() {
        return driver.findElement(remove).getText();
    }
    @Step("Clicking on Add to Cart Button")
    public Page03_ProductDetails clickAddToCart() {
        Utilitie.clicking(driver, addTocartBtn);
        return this;
    }
    @Step("Clicking on Remove Button")
    public Page03_ProductDetails clickRemove() {
        Utilitie.clicking(driver, remove);
        return this;
    }

    @Step("Clicking on cart icon to view cart")
    public Page04_AddToCart clickCart() {
        Utilitie.clicking(driver, carticon);
        return new Page04_AddToCart(driver);
    }
    @Step("Clicking on Back button to return to Home page")
    public Page02_Home Back()
    {
        Utilitie.clicking(driver, back);
        return new Page02_Home(driver);
    }
    public boolean assertCart(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
    public String assertdetaails() {
        return driver.getCurrentUrl();
    }
}