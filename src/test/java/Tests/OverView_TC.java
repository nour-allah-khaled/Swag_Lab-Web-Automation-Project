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

public class OverView_TC {
    private final String username = DataUtitlie.getJsonData("ValidLogin", "username");
    private final String password = DataUtitlie.getJsonData("ValidLogin", "password");
    private final String Firstname = DataUtitlie.getJsonData("Valid_Checkout_data", "firstName");
    private final String Lastname = DataUtitlie.getJsonData("Valid_Checkout_data", "LastName");
    private final String postalCode = DataUtitlie.getJsonData("Valid_Checkout_data", "Postsl");
    private final String finish = DataUtitlie.getPropertyValue("enviroments","Finish");
    private final String Home = DataUtitlie.getPropertyValue("enviroments","Home_URL");
    private final String Cancle = DataUtitlie.getPropertyValue("enviroments","Checkout");
    public OverView_TC() throws IOException {
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
        Assert.assertEquals(new OverView_Page(getDriver()).assert_URL(), Home);
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
