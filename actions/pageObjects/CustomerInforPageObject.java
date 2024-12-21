package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInforPageUI;

public class CustomerInforPageObject extends BasePage {
    private WebDriver driver;
    public CustomerInforPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, CustomerInforPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInforPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, CustomerInforPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInforPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, CustomerInforPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerInforPageUI.EMAIL_TEXTBOX, "value");
    }
}
