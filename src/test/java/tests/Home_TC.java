package tests;

import driver_factory.DriverFactory;
import pages.Home_Page;
import pages.Login_Page;
import utilities.DataUtitlie;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import listeners.TestMethodLoggerListener;
import listeners.ITestResultListener;
import utilities.LogsUtility;

import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;

@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
})
public class Home_TC {
    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments","LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        LogsUtility.info("User logged in successfully");
    }
    @Test
    public void Valid_Sort_AtoZ() {
        new Home_Page(getDriver()).DropDown("Name (A to Z)").assertProductsSorted_AtoZ();
    }
    @Test
    public void Valid_Sort_ZtoA() {
        new Home_Page(getDriver()).DropDown("Name (Z to A)").assertProductsSorted_ZtoA();
    }
    @Test
    public void Valid_Sort_LowToHigh() {
        new Home_Page(getDriver()).DropDown("Price (low to high)").assertProductsSorted_LowPrice();
    }
    @Test
    public void Valid_Sort_HighToLow() {
        new Home_Page(getDriver()).DropDown("Price (high to low)").assertProductsSorted_HighPrice();
    }
    @Test
    public void Valid_product_details() {
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
    }
    @Test
    public void INValid_product_details() {
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(InExP_product_Details), "Product Details URL does not match expected.");
    }
    @Test
    public void Valid_Navigate_Cart() {
        new Home_Page(getDriver()).clickCart();
        Assert.assertTrue(new Home_Page(getDriver()).assert_Cart_icon(Cart));
    }
    @Test
    public void Valid_AddToCartButton() {
        Home_Page homePage = new Home_Page(getDriver());
        homePage.clickAddToCart();
        String count = homePage.getCartBadgeNumber();
        Assert.assertEquals("1", count);
    }
    @Test
    public void Valid_RemovefromCart() {
        Home_Page homePage = new Home_Page(getDriver());
        homePage.clickAddToCart();
        homePage.clickRemove();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")),
                ExpectedConditions.textToBe(By.className("shopping_cart_badge"), "0")
        ));
        String CountafterRemove = homePage.getCartBadgeNumber();
        System.out.println("Cart count after remove: '" + CountafterRemove + "'");
        Assert.assertTrue(CountafterRemove.trim().isEmpty() || CountafterRemove.trim().equals("0"));
    }
    @Test
    public void Valid_RemoveButton() {
        Home_Page homePage = new Home_Page(getDriver());
        homePage.clickAddToCart();
        String buttonText = homePage.getRemoveButtonText();
        Assert.assertEquals(buttonText, "Remove");
    }
    @Test
    public void Logout() {
        new Home_Page(getDriver()).clickHumicon();
        Assert.assertTrue(new Home_Page(getDriver()).assert_Logout(Logout));
    }
    @AfterMethod
    public void quit()
    {
      DriverFactory.quit();
    }
}
