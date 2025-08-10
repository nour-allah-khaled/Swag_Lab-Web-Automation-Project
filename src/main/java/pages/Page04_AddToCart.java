package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utilitie;

import java.time.Duration;

public class Page04_AddToCart {
    private final WebDriver driver;
    private final By back = By.cssSelector("#continue-shopping");
    private final By checkout = By.cssSelector("#checkout");
    private final By product_detail= By.className("inventory_item_name");
    private final By removeItem = By.cssSelector("#remove-sauce-labs-backpack");
    private final By cart_badge = By.className("shopping_cart_badge");
    private final By quantity = By.className("cart_quantity");
    public Page04_AddToCart(WebDriver driver) {
        this.driver=driver;
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
    @Step("Clicking on Back button to return to Home page")
    public Page02_Home click_back() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(back));
        driver.findElement(back).click();
        return new Page02_Home(driver);
    }
    @Step("Clicking on Checkout button to navigate to checkout")
    public Page05_Checkout click_checkout() {
        Utilitie.clicking(driver,checkout);
        return new Page05_Checkout(driver);
    }
    @Step("Clicking on product to view product information")
    public Page03_ProductDetails click_product_detail() {
        Utilitie.clicking(driver,product_detail);
        return new Page03_ProductDetails(driver);
    }
    @Step("Clicking on the remove item button")
    public void click_remove_item() {
        Utilitie.clicking(driver,removeItem);
    }

    public boolean assert_item(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
}