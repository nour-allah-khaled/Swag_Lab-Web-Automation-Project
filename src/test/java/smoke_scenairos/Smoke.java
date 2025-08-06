package smoke_scenairos;

import driver_factory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home_Page;
import pages.Login_Page;
import utilities.DataUtitlie;
import utilities.LogsUtility;

import java.io.IOException;
import java.time.Duration;

import static driver_factory.DriverFactory.getDriver;
import static driver_factory.DriverFactory.setDriver;
import static utitie.TestData.*;
import static utitie.TestData.Exp_URL;

public class Smoke {
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments", "LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test(groups = "Smoke")
    public void ValidLogin_Scenario() {
        new Login_Page(getDriver()).userlogin(username)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
    }
    @Test(groups = "Smoke")
    public void ValidNavigateToProduct_Scenario() {
        new Login_Page(getDriver()).userlogin(username)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickon_Product();
        Assert.assertTrue(new Home_Page(getDriver()).assertProdcut(Expected), "Product Details URL matches expected.");
        LogsUtility.info("User navigated successfully to the product details page");
    }
    @Test(groups = "Smoke")
    public void ValidSortingByPrice_Scenario() {
        new Login_Page(getDriver()).userlogin(username)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).DropDown("Price (low to high)").assertProductsSorted_LowPrice();
        LogsUtility.info("Products are sorted by price from low to high successfully");
    }
    @Test(groups = "Smoke")
    public void ValidLogout_Scenario() {
        new Login_Page(getDriver()).userlogin(username)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
        LogsUtility.info("User logged in successfully and navigated to the Home page");
        new Home_Page(getDriver()).clickHumicon();
        Assert.assertTrue(new Home_Page(getDriver()).assert_Logout(Logout));
    }
    @AfterMethod(alwaysRun = true)
    public void quit() {
        DriverFactory.quit();
        LogsUtility.info("Driver is closed");
    }
}
