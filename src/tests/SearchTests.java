package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "Java";
        String article_title = "Java (programming language)";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(article_title);
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "Java";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        String search_line = "Linkin Park discography";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "zgdjgdvvcs";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCompareTopicsInputPlaceholder() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();

        String topic_placeholder_text = SearchPageObject.getSearchInputText();
        assertEquals(
                "We see unexpected text!",
                "Search Wikipedia",
                topic_placeholder_text
        );
    }

    @Test
    public void testFindMultipleArticles() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "Thailand";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.assertMultipleElementsPresent();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForMultipleElementsNotPresent();
    }

    @Test
    public void testSearchResultsContainKeyword() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "Java";
        String keyword = "Java";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.verifyResultsContainKeyword(keyword);
    }

}
