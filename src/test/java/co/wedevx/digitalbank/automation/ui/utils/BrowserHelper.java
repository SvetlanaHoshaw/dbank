package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/*
 * WebDriver Helper Extension is designed to simplify based Selenium/WebDriver tests.
 * It's built on top of Selenium/WebDriver  to make your tests more readabe, reusable and
 * maintable by prvoding esay handling towards browser and adnvance identifiers.*/
public class BrowserHelper {
    //adding wait methods in one class to avoid redundancy of code

    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSec) {
//we will pass WebDriver that we are wroking with, after it will take the element that we located, and specify time to wait
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilElementClickableAndClick(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;
    }

    public WebElement fluentWaitForElementPresence(WebDriver driver, WebElement element, int maxWaitTimeInSeconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(maxWaitTimeInSeconds, TimeUnit.SECONDS)
                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException("Element not present within the specified time: " + e.getMessage());
        }
    }

    //method to scroll to element
    public static WebElement scrollIntoView(WebDriver driver, WebElement element) {
        try {
            // Use JavaScriptExecutor to scroll the web page
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
        } catch (Exception e) {
            // Handle exceptions and provide clear error messages
            throw new RuntimeException("Failed to scroll into view: " + e.getMessage());
        }
        return element;
    }

    //method to click on element with specified text
    public static void clickElementWithText(WebDriver driver, String text) {
        try {
            String xpath = "//*[contains(text(), '" + text + "')]";
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            if (elements.isEmpty()) {
                System.out.println("Element with text '" + text + "' not found.");
            } else if (elements.size() > 1) {
                System.out.println("Multiple elements found with text '" + text + "'. Clicking the first one.");
                elements.get(0).click();
            } else {
                elements.get(0).click();
            }
        } catch (Exception e) {
            System.out.println("An error occurred while clicking the element with text '" + text + "': " + e.getMessage());
        }
    }

    //method to find and to scroll to element, make sure it is interactable, clear existing value and add a new value
    public static void fillTextInput(WebDriver driver, WebElement element, String inputValue) {
        try {
            // Scroll to the text input element
            scrollIntoView(driver, element);

            // Check if the text input element is interactable (visible and enabled)
            if (element.isDisplayed() && element.isEnabled()) {
                // Clear any existing value and fill the input with the provided value
                element.clear();
                element.sendKeys(inputValue);
            } else {
                System.out.println("Text input element is not interactable.");
            }

        } catch (Exception e) {
            // Handle exceptions or provide appropriate error messages
            System.out.println("An error occurred while filling text input: " + e.getMessage());
        }
    }

}
