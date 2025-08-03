package Tests;

import DriverFactory.DriverFactory;
import Pages.*;
import Utities.DataUtitlie;
import Utities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setDriver;

public class Finish_Tc {
    private final String username = DataUtitlie.getJsonData("ValidLogin", "username");
    private final String password = DataUtitlie.getJsonData("ValidLogin", "password");
    private final String Firstname = DataUtitlie.getJsonData("Valid_Checkout_data", "firstName");
    private final String Lastname = DataUtitlie.getJsonData("Valid_Checkout_data", "LastName");
    private final String postalCode = DataUtitlie.getJsonData("Valid_Checkout_data", "Postsl");
    private final String Home_URL = DataUtitlie.getPropertyValue("enviroments","Home_URL");
    private final String Invalid_page = DataUtitlie.getPropertyValue("enviroments","Finish");
    public Finish_Tc() throws IOException {
    }

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
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        product.clickCart();
        AddToCart_Page addtocart = new AddToCart_Page(getDriver());
        addtocart.click_checkout();
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        new OverView_Page(getDriver()).clickOnFinshBtn();
    }
    @Test
    public void Valid_Order(){
        new Finish_Page(getDriver()).clickBack();
        Assert.assertEquals(new Finish_Page(getDriver()).assert_URL(),Home_URL);
    }
    @Test
    public void InValid_Order(){
        new Finish_Page(getDriver()).clickBack();
        Assert.assertEquals(new Finish_Page(getDriver()).assert_URL(),Invalid_page);
    }
    @AfterMethod
    public void quit()
    {
        DriverFactory.quit();
    }
}
