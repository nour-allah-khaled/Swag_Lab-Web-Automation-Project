package e2e_scenairos;

import driver_factory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.DataUtitlie;
import utilities.LogsUtility;

import java.io.IOException;
import java.time.Duration;

import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;
import static utitie.TestData.Exp_URL;

public class InValid_E2E_Scenarios {
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(groups = {"InValid_Scenarios"})
    public void InvalidLogin_Scenario() {
        new Login_Page(getDriver()).userlogin(invalidname)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User navigated successfully to the login page " +
                "and an error message is displayed for invalid name field");
    }

    @Test(groups = {"InValid_Scenarios"})
    public void Login_Emptyname_Scenario() {
        new Login_Page(getDriver()).userlogin(emptyname)
                .passlogin(emptypass).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User navigated successfully to the login page " +
                "and an error message is displayed for empty name field");
    }

    @Test(groups = {"InValid_Scenarios"})
    public void LoginToInvalidProductURL_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(InExP_product_Details), "Product Details URL does not match expected.");
        LogsUtility.info("User failed to navigate to the product details page ");
    }

    @Test(groups = {"InValid_Scenarios"})
    public void LoginToInvalidChecout_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        LogsUtility.info("After clicking on the add to cart button, " +
                "the cart badge increased successfully and the product appear on the cart page");
        product.clickCart();
        AddToCart_Page addtocart = new AddToCart_Page(getDriver());
        addtocart.click_checkout();
        Assert.assertTrue(new AddToCart_Page(getDriver()).assert_item(Checkout));
        LogsUtility.info("User navigated successfully to the add to cart page and navigate to the Checkout page");
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(empty_last)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
        LogsUtility.info("User navigated successfully to the Checkout page " +
                "and an error message is displayed for empty last name");
    }
    @Test(groups = {"InValid_Scenarios"})
    public void LoginToInvalidFinish_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
        ProductDetails_Page product = new ProductDetails_Page(getDriver());
        product.clickAddToCart();
        String count = product.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        LogsUtility.info("After clicking on the add to cart button, " +
                "the cart badge increased successfully and the product appear on the cart page");
        product.clickCart();
        AddToCart_Page addtocart = new AddToCart_Page(getDriver());
        addtocart.click_checkout();
        Assert.assertTrue(new AddToCart_Page(getDriver()).assert_item(Checkout));
        LogsUtility.info("User navigated successfully to the add to cart page and navigate to the Checkout page");
        new Checkout_Page(getDriver()).First_Name(Firstname).Lasr_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
        LogsUtility.info("User navigated successfully to the Checkout page " +
                "and redirect to the Overview page after clicking on the continue button");
        new OverView_Page(getDriver()).clickOnFinshBtn();
        Assert.assertEquals(new OverView_Page(getDriver()).assert_URL(),finish);
        LogsUtility.info("User navigated successfully to the Overview page " +
                "and redirect to the Finish page after clicking on the finish button");
        new Finish_Page(getDriver()).clickBack();
        Assert.assertEquals(new Finish_Page(getDriver()).assert_URL(),Invalid_page);
        LogsUtility.info("The checkout failed and navigated to the Invalid page");
    }
    @AfterMethod(alwaysRun = true)
    public void quit() {
        DriverFactory.quit();
        LogsUtility.info("Driver is closed");
    }
}