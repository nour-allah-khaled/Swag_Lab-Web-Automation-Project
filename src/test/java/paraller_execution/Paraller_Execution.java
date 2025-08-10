package paraller_execution;

import driver_factory.DriverFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utilities.DataUtitlie;
import utilities.LogsUtility;
import utilities.Utilitie;

import java.io.IOException;
import java.time.Duration;

import static driver_factory.DriverFactory.getDriver;
import static utitie.TestData.*;
import static utitie.TestData.Expected;

public class Paraller_Execution {
    @Parameters(value = "browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("edge") String browser) throws IOException {
        DriverFactory.setDriver(browser);
        LogsUtility.info("Running on browser: " + browser);
        LogsUtility.info("Browser is opened" + browser);
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(groups = {"Valid_Scenarios"})
    public void LoginToFinsh_Scenario() {
        new Page01_Login(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Page01_Login(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Page02_Home(getDriver()).clickon_Product();
        Assert.assertTrue(new Page02_Home(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
        Page03_ProductDetails product = new Page03_ProductDetails(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        LogsUtility.info("After clicking on the add to cart button, " +
                "the cart badge increased successfully and the product appear on the cart page");
        product.clickCart();
        Page04_AddToCart addtocart = new Page04_AddToCart(getDriver());
        addtocart.click_checkout();
        Assert.assertTrue(new Page04_AddToCart(getDriver()).assert_item(Checkout));
        LogsUtility.info("User navigated successfully to the add to cart page and navigate to the Checkout page");
        new Page05_Checkout(getDriver()).First_Name(Firstname).Last_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Page05_Checkout(getDriver()).assert_URL(), overview);
        LogsUtility.info("User navigated successfully to the Checkout page " +
                "and redirect to the Overview page after clicking on the continue button");
        new Page06_OverView(getDriver()).clickOnFinshBtn();
        Assert.assertEquals(new Page06_OverView(getDriver()).assert_URL(), finish);
        LogsUtility.info("User navigated successfully to the Overview page " +
                "and redirect to the Finish page after clicking on the finish button");
        new Page07_Finish(getDriver()).clickBack();
        Assert.assertEquals(new Page07_Finish(getDriver()).assert_URL(), Home_URL);
        LogsUtility.info("The checkout complated successfully and navigated to the Home page");
    }

    @Test(groups = {"Valid_Scenarios"})
    public void LoingToHome_withSortingPrice_Scenario() {
        new Page01_Login(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Page01_Login(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Page02_Home(getDriver()).DropDown("Price (high to low)").assertProductsSorted_HighPrice();
    }

    @Test(groups = {"Valid_Scenarios"})
    public void LoginToProductDetails_Scenario() {
        new Page01_Login(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Page01_Login(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Page02_Home(getDriver()).clickon_Product();
        Assert.assertTrue(new Page02_Home(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
    }

    @Test(groups = {"Valid_Scenarios"})
    public void LoginToCart_Scenario() {
        new Page01_Login(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Page01_Login(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Page02_Home(getDriver()).DropDown("Name (A to Z)").assertProductsSorted_AtoZ();
        Page02_Home homePage = new Page02_Home(getDriver());
        homePage.clickAddToCart();
        String count = homePage.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        LogsUtility.info("After clicking on the add to cart button, " +
                "the cart badge increased successfully and the product appear on the cart page");
        homePage.clickCart();
        Assert.assertTrue(new Page02_Home(getDriver()).assert_Cart_icon(Cart));
        LogsUtility.info("User navigated successfully to the add to cart page and navigate to the Checkout page");
    }

    @Test(groups = {"Valid_Scenarios"})
    public void LoginToRemoveFromCart_Scenario() {
        new Page01_Login(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Page01_Login(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Page02_Home(getDriver()).clickon_Product();
        Assert.assertTrue(new Page02_Home(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
        Page03_ProductDetails product = new Page03_ProductDetails(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        LogsUtility.info("After clicking on the add to cart button, " +
                "the cart badge increased successfully and the product appear on the cart page");
        product.clickCart();
        new Page04_AddToCart(getDriver()).click_remove_item();
        Utilitie.waitForElementToDisappear(getDriver(), By.id("remove-sauce-labs-backpack"));
        Assert.assertFalse(new Page04_AddToCart(getDriver()).isRemoveButtonPresent(), "The Item Has beeen removed successfullly");
        LogsUtility.info("User navigated successfully to the cart and after clicking on the Remove button, the item disappear");
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        DriverFactory.quit();
    }
}
