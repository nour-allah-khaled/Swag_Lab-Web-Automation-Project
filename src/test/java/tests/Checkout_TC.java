package tests;

import driver_factory.DriverFactory;
import pages.*;
import utilities.DataUtitlie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;
import listeners.TestMethodLoggerListener;
import listeners.ITestResultListener;
import utilities.LogsUtility;
@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
})
public class Checkout_TC {

    public Checkout_TC() throws IOException {
    }
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
    }
    @Test
    public void Valid_Checkout()
    {
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
    }
    @Test
    public void InValid_Firstname_Checkout()
    {
        new Checkout_Page(getDriver()).First_Name(empty_first).Lasr_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
    }
    @Test
    public void Valid_Empty_Firstname_Message()
    {
        new Checkout_Page(getDriver()).First_Name(empty_first).Lasr_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertTrue(new Checkout_Page(getDriver()).assertErrorMessage(EmFirst),EmFirst);
    }
    @Test
    public void InValid_Lastname_Checkout()
    {
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(empty_last)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
    }
    @Test
    public void Valid_Empty_Lastname_Message()
    {
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(empty_last)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertTrue(new Checkout_Page(getDriver()).assertErrorMessage(EmLast),EmLast);
    }
    @Test
    public void InValid_Postal_Checkout()
    {
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(Lastname)
                .Postal_Code(empty_postal).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
    }
    @Test
    public void Valid_Empty_Postal_Message()
    {
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(Lastname)
                .Postal_Code(empty_postal).Click_Continue();
        Assert.assertTrue(new Checkout_Page(getDriver()).assertErrorMessage(EmPostal),EmPostal);
    }
    @Test
    public void Click_Cancel()
    {
        new Checkout_Page(getDriver()).Click_Cancel();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),Cart);
    }
    @AfterMethod
    public void quit()
    {
        DriverFactory.quit();
    }
}
