package academy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    ExtentReports extentReports = ExtentReportNG.getReportObject();
    ExtentTest test;
    ThreadLocal <ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        takeSnapShot(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test Passed");
        takeSnapShot(result);
    }


    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test failure");
        extentTest.get().fail(result.getThrowable());
        takeSnapShot(result);
        extentTest.get().addScreenCaptureFromPath(takeSnapShot(result),result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeSnapShot(result);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        takeSnapShot(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }
}
