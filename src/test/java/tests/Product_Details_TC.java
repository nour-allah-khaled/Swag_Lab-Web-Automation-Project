package tests;
import driver_factory.DriverFactory;
import pages.Home_Page;
import pages.Login_Page;
import pages.ProductDetails_Page;
import utilities.DataUtitlie;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import listeners.TestMethodLoggerListener;
import listeners.ITestResultListener;
import utilities.LogsUtility;

import java.io.IOException;
import java.time.Duration;
import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;

@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
    })
public class Product_Details_TC {
    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(DataUtitlie.getPropertyValue("enviroments", "Browser"));
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        LogsUtility.info("User logged in successfully");
        new Home_Page(getDriver()).clickon_Product();
    }
    @Test
    public void Valid_AddToCartButton() {
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        product.clickCart();
    }
    @Test
    public void Valid_RemovefromCart() {
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        product.clickRemove();
        String CountafterRemove = product.getCartBadgeNumber();
        Assert.assertTrue(CountafterRemove.equals("") || CountafterRemove.equals("0"));
    }
    @Test
    public void Valid_Remove()
    {
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        String buttonText = product.getRemoveButtonText();
        Assert.assertEquals(buttonText, "Remove");
    }
    @Test
    public void Click_Cart_icon(){
        new ProductDetails_Page(getDriver()).clickCart();
        Assert.assertTrue(new ProductDetails_Page(getDriver()).assertCart(Cart_URL));
    }
    @Test
    public void Click_pre_page(){
        new ProductDetails_Page(getDriver()).Back();
        Assert.assertTrue(new ProductDetails_Page(getDriver()).assertCart(Home_URL));
    }
    @AfterMethod
    public void quit() {
      DriverFactory.quit();
    }
}

