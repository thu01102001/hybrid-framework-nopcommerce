package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    //chua cac common (reusable) function cho cac class ben Page Object

    //1 - Access modifier: public / protected / private / default
    //2 - Kieu du lieu cua ham (Data type): void / int / String / boolean / WebElement / List<WebElement>
    //      No se lien quan den cai chuc nang minh viet trong than ham
    //3 - Ten ham: dat ten co nghia theo chuc nang dang can viet
    //      Convention tuan theo chuan cua tung ngon ngu lap trinh (Java)
    //      camelCase: tu dau tien viet thuong - chu cai dau tien cua cac tu tiep theo viet hoa
    //4 - Co tham so hay ko (tuy thuoc vao chuc nang can viet)
    //5 - Kieu du lieu tra ve cho ham
    //      Neu nhu co return du lieu thi se khop voi kieu du lieu o so 2
    //      Neu nhu co return thi no la cai step cuoi cung

    //goi 1 ham static goi truc tiep tu class ma ko thong qua doi tuong
    public static BasePage getBasePage() {
        return new BasePage();
    }
    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private long LONG_TIMEOUT = 30;
    private Alert waitToAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitToAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitToAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String valueToSend) {
        waitToAlertPresence(driver).sendKeys(valueToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitToAlertPresence(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(valueToSend);
    }

    public void selectItemDropdown(WebDriver driver, String locator, String textIem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(textIem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXPath, String childXPath, String textItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(parentXPath))).click();
        sleepInSecond(2);

        List<WebElement> allItems = waitForListElementPresence(driver, childXPath);

        for (WebElement item : allItems) {
            if(item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaRGB(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
        if(!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public void unCheckToCheckboxRadio(WebDriver driver, String locator) {
        if(getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public WebDriver switchToIframe(WebDriver driver, String locator) {
        return driver.switchTo().frame(getWebElement(driver, locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver) {
        return driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {

        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }
    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }
    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }
    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }
    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }
    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }
    public void scrollToElementOnTop(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }
    public void scrollToElementOnDown(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }
    public void setAttributeInDOM(WebDriver driver,String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }
    public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }
    public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }
    public String getAttributeInDOM(WebDriver driver,String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }
    public String getElementValidationMessage(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }
    public boolean isImageLoaded(WebDriver driver,String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }
    public WebElement waitForElementVisible(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }
    public List<WebElement> waitForListElementVisible(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
    }
    public boolean waitForElementInVisible(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }
    public boolean waitForListElementInVisible(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }
    public WebElement waitForElementClickable(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locator)));
    }
    public WebElement waitForElementPresence(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitForListElementPresence(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

}
