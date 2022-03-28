package step;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;
import org.testng.TestException;

import java.util.List;


public class BaseImplementation {
    protected static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
            .getLogger(BaseImplementation.class);
    private static int timeout = 30;
    public WebDriverWait wait;
    public Actions actions;
    public static WebDriver _driver;

    public BaseImplementation() {
        _driver = DriverFactory.getDriver();
    }

    public WebElement findElement(By selector) {
        waitForElementToBeVisible(selector);
        try {
            return _driver.findElement(selector);
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element did not display: [%s] ", selector.toString()));
        }

    }

    public List<WebElement> findElements(By selector) {
        waitForElementToBeVisible(selector);
        try {
            return _driver.findElements(selector);
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element did not display: [%s] ", selector.toString()));
        }

    }

    private void waitForElementToBeVisible(By selector) {
        try {

            wait = new WebDriverWait(_driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoSuchElementException(String.format("The following element was not visible: %s", selector));
        }
    }


    public void clickElement(By selector) {
        WebElement element = findElement(selector);
        waitForElementToBeClickable(selector);
        try {
            JavascriptExecutor executor = (JavascriptExecutor) _driver;
            executor.executeScript("arguments[0].style.border='3px solid red'", element);
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }
    }

    public void waitForElementToBeClickable(By selector) {
        try {
            wait = new WebDriverWait(_driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException("The following element is not clickable: " + selector);
        }
    }

    public void hoverToThenClick(By selector) {
        WebElement element = findElement(selector);
        actions = new Actions(_driver);
        try {
            ((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", element);
            actions.moveToElement(element).perform();
            actions.click(element).perform();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
        }
    }

    public void sendKeys(By selector, String value) {
        WebElement element = findElement(selector);
        clearField(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new TestException(String.format("Error in sending [%s] to the following element: [%s]", value, selector.toString()));
        }
    }

    public void clearField(WebElement element) {
        try {
            element.clear();
            waitForElementTextToBeEmpty(element);
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }

    public void waitForElementTextToBeEmpty(WebElement element) {
        String text;
        try {
            text = element.getText();
            int maxRetries = 10;
            int retry = 0;
            while ((text.length() >= 1) || (retry < maxRetries)) {
                retry++;
                text = element.getText();
            }
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }

    }

}
