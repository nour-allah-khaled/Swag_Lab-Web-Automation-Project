package tests;
import driver_factory.DriverFactory;
import pages.Page02_Home;
import pages.Page01_Login;
import pages.Page03_ProductDetails;
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
public class TC03_Product_Details {
    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new Page01_Login(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        LogsUtility.info("User logged in successfully");
        new Page02_Home(getDriver()).clickon_Product();
    }
    @Test
    public void Valid_AddToCartButton() {
        Page03_ProductDetails product = new Page03_ProductDetails(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        product.clickCart();
    }
    @Test
    public void Valid_RemovefromCart() {
        Page03_ProductDetails product = new Page03_ProductDetails(getDriver());
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
        Page03_ProductDetails product = new Page03_ProductDetails(getDriver());
        product.clickAddToCart();
        String buttonText = product.getRemoveButtonText();
        Assert.assertEquals(buttonText, "Remove");
    }
    @Test
    public void Click_Cart_icon(){
        new Page03_ProductDetails(getDriver()).clickCart();
        Assert.assertTrue(new Page03_ProductDetails(getDriver()).assertCart(Cart_URL));
    }
    @Test
    public void Click_pre_page(){
        new Page03_ProductDetails(getDriver()).Back();
        Assert.assertTrue(new Page03_ProductDetails(getDriver()).assertCart(Home_URL));
    }
    @AfterMethod
    public void quit() {
      DriverFactory.quit();
    }
}

