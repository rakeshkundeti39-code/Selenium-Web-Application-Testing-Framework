package com.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    // Save screenshot as file
    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        String screenshotDir =
                System.getProperty("user.dir")
                + File.separator
                + "reports"
                + File.separator
                + "screenshots";

        File folder = new File(screenshotDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        String fileName =
                testName + "_" + timeStamp + ".png";

        String destination =
                screenshotDir
                + File.separator
                + fileName;

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        try {

            Files.copy(
                    source.toPath(),
                    new File(destination).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return destination;
    }

    // Capture screenshot as Base64
    public static String captureScreenshotBase64(
            WebDriver driver) {

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BASE64);
    }
}