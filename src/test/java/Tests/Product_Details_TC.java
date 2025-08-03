package Tests;
import DriverFactory.DriverFactory;
import Pages.Home_Page;
import Pages.Login_Page;
import Pages.ProductDetails_Page;
import Utities.DataUtitlie;
import Utities.LogsUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.TestMethodLoggerListener;
import Listeners.ITestResultListener;
import java.io.IOException;
import java.time.Duration;
import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setDriver;
@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
    })
public class Product_Details_TC {
    private final String username = DataUtitlie.getJsonData("ValidLogin", "username");
    private final String password = DataUtitlie.getJsonData("ValidLogin", "password");
    private final String Cart_URL = DataUtitlie.getPropertyValue("enviroments","Cart_Link");
    private final String Home_URL = DataUtitlie.getPropertyValue("enviroments","Home_URL");
    public Product_Details_TC() throws IOException {
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

