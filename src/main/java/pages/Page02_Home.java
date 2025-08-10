package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.Utilitie;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page02_Home {
    private final WebDriver driver;
    private final By humicon = By.cssSelector("#react-burger-menu-btn");
    private final By LogoutBtn = By.cssSelector("#logout_sidebar_link");
    private final By cartBtn = By.className("shopping_cart_link");
    private final By dropBtn = By.className("product_sort_container");
    private final By addtocartBtn = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    private final By remove = By.cssSelector("#remove-sauce-labs-backpack");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By product_details = By.xpath("//img [@alt = 'Sauce Labs Backpack']");
    private final By cart_badge = By.className("shopping_cart_badge");

    public Page02_Home(WebDriver driver) {
        this.driver = driver;
    }
    public Page02_Home DropDown(String option)
    {
        Utilitie.selectDropDown(driver,dropBtn,option);
        return this;
    }
    public List<String> getProductNamesList() {
        List<WebElement> productElements = Utilitie.findElements(driver, productNames);
        List<String> actualList = new ArrayList<>();
        for (WebElement element : productElements) {
            actualList.add(element.getText());
        }
        return actualList;
    }
    public List<Double> getProductPriceList() {
        List<WebElement> priceElements = Utilitie.findElements(driver, productPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement el : priceElements) {
            String text = el.getText().replace("$", "").trim();
            double price = Double.parseDouble(text);
            prices.add(price);
        }
        return prices;
    }
    public String getCartBadgeNumber() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(cart_badge));
            return driver.findElement(cart_badge).getText().trim();
        } catch (NoSuchElementException | TimeoutException e) {
            return "";
        }
    }
    @Step("User is sorting products from A to Z from dropdown")
    public Page02_Home assertProductsSorted_AtoZ() {
        List<String> actualList = getProductNamesList();
        List<String> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        Assert.assertEquals(expectedList, actualList);
        return this;
    }
    @Step("User is sorting products from Z to A from dropdown")
    public Page02_Home assertProductsSorted_ZtoA() {
        List<String> actualList = getProductNamesList();
        List<String> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList, Collections.reverseOrder());
        Assert.assertEquals(expectedList, actualList);
        return this;
    }
    @Step("User is sorting products from low to high price from dropdown")
    public Page02_Home assertProductsSorted_LowPrice() {
        List<Double> actualPrices = getProductPriceList();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(expectedPrices, actualPrices);
        return this;
    }
    @Step("User is sorting products from high to low price from dropdown")
    public Page02_Home assertProductsSorted_HighPrice() {
        List<Double> actualPrices = getProductPriceList();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());
        Assert.assertEquals(expectedPrices, actualPrices);
        return this;
    }
    @Step("Clicking on the Humburger icon then clicking on Logout button from the sidebar")
    public Page01_Login clickHumicon() {
        Utilitie.clicking(driver, humicon);
        Utilitie.clicking(driver,LogoutBtn);
        return new Page01_Login(driver);
    }
    @Step("Clicking on the Cart icon")
    public Page04_AddToCart clickCart(){
        Utilitie.clicking(driver,cartBtn);
        return new Page04_AddToCart(driver);
    }
    public boolean assert_Cart_icon(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
    @Step("Clicking on the Add to Cart button")
    public void clickAddToCart() {
        Utilitie.clicking(driver, addtocartBtn);
    }
    @Step("Clicking on the Remove button")
    public void clickRemove(){
        Utilitie.clicking(driver,remove);
    }
    @Step("Clicking on the product image to view product details")
    public Page03_ProductDetails clickon_Product()
    {
        Utilitie.clicking(driver,product_details);
        return new Page03_ProductDetails(driver);
    }
    public String getRemoveButtonText() {
        return driver.findElement(remove).getText();
    }
    public boolean assert_Logout(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
    public boolean assertProdcut(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
}
