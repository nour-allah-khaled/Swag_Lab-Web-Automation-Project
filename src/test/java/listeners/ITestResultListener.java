package listeners;

import utilities.LogsUtility;
import utitie.AllureUtils;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListener implements ITestListener, IExecutionListener {
    public void onExecutionStart() {
        AllureUtils.cleanAllureResults();
    }
    public void onTestStart(ITestResult result) {
        LogsUtility.info("Test Case "+ result.getName()+ " started");
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtility.info("Test Case "+ result.getName()+ " passed");

    }
    public void onTestSkipped(ITestResult result) {
        LogsUtility.info("Test Case "+ result.getName()+ " skipped");

    }
}
