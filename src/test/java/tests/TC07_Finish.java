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

public class TC07_Finish {
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
        Page03_ProductDetails product = new Page03_ProductDetails(getDriver());
        product.clickAddToCart();
        product.clickCart();
        Page04_AddToCart addtocart = new Page04_AddToCart(getDriver());
        addtocart.click_checkout();
        new Page05_Checkout(getDriver()).First_Name(Firstname).Last_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        new Page06_OverView(getDriver()).clickOnFinshBtn();
    }
    @Test
    public void Valid_Order(){
        new Page07_Finish(getDriver()).clickBack();
        Assert.assertEquals(new Page07_Finish(getDriver()).assert_URL(),Home_URL);
    }
    @Test
    public void InValid_Order(){
        new Page07_Finish(getDriver()).clickBack();
        Assert.assertEquals(new Page07_Finish(getDriver()).assert_URL(),Invalid_page);
    }
    @AfterMethod
    public void quit()
    {
        DriverFactory.quit();
    }
}
