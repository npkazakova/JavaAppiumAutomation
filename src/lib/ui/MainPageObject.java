package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void assertMultipleElementsPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        waitForElementPresent(locator, error_message, timeoutInSeconds);
        List<WebElement> elements = driver.findElements(by);
        assertTrue(
                "Expected multiple elements, but found " + elements.size(),
                elements.size() > 1
        );
    }

    public boolean waitForMultipleElementsNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void verifyResultsContainKeyword(String locator, String keyword, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        String error_message1 = "Cannot find response elements";
        waitForElementPresent(locator, error_message1, timeoutInSeconds);

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

    public WebElement assertElementIsPresentAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement assertElementHasText(String locator, String expectedText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
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
//                .press(x, start_y)
//                .waitAction(timeOfSwipe)
//                .moveTo(x, end_y)
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if(already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swipping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped > max_swipes){
                assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 5).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void swipeElementToLeft(String locator, String error_message) {

        WebElement element = waitForElementPresent(locator, error_message, 10);
        Point location = element.getLocation();
        Dimension size = element.getSize();

        int left_x = location.getX();
        int right_x = left_x + size.getWidth();
        int upper_y = location.getY();
        int lower_y = upper_y + size.getHeight();
        int middle_y = upper_y + (size.getHeight() / 2);

        int start_x = right_x - 20;
        int end_x = left_x + 20;
        int start_y = middle_y;
        int end_y = middle_y;

        this.swipe(
                new Point(start_x, start_y),
                new Point(end_x, end_y),
                Duration.ofMillis(550)
        );
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message) {

        WebElement element = this.waitForElementPresent(locator + "/..", error_message, 10);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();
        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
    }

    protected void swipe(Point start, Point end, Duration duration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        this.driver.perform(Arrays.asList(swipe));
    }


    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        assertNotNull(error_message, element);
    }

    public void assertElementNotPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        WebElement element = elements.get(0);
        String elementText = element.getText();
        if (!elementText.equals("No results")) {
            String default_message = "The found element does not have the expected text 'No results'.";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void restoreScreenOrientation() {
        if (driver.getOrientation() != ScreenOrientation.PORTRAIT) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    public void scroll(int timeOfSwipe)
    {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int centerX = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1)

                .addAction(finger.createPointerMove(Duration.ofSeconds(0),
                        PointerInput.Origin.viewport(), centerX, startY))
                .addAction(finger.createPointerDown(0))

                .addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe),
                        PointerInput.Origin.viewport(), centerX, endY))

                .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    public void scrollUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped > max_swipes){
                assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            scroll(25);
            ++already_swiped;
        }
    }

    private By getLocatorByString(String locator_with_type) {
        String[] locatorParts = locator_with_type.split(":", 2);
        String by_type = locatorParts[0];
        String locator = locatorParts[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

}
