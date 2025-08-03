package Pages;

import Utities.Utilitie;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ProductDetails_Page{
    private WebDriver driver;
    private final By addTocartBtn  = By.cssSelector("#add-to-cart");
    private final By back  = By.cssSelector("#back-to-products");
    private final By remove = By.cssSelector("#remove");
    private final By carticon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    public ProductDetails_Page(WebDriver driver) {
        this.driver = driver;
    }
    public ProductDetails_Page clickAddToCart() {
        Utilitie.clicking(driver, addTocartBtn);
        return new ProductDetails_Page(driver);
    }
    public String getCartBadgeNumber() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
    public ProductDetails_Page clickRemove() {
        Utilitie.clicking(driver, remove);
        return new ProductDetails_Page(driver);
    }
    public String getRemoveButtonText() {
        return driver.findElement(remove).getText();
    }
    public AddToCart_Page clickCart() {
        Utilitie.clicking(driver, carticon);
        return new AddToCart_Page(driver);
    }
    public Home_Page Back()
    {
        Utilitie.clicking(driver, back);
        return new Home_Page(driver);
    }
    public boolean assertCart(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
    public String assertdetaails() {
        return driver.getCurrentUrl();
    }
}