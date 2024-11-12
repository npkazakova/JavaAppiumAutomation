import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        //capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:/Репозитории/JavaAppiumAutomation/apks/org.wikipedia.apk");
        //сapabilities.setCapability("app","/Users/nataliakazakova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        restoreScreenOrientation(); // default orientation
        driver.quit();
    }

    // Tests
    @Test
    public void firstTest() {
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
    public void testCancelSearch() {
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
    public void testCompareArticleDescription() {
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
    public void testSwipeArticle() {
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
                "Appium",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article description",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );

//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);
    }

    @Test
    public void testCompareTopicsInputText() {
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

    @Test
    public void testFindMultipleArticles() {
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
                "Thailand",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        assertMultipleElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']//*[contains(@class, 'ViewGroup')]"),
                "Cannot find multiple items",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForMultipleElementsNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']//*[contains(@class, 'ViewGroup')]"),
                "Multiple elements are still present on the page",
                5
        );
    }

    @Test
    public void testSearchResultsContainKeyword() {
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
                "JAVA",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

         verifyResultsContainKeyword(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']" +
                        "//*[contains(@class, 'ViewGroup')][position() > 1]" +
                        "//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "java",
                "Search results do not contain the keyword ",
                15
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
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

        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        String search_result_locator = "(//*[@resource-id='org.wikipedia:id/search_container']" +
                                        "/*[@class='android.widget.FrameLayout'])[position()>1]";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );


        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator + "//*[@resource-id='org.wikipedia:id/page_list_item_title']")
        );

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
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

        String search_line = "zgdjgdvvcs";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(empty_result_label),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void saveFirstArticleToMyList () {
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

        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article description",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot press button to go back to list of articles",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot press button to go back to search page",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot press 'Saved' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_description'][@text='Default list for your saved articles']"),
                "Cannot press navigation button to 'Default list for your saved articles",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void saveTwoArticlesToMyList () {
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

        // first article 'Java (programming language)'
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' title",
                5
        );

        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article description 'Java (programming language)'",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find button to save article 'Java (programming language)'",
                5
        );

        // add first article to list
        String add_to_list_action = "org.wikipedia:id/snackbar_action";

        waitForElementAndClick(
                By.id(add_to_list_action),
                "Cannot find 'Add to list' action for the first article",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Name of this list']"),
                "Programming languages",
                "Cannot find input to set name of articles list 'Programming languages'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot press button to go back to list of articles",
                5
        );

        // second article 'JavaScript'
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' title",
                5
        );

        waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article description 'JavaScript'",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find button to save article 'JavaScript'",
                5
        );

        // add second article to list
        waitForElementAndClick(
                By.id(add_to_list_action),
                "Cannot find 'Add to list' action for the second article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Programming languages']"),
                "Cannot find 'Programming languages' list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'View list' action for the second article",
                5
        );

        // open list of articles and check that both articles are saved
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='Programming languages']"),
                "Cannot find saved list 'Programming languages'",
                5
        );

        // check that both articles are saved
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article 'Java (programming language)'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find saved article 'JavaScript'",
                5
        );

        // delete first article
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot swipe to delete saved article 'Java (programming language)'"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article 'Java (programming language)'",
                5
        );

        // check the second article still saved and go to it
        waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find saved article 'JavaScript'",
                5
        );

        // check the description of the article 'JavaScript'
        String article_description = "pcs-edit-section-title-description";
        waitForElementPresent(
                By.id(article_description),
                "Cannot find article description 'JavaScript'",
                15
        );

        assertElementHasText(
                By.id(article_description),
                "High-level programming language",
                "Cannot find description of the article 'JavaScript'",
                5
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
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

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' topic searching by " + search_line,
                15
        );

        String description_before_rotaion = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find article description",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String description_after_rotaion = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find article description",
                15
        );

        Assert.assertEquals(
                "Article description has been changed after screen rotation",
                description_before_rotaion,
                description_after_rotaion
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String description_after_second_rotaion = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find article description",
                15
        );

        Assert.assertEquals(
                "Article description has been changed after screen rotation",
                description_before_rotaion,
                description_after_second_rotaion
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
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
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' title",
                5
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find article after returning from background",
                5
        ); // баг в приложении, поиск сбрасывается после возвращения из фона
    }

    @Test
    public void testArticleHasDescription() {
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
                "Appium",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

       waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article title'",
                5
        );

        // Check that description is present on the article page
        assertElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Description is not present on the article page"
                );
    }

    // Methods
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private void assertMultipleElementsPresent(By by, String error_message, long timeoutInSeconds) {
        waitForElementPresent(by, error_message, timeoutInSeconds);
        List<WebElement> elements = driver.findElements(by);
        assertTrue(
                "Expected multiple elements, but found " + elements.size(),
                elements.size() > 1
        );
    }

    private boolean waitForMultipleElementsNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void verifyResultsContainKeyword(By by, String keyword, String error_message, long timeoutInSeconds) {
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

    private WebElement assertElementIsPresentAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement assertElementHasText(By by, String expectedText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actualText = element.getText();
        Assert.assertEquals(error_message, expectedText, actualText);
        return element;
    }

    protected void swipeUp(int timeOfSwipe)
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

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
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

    protected void swipeElementToLeft(By by, String error_message)
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

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By by, String error_message) {
        WebElement element = driver.findElement(by);
        assertNotNull(error_message, element);
    }

    private void assertElementNotPresent(By by, String error_message) {
        List<WebElement> elements = driver.findElements(by);
            WebElement element = elements.get(0);
            String elementText = element.getText();
            if (!elementText.equals("No results")) {
                String default_message = "The found element does not have the expected text 'No results'.";
                throw new AssertionError(default_message + " " + error_message);
            }

    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void restoreScreenOrientation() {
        if (driver.getOrientation() != ScreenOrientation.PORTRAIT) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }
}