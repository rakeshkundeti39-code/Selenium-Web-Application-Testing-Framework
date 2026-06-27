package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.listeners.TestListener;
import com.automation.pages.LoginPage;
import com.automation.utilities.ConfigReader;
import com.automation.utilities.ScreenshotUtils;

@Listeners(TestListener.class)
public class LoginTest1 extends BaseTest {

    @Test
    public void verifyLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void verifyWrongTitle() {

        String actualTitle = driver.getTitle();

        Assert.assertEquals(
                actualTitle,
                "Google",
                "Title Validation Failed");
    }
    
    @Test
    public void screenshotTest() throws Exception {

        Thread.sleep(3000);

        String title = driver.getTitle();

        System.out.println(title);

        String base64 =
                ScreenshotUtils.captureScreenshotBase64(driver);

        System.out.println(
                "Base64 Length: " + base64.length());

        Assert.fail("Intentional Failure");
    }
    
    @Test
    public void verifyInvalidLogin() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("invalidUsername"),
                ConfigReader.getProperty("invalidPassword"));

        String actualError =
                loginPage.getErrorMessage();

        Assert.assertEquals(
                actualError,
                "Invalid credentials");
    }
}