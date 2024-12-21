package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_Apply_BasePage_1 {
    WebDriver driver;
    String emailAddress;
    BasePage basePage;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        basePage = new BasePage();

        emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        basePage.openPageUrl(driver, "http://localhost/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", "123@456#%*");
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void TC_03_Register_Success() {
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");

        basePage.clickToElement(driver, "//a[@class='ico-logout']");
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
        basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error']//li"), "Your registration completed");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(basePage.getElementText(driver, "//span[@class='field-validation-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("654321");

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
