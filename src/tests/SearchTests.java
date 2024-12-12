package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);

        String search_line = "Java";
        String article_title = "Java (programming language)";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(article_title);
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);

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

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);
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

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);

        String search_line = "zgdjgdvvcs";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCompareTopicsInputPlaceholder() {

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);
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

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);

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

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);

        String search_line = "Java";
        String keyword = "Java";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.verifyResultsContainKeyword(keyword);
    }

    @Test
    public void testSearchResultsContainSpecificArticles() {

        SearchPageObject SearchPageObject = new SearchPageObjectFactory().get(driver);

        String search_line = "Java";
        String[][] expectedResults = {
                {"Java (programming language)", "Object-oriented programming language"},
                {"JavaScript", "High-level programming language"},
                {"Java version history", "List of versions of the Java programming language"}
        };

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        for(String[] result : expectedResults) {
            String title = result[0];
            String description = result[1];
            SearchPageObject.waitForElementByTitleAndDescription(title, description);
        }
    }
}
