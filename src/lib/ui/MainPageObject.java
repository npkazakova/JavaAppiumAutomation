package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void assertMultipleElementsPresent(By by, String error_message, long timeoutInSeconds) {
        waitForElementPresent(by, error_message, timeoutInSeconds);
        List<WebElement> elements = driver.findElements(by);
        assertTrue(
                "Expected multiple elements, but found " + elements.size(),
                elements.size() > 1
        );
    }

    public boolean waitForMultipleElementsNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void verifyResultsContainKeyword(By by, String keyword, String error_message, long timeoutInSeconds) {
        String error_message1 = "Cannot find response elements";
        waitForElementPresent(by, error_message1, timeoutInSeconds);

        List<WebElement> elements = driver.findElements(by);

        if (elements.isEmpty()) {
            throw new AssertionError(error_message + ": No elements found for the given selector.");
        }
        keyword = keyword.toLowerCase();

        for (WebElement element : elements) {
            String elementText = element.getText().toLowerCase();

            assertTrue(error_message + "'" + keyword + "'", elementText.contains(keyword));
        }
    }

    public WebElement assertElementIsPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement assertElementHasText(By by, String expectedText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actualText = element.getText();
        Assert.assertEquals(error_message, expectedText, actualText);
        return element;
    }

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize(); // параметры экрана
        int x = size.width / 2; // середина экрана
        int start_y = (int) (size.height * 0.8); // 80% нижняя часть экрана
        int end_y = (int) (size.height * 0.2); // 20% верхняя часть экрана

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swipping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(By by, String error_message) {
        WebElement element = driver.findElement(by);
        assertNotNull(error_message, element);
    }

    public void assertElementNotPresent(By by, String error_message) {
        List<WebElement> elements = driver.findElements(by);
        WebElement element = elements.get(0);
        String elementText = element.getText();
        if (!elementText.equals("No results")) {
            String default_message = "The found element does not have the expected text 'No results'.";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void restoreScreenOrientation() {
        if (driver.getOrientation() != ScreenOrientation.PORTRAIT) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

}
