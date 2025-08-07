package e2e_scenairos;

import driver_factory.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.DataUtitlie;
import utilities.LogsUtility;
import utilities.Utilitie;
import java.io.IOException;
import java.time.Duration;
import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;
public class Valid_E2E_Scenarios {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test case validates the login functionality with valid credentials")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void Login_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test case validates the login functionality with valid credentials")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoingToHome_withSortingPrice_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).DropDown("Price (high to low)").assertProductsSorted_HighPrice();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test case validates the login functionality with valid credentials and " +
            "click on the product to navigate to the product details page")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToProductDetails_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the return to the home page button on the product details page ")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToReturnHome_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User Clicked on the product and navigate to the product details page");
        new ProductDetails_Page(getDriver()).Back();
        Assert.assertTrue(new ProductDetails_Page(getDriver()).assertCart(Home_URL));
        LogsUtility.info("User clicked on the back button and navigated to the Home page");
    }
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test case validates the login functionality with valid credentials and " +
            "Navigate to the Home page and sort products by name in ascending order "
            +"click on the product to navigate to the product details page and add the product to the cart")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToCart_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).DropDown("Name (A to Z)").assertProductsSorted_AtoZ();
        Home_Page homePage = new Home_Page(getDriver());
        homePage.clickAddToCart();
        String count = homePage.getCartBadgeNumber();
        Assert.assertEquals("1", count);
        LogsUtility.info("After clicking on the add to cart button, " +
                "the cart badge increased successfully and the product appear on the cart page");
        homePage.clickCart();
        Assert.assertTrue(new Home_Page(getDriver()).assert_Cart_icon(Cart));
        LogsUtility.info("User navigated successfully to the add to cart page and navigate to the Checkout page");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test case validates the login functionality with valid credentials and " +
            "click on the product to navigate to the product details page and add the product to the cart" +
            "and then remove the product from the cart")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToRemoveFromCart_Scenario() {
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
        new AddToCart_Page(getDriver()).click_remove_item();
        Utilitie.waitForElementToDisappear(getDriver(), By.id("remove-sauce-labs-backpack"));
        Assert.assertFalse(new AddToCart_Page(getDriver()).isRemoveButtonPresent(), "The Item Has been removed successfully");
        LogsUtility.info("User navigated successfully to the cart and after clicking on the Remove button, the item disappear");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify navigating to the previous page " +
            "by clicking on the cancle button on the checkout page.")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToCancelCheckout_Scenario() {
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
        new Checkout_Page(getDriver()).Click_Cancel();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),Cart);
        LogsUtility.info("User clicked on the cancel button and navigated to the cart page");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test case validates the login functionality with valid credentials and " +
            "click on the product to navigate to the product details page, add the product to the cart, " +
            "proceed through the checkout steps, and verify redirection to the overview page.")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToOverview_Scenario() {
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
        new Checkout_Page(getDriver()).First_Name(Firstname).Last_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
        LogsUtility.info("User navigated successfully to the Checkout page " +
                "and redirect to the Overview page after clicking on the continue button");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("This test case validates the complete end-to-end purchase flow: " +
            "logging in, selecting a product, " +
            "adding it to the cart, proceeding through the checkout steps, confirming the order, " +
            "and verifying redirection to the home page.")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToFinsh_Scenario() {
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
        new Checkout_Page(getDriver()).First_Name(Firstname).Last_Name(Lastname)
                .Postal_Code(postalCode).Click_Continue();
        Assert.assertEquals(new Checkout_Page(getDriver()).assert_URL(),overview);
        LogsUtility.info("User navigated successfully to the Checkout page " +
                "and redirect to the Overview page after clicking on the continue button");
        new OverView_Page(getDriver()).clickOnFinshBtn();
        Assert.assertEquals(new OverView_Page(getDriver()).assert_URL(),finish);
        LogsUtility.info("User navigated successfully to the Overview page " +
                "and redirect to the Finish page after clicking on the finish button");
        new Finish_Page(getDriver()).clickBack();
        Assert.assertEquals(new Finish_Page(getDriver()).assert_URL(),Home_URL);
        LogsUtility.info("The checkout completed successfully and navigated to the Home page");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can logout successfully by clicking on the logout button " +
            "and is redirected to the login page.")
    @Owner("Nour Allah Khaled")
    @Test(groups = {"Valid_Scenarios"})
    public void LoginToLogout_Scenario() {
        new Login_Page(getDriver()).userlogin(username).passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickHumicon();
        Assert.assertTrue(new Home_Page(getDriver()).assert_Logout(Logout));
        LogsUtility.info("User clicked on the humburger icon is functional that a sidebar menu appears and " +
                "clicked on the logout button to navigated to the login page");
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        DriverFactory.quit();
        LogsUtility.info("Driver is closed");
    }
}