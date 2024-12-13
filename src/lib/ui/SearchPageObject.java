package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject
{

    protected static String
            SEARCH_SKIP_LINK,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_INPUT_PLACEHOLDER,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_MULTIPLE_RESULTS,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_NO_RESULTS,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULTS_BY_SUBSTRING_TPL,
            SEARCH_RESULTS_IN_LIST_BY_TITLE_BY_SUBSTRING_TPL,
            SEARCH_KEYWORD_BY_SUBSTRING_TPL;

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
        this.waitForElementAndClick(SEARCH_SKIP_LINK, "Cannot find 'Skip' link", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public String getSearchInputText()
    {
        this.waitForElementPresent(SEARCH_INPUT_PLACEHOLDER, "Cannot find search input field", 15);
        return this.waitForElementAndGetAttribute(SEARCH_INPUT_PLACEHOLDER, "text", "Cannot find placeholder", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find X button to cancel search", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "X button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click X button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 15);
    }

    public void assertMultipleElementsPresent()
    {
        this.assertMultipleElementsPresent(SEARCH_MULTIPLE_RESULTS, "Cannot find multiple search results", 15);
    }

    public void waitForMultipleElementsNotPresent()
    {
        this.waitForMultipleElementsNotPresent(SEARCH_MULTIPLE_RESULTS, "Search results are still present", 15);
    }

    public void verifyResultsContainKeyword(String substring)
    {
        String keyword_result_xpath = getResultsContainKeyword(substring);
        this.waitForElementPresent(keyword_result_xpath, "Cannot find search results with keyword " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,"Cannot find anything by the request ",15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void waitForNoResultsFoundLabel()
    {
        this.waitForElementPresent(SEARCH_NO_RESULTS, "Cannot find 'No results' label", 15);
    }

    public void assertNoResultFound()
    {
        this.assertElementNotPresent(SEARCH_NO_RESULTS, "We supposed not to find any results");
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void getSearchElementInListByTitle(String title)
    {
        String search_result_xpath = getResultsSearchElementInListByTitle(title);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title, 15);
    }

    public void getArticleInListNotPresentByTitle(String title)
    {
        String search_result_xpath = getResultsSearchElementInListByTitle(title);
        this.waitForElementNotPresent(search_result_xpath, "Search result with title " + title + " is still present", 15);
    }

    public void clickByArticleWithTitle(String title)
    {
        String search_result_xpath = getResultsSearchElementInListByTitle(title);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with title " + title, 5);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultsSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title '" + title + "' and description '" + description + "'", 15);
    }
}
