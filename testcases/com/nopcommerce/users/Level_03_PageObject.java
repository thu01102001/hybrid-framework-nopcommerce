package com.nopcommerce.users;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_PageObject extends BaseTest {
    WebDriver driver;
    String firstName, lastName, emailAddress, password;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    CustomerInforPageObject customerInforPage;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        firstName = "Morten";
        lastName = "Murkitt";
        emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
        password = "123456789";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://localhost/");

        homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_01_Register() {
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLink();

        //ve lai trang Home
        homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_02_Login() {
        homePage.clickToLoginLink();

        loginPage = new LoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);

        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_03_() {
        homePage.clickToMyAccountLink();

        customerInforPage = new CustomerInforPageObject(driver);

        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(), emailAddress);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
