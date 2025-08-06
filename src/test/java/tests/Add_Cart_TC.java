package tests;
import driver_factory.DriverFactory;
import pages.*;
import utilities.DataUtitlie;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import static driver_factory.DriverFactory.*;
import listeners.TestMethodLoggerListener;
import listeners.ITestResultListener;
import utilities.LogsUtility;
import static utitie.TestData.*;
@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
})
public class Add_Cart_TC {

    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        LogsUtility.info("User logged in successfully");
        new Home_Page(getDriver()).clickon_Product();
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        product.clickCart();
    }
    @Test
    public void Valid_Return() {
       Home_Page home = new AddToCart_Page(getDriver()).click_back();
       Assert.assertTrue(home.assert_Cart_icon(Home_URL));
    }
    @Test
    public void Invalid_Return() {
        Home_Page home = new AddToCart_Page(getDriver()).click_back();
        Assert.assertTrue(home.assert_Cart_icon(Logout),"The Page Fail to navigate to the home page");
    }
    @Test
    public void Valid_Checkout() {
        new AddToCart_Page(getDriver()).click_checkout();
        Assert.assertTrue(new AddToCart_Page(getDriver()).assert_item(Checkout));
    }
    @Test
    public void InValid_Checkout() {
        new AddToCart_Page(getDriver()).click_checkout();
        Assert.assertTrue(new AddToCart_Page(getDriver()).assert_item(Home_URL));
    }
    @Test
    public void Click_on_Product() {
        ProductDetails_Page home = new AddToCart_Page(getDriver()).click_product_detail();
        Assert.assertEquals(home.assertdetaails(),Expected);
    }
    @Test
    public void Click_on_Remove()
    {
        new AddToCart_Page(getDriver()).click_remove_item();
        Assert.assertFalse(new AddToCart_Page(getDriver()).isRemoveButtonPresent(),"The Item Has beeen removed successfullly");
    }
    @Test
    public void CartBadge_After_Remove()
    {
        new AddToCart_Page(getDriver()).click_remove_item();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")),
                ExpectedConditions.textToBe(By.className("shopping_cart_badge"), "0")
        ));
        String CountafterRemove = new AddToCart_Page(getDriver()).getCartBadgeNumber();
        System.out.println("Cart count after remove: '" + CountafterRemove + "'");
        Assert.assertTrue(CountafterRemove.trim().isEmpty() || CountafterRemove.trim().equals("0"));
    }
    @AfterMethod
    public void quit() {
       DriverFactory.quit();
    }
}
