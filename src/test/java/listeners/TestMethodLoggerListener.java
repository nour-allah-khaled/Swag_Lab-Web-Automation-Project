package listeners;


import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utilities.LogsUtility;
import utilities.Utilitie;

import static driver_factory.DriverFactory.getDriver;

public class TestMethodLoggerListener implements IInvokedMethodListener{

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if(testResult.getStatus() == ITestResult.FAILURE) {
            LogsUtility.info("Test Case "+ testResult.getName()+ " failed");
            Utilitie.Screen_shot(getDriver(),testResult.getName());
        }
    }

}
