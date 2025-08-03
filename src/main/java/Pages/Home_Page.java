package Pages;

import Utities.Utilitie;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Home_Page {
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
    public Home_Page(WebDriver driver) {
        this.driver = driver;
    }
    public Home_Page DropDown(String option)
    {
        Utilitie.selectDropDown(driver,dropBtn,option);
        return this;
    }
    public Home_Page assertProductsSorted_AtoZ() {
        List<WebElement> productElements = Utilitie.findElements(driver,productNames);
        List<String> actualList = new ArrayList<>();
        for (WebElement element : productElements) {
            actualList.add(element.getText());
        }
        List<String> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList);
        Assert.assertEquals(actualList, expectedList, "Products are not sorted A to Z");
        return this;
    }
    public Home_Page assertProductsSorted_ZtoA() {
        List<WebElement> productElements = Utilitie.findElements(driver,productNames);
        List<String> actualList = new ArrayList<>();
        for (WebElement element : productElements) {
            actualList.add(element.getText());
        }
        List<String> expectedList = new ArrayList<>(actualList);
        Collections.sort(expectedList, Collections.reverseOrder());
        Assert.assertEquals(actualList, expectedList, "Products are not sorted Z to A");
        return this;
    }
    public Home_Page assertProductsSorted_LowPrice() {
        List<WebElement> priceElements = Utilitie.findElements(driver, productPrices);
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price Low to High");
        return this;
    }
    public Home_Page assertProductsSorted_HighPrice() {
        List<WebElement> priceElements = Utilitie.findElements(driver, productPrices);
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());
        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price High to Low");
        return this;
    }
    public Login_Page clickHumicon() {
        Utilitie.clicking(driver, humicon);
        Utilitie.clicking(driver,LogoutBtn);
        return new Login_Page(driver);
    }
    public boolean assert_Logout(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
    public AddToCart_Page clickCart(){
        Utilitie.clicking(driver,cartBtn);
        return new AddToCart_Page(driver);
    }
    public boolean assert_Cart_icon(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
    public void clickAddToCart() {
        Utilitie.clicking(driver, addtocartBtn);
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

    public void clickRemove(){
        Utilitie.clicking(driver,remove);
    }
    public String getRemoveButtonText() {
        return driver.findElement(remove).getText();
    }

    public ProductDetails_Page clickon_Product()
    {
        Utilitie.clicking(driver,product_details);
        return new ProductDetails_Page(driver);
    }
    public boolean assertProdcut(String Expected) {
        return driver.getCurrentUrl().equals(Expected);
    }
}
