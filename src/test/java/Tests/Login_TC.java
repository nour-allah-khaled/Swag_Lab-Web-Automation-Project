package Tests;

import DriverFactory.DriverFactory;
import Pages.Login_Page;
import Utities.DataUtitlie;
import Utities.LogsUtility;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Listeners;
import Listeners.TestMethodLoggerListener;
import Listeners.ITestResultListener;
import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setDriver;
@Listeners({
        TestMethodLoggerListener.class,
        ITestResultListener.class
})

public class Login_TC {
   private final String username = DataUtitlie.getJsonData("ValidLogin","username");
   private final String password = DataUtitlie.getJsonData("ValidLogin","password");
   private final String invalidname = DataUtitlie.getJsonData("InValidLogin","invaliduser");
   private final String invalidpass = DataUtitlie.getJsonData("InValidLogin","invalidpassword");
   private final String emptyname = DataUtitlie.getJsonData("InValidLogin","emptyuser");
   private final String emptypass = DataUtitlie.getJsonData("InValidLogin","emptypassword");
   private final String Exp_URL = DataUtitlie.getPropertyValue("enviroments","Home_URL");

    public Login_TC() throws IOException {
    }
    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(DataUtitlie.getPropertyValue("enviroments","Browser"));
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
