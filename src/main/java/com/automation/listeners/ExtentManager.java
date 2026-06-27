package com.automation.listeners;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        System.out.println("ExtentManager Called");

        if (extent == null) {

            System.out.println("Creating Extent Report");

            File reportDir = new File("reports");

            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            String reportPath =
                    System.getProperty("user.dir")
                    + File.separator
                    + "reports"
                    + File.separator
                    + "ExtentReport.html";

            //System.out.println("Report Path: " + reportPath);

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);
        }

        return extent;
    }
}