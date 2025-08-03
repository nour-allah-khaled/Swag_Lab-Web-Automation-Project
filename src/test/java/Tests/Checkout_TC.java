package Tests;
import DriverFactory.DriverFactory;
import Pages.*;
import Utities.DataUtitlie;
import Utities.LogsUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setDriver;
import Listeners.TestMethodLoggerListener;
import Listeners.ITestResultListener;
@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
})
public class Checkout_TC {
    private final String username = DataUtitlie.getJsonData("ValidLogin", "username");
    private final String password = DataUtitlie.getJsonData("ValidLogin", "password");
    private final String Firstname = DataUtitlie.getJsonData("Valid_Checkout_data", "firstName");
    private final String Lastname = DataUtitlie.getJsonData("Valid_Checkout_data", "LastName");
    private final String postalCode = DataUtitlie.getJsonData("Valid_Checkout_data", "Postsl");
    private final String overview = DataUtitlie.getPropertyValue("enviroments","Overview");
    private final String empty_first = DataUtitlie.getJsonData("InValid_Checkout_data", "first_Name");
    private final String empty_last = DataUtitlie.getJsonData("InValid_Checkout_data", "Last_Name");
    private final String empty_postal  = DataUtitlie.getJsonData("InValid_Checkout_data", "Postsl_num");
    private final String EmFirst= DataUtitlie.getPropertyValue("enviroments","Empty_first");
    private final String EmLast= DataUtitlie.getPropertyValue("enviroments","Empty_Last");
    private final String EmPostal= DataUtitlie.getPropertyValue("enviroments","Empty_Postal");
    private final String Cart = DataUtitlie.getPropertyValue("enviroments","Cart_Link");
    public Checkout_TC() throws IOException {
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
