package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{

    public static final String
            SEARCH_SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT_PLACEHOLDER = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[contains(@text,'{TITLE}')]/following-sibling::*[contains(@text,'{DESCRIPTION}')]",
            SEARCH_RESULTS_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULTS_IN_LIST_BY_TITLE_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]",
            SEARCH_RESULT_ELEMENT = "(//*[@resource-id='org.wikipedia:id/search_container']/*[@class='android.widget.FrameLayout'])[position()>1]",
            SEARCH_MULTIPLE_RESULTS = "//*[@resource-id='org.wikipedia:id/search_results_display']//*[contains(@class, 'ViewGroup')]",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            SEARCH_KEYWORD_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULTS_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultsContainKeyword(String substring)
    {
        return SEARCH_KEYWORD_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultsSearchElementInListByTitle(String title)
    {
        return SEARCH_RESULTS_IN_LIST_BY_TITLE_BY_SUBSTRING_TPL.replace("{TITLE}", title);
    }

    private static String getResultsSearchElementByTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void clickSkipButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_SKIP_BUTTON), "Cannot find 'Skip' button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public String getSearchInputText()
    {
        this.waitForElementPresent(By.id(SEARCH_INPUT_PLACEHOLDER), "Cannot find search input field", 15);
        return this.waitForElementAndGetAttribute(By.id(SEARCH_INPUT_PLACEHOLDER), "text", "Cannot find placeholder", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find X button to cancel search", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "X button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click X button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 15);
    }

    public void assertMultipleElementsPresent()
    {
        this.assertMultipleElementsPresent(By.xpath(SEARCH_MULTIPLE_RESULTS), "Cannot find multiple search results", 15);
    }

    public void waitForMultipleElementsNotPresent()
    {
        this.waitForMultipleElementsNotPresent(By.xpath(SEARCH_MULTIPLE_RESULTS), "Search results are still present", 15);
    }

    public void verifyResultsContainKeyword(String substring)
    {
        String keyword_result_xpath = getResultsContainKeyword(substring);
        this.waitForElementPresent(By.xpath(keyword_result_xpath), "Cannot find search results with keyword " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),"Cannot find anything by the request ",15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void getSearchElementInListByTitle(String title)
    {
        String search_result_xpath = getResultsSearchElementInListByTitle(title);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with title " + title, 15);
    }

    public void getArticleInListNotPresentByTitle(String title)
    {
        String search_result_xpath = getResultsSearchElementInListByTitle(title);
        this.waitForElementNotPresent(By.xpath(search_result_xpath), "Search result with title " + title + " is still present", 15);
    }

    public void clickByArticleWithTitle(String title)
    {
        String search_result_xpath = getResultsSearchElementInListByTitle(title);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with title " + title, 5);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultsSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with title '" + title + "' and description '" + description + "'", 15);
    }
}
