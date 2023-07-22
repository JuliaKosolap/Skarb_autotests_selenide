package org.example.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.setup.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static org.example.common.CustomLogger.logger;

public class CustomListener extends BaseTest implements ITestListener {
    protected ExtentSparkReporter htmlReporter;
    public ExtentReports extent;
    public static ExtentTest test;
    public CustomListener() {
        htmlReporter =  new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("****************************************************************************************");
        logger.info("********************    Test case: " + result.getName() + "   **************************");
        logger.info("****************************************************************************************");
        test = extent.createTest(result.getName(), "");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed" + "\n");
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test failed!");
        logger.info(result.getThrowable().getMessage() + "\n");
        test.fail("Test Failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test was skipped!");
        logger.info(result.getThrowable().getMessage() + "\n");
        test.skip("Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
