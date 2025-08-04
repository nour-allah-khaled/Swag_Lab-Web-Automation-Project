package tests;

import driver_factory.DriverFactory;
import pages.Login_Page;
import utilities.DataUtitlie;

import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Listeners;
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
public class Login_TC {
    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(Browser);
        LogsUtility.info("Edge Driver is opened");
        getDriver().get(DataUtitlie.getPropertyValue("enviroments","LOGIN_URL"));
        LogsUtility.info("Page is Redirected to URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test(priority = 1)
    public void testLogin() {
        new Login_Page(getDriver()).userlogin(username)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
    }

    @Test(priority = 2)
    public void Invaliduser_testLogin() {
        new Login_Page(getDriver()).userlogin(invalidname)
                .passlogin(password).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
    }
    @Test(priority = 3)
    public void Invalidpass_testLogin() {
        new Login_Page(getDriver()).userlogin(username)
                .passlogin(invalidpass).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
    }
    @Test(priority = 4)
    public void empty_testLogin() {
        new Login_Page(getDriver()).userlogin(emptyname)
                .passlogin(emptypass).LoginBtn();
        Assert.assertTrue(new Login_Page(getDriver()).assertLogin(Exp_URL));
    }
    @AfterMethod
    public void quit()
    {
        DriverFactory.quit();
    }
}
