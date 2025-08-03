package Listeners;

import Utities.LogsUtility;
import Utities.Utilitie;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;

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
