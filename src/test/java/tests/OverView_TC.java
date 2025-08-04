package tests;

import driver_factory.DriverFactory;
import pages.*;
import utilities.DataUtitlie;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.LogsUtility;

import java.io.IOException;
import java.time.Duration;

import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;

public class OverView_TC {
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
        AddToCart_Page addtocart = new AddToCart_Page(getDriver());
        addtocart.click_checkout();
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(Lastname).Postal_Code(postalCode).Click_Continue();
    }
    @Test
    public void Clickon_Finish()
    {
        new OverView_Page(getDriver()).clickOnFinshBtn();
        Assert.assertEquals(new OverView_Page(getDriver()).assert_URL(),finish);
    }
    @Test
    public void Clickon_Cancle()
    {
        new OverView_Page(getDriver()).clickOnCancleBtn();
        Assert.assertEquals(new OverView_Page(getDriver()).assert_URL(), Home_URL);
    }
    @Test
    public void Invalid_Clickon_Cancle()
    {
        new OverView_Page(getDriver()).clickOnCancleBtn();
        Assert.assertEquals(new OverView_Page(getDriver()).assert_URL(), Cancle);
    }
    @AfterMethod
    public void quit()
    {
        DriverFactory.quit();
    }
}
