package com.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.base.BaseTest;
import com.automation.utilities.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.awt.Desktop;
import java.io.File;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();

    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Execution Started");
    }

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(
                result.getMethod().getMethodName());

        System.out.println("Test Started: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        try {

            String base64Screenshot =
                    ScreenshotUtils.captureScreenshotBase64(
                            BaseTest.getDriver());

            test.pass(
                    "Test Passed",
                    MediaEntityBuilder
                            .createScreenCaptureFromBase64String(
                                    base64Screenshot)
                            .build());

        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("Test Passed: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try {

            String base64Screenshot =
                    ScreenshotUtils.captureScreenshotBase64(
                            BaseTest.getDriver());

            test.fail(
                    "Failure Screenshot",
                    MediaEntityBuilder
                            .createScreenCaptureFromBase64String(
                                    base64Screenshot)
                            .build());

        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("Test Failed: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.skip("Test Skipped");

        System.out.println("Test Skipped: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println("Execution Completed");

        try {

            Thread.sleep(2000);

            File reportFile = new File(
                    System.getProperty("user.dir")
                    + File.separator
                    + "reports"
                    + File.separator
                    + "ExtentReport.html");

            System.out.println(
                    "Opening Report: "
                    + reportFile.getAbsolutePath());

            Desktop.getDesktop()
                    .open(reportFile);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}