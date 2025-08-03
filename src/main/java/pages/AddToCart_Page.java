package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utilitie;

import java.time.Duration;

public class AddToCart_Page  {
    private final WebDriver driver;
    private final By back = By.cssSelector("#continue-shopping");
    private final By checkout = By.cssSelector("#checkout");
    private final By product_detail= By.className("inventory_item_name");
    private final By removeItem = By.cssSelector("#remove-sauce-labs-backpack");
    private final By cart_badge = By.className("shopping_cart_badge");
    private final By quantity = By.className("cart_quantity");
    public AddToCart_Page(WebDriver driver) {
        this.driver=driver;
    }
    public Home_Page click_back() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(back));
        driver.findElement(back).click();
        return new Home_Page(driver);
    }

    public Checkout_Page click_checkout() {
        Utilitie.clicking(driver,checkout);
        return new Checkout_Page(driver);
    }
    public ProductDetails_Page click_product_detail() {
        Utilitie.clicking(driver,product_detail);
        return new ProductDetails_Page(driver);
    }
    public void click_remove_item() {
        Utilitie.clicking(driver,removeItem);
    }
    public boolean isRemoveButtonPresent() {
        try {
            return driver.findElement(removeItem).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getCartBadgeNumber() {
        try {
            return driver.findElement(cart_badge).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
    public boolean assert_item(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
}