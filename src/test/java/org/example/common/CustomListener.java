package org.example.common;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static org.example.common.CustomLogger.logger;
public class CustomListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("****************************************************************************************");
        logger.info("********************    Test case: " + result.getName() + "   **************************");
        logger.info("****************************************************************************************");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed" + "\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test failed!");
        logger.info(result.getThrowable().getMessage() + "\n");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test was skipped!");
        logger.info(result.getThrowable().getMessage() + "\n");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

}
