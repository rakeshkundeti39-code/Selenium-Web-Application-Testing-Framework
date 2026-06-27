package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By username = By.name("username");

    By password = By.name("password");

    By loginButton =
            By.xpath("//button[@type='submit']");

    By errorMessage =
            By.xpath("//p[text()='Invalid credentials']");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public void login(String userName,
                      String passWord) {

        driver.findElement(username)
                .sendKeys(userName);

        driver.findElement(password)
                .sendKeys(passWord);

        driver.findElement(loginButton)
                .click();
    }

    public String getErrorMessage() {

        return driver.findElement(errorMessage)
                .getText();
    }
}