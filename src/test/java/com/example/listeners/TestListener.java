package com.example.listeners;

import com.example.driver.DriverManager;
import com.example.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener to monitor test lifecycle and capture screenshots on failure.
 */
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("========== Starting Test Suite: " + context.getName() + " ==========");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========== Completed Test Suite: " + context.getName() + " ==========");
        System.out.println("Passed: " + context.getPassedTests().size() +
                ", Failed: " + context.getFailedTests().size() +
                ", Skipped: " + context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[TEST STARTED] " + result.getMethod().getMethodName() + " - " + result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[TEST PASSED] " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println("[TEST FAILED] " + result.getMethod().getMethodName() + " | Cause: " + result.getThrowable());
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String path = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());
            if (path != null) {
                System.out.println("Saved failure screenshot to: " + path);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[TEST SKIPPED] " + result.getMethod().getMethodName());
    }
}
