import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.1");
        //capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:/Репозитории/JavaAppiumAutomation/apks/org.wikipedia.apk");
        //сapabilities.setCapability("app","/Users/nataliakazakova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_description"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search field",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleDescription()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' title",
                5
        );

        WebElement description_element = waitForElementPresent(
            By.id("pcs-edit-section-title-description"),
                "Cannot find article description",
                15
        );

        String article_description = description_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected description!",
                "Object-oriented programming language",
                article_description
        );
    }

    @Test
    public void testCompareTopicsInputText()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement text_element = assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Cannot find topics input text",
                15
        );

        String topic_input_text = text_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected text!",
                "Search Wikipedia",
                topic_input_text
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String expectedText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actualText = element.getText();
        Assert.assertEquals(error_message, expectedText, actualText);
        return element;
    }
}

//    @Test
//    public void firstTest()
//    {
//        waitForElementByXpathAndClick(
//                "//*[contains(@text, 'Search Wikipedia')]",
//                "Cannot find 'Search Wikipedia' input",
//                5
//        );
//
//        waitForElementByXpathAndSendKeys(
//                "//*[contains(@text, 'Search…')]",
//                "Java",
//                "Cannot find search input",
//                5
//        );

//    @Test
//    public void testCancelSearch()
//    {
//        waitForElementByIdAndClick(
//                "org.wikipedia:id/search_container",
//                "Cannot find 'Search Wikipedia' input",
//                5
//
//        );
//
//        waitForElementByIdAndClick(
//                "org.wikipedia:id/search_close_btn",
//                "Cannot find X to cancel search",
//                5
//
//        );
//
//        waitForElementNotPresent(
//                "org.wikipedia:id/search_close_btn",
//                "X is still present on the page",
//                5
//        );
//    }

//    WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
//        element_to_init_search.click();
//
//        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
//                "//*[contains(@text, 'Search…')]",
//                "Cannot find search input"
//        );
//
//        element_to_enter_search_line.sendKeys("Java");
//    }

//    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.withMessage(error_message + "\n");
//        By by = By.xpath(xpath);
//        return wait.until(
//                ExpectedConditions.presenceOfElementLocated(by)
//        );
//    }


//    private WebElement waitForElementPresent(By by, String error_message)
//    {
//        return waitForElementPresent(by, error_message, 5);
//    }


//    private WebElement waitForElementPresentByXpath(String xpath, String error_message)
//    {
//        return waitForElementPresent(xpath, error_message, 5);
//    }

//    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
//    {
//        WebElement element =  waitForElementPresent(xpath, error_message, timeoutInSeconds);
//        element.click();
//        return element;
//    }

//    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.withMessage(error_message + "\n");
//        By by = By.id(id);
//        return wait.until(
//                ExpectedConditions.presenceOfElementLocated(by)
//        );
//    }
//
//    private WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds)
//    {
//        WebElement element =  waitForElementPresentById(id, error_message, timeoutInSeconds);
//        element.click();
//        return element;
//    }

//    private boolean waitForElementNotPresent(String id, String error_message, long timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//                wait.withMessage(error_message + "/n");
//                By by = By.id(id);
//                return wait.until(
//                        ExpectedConditions.invisibilityOfElementLocated(by)
//                );
//    }

//    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.withMessage(error_message + "/n");
//        return wait.until(
//                ExpectedConditions.invisibilityOfElementLocated(by)
//        );
//    }

