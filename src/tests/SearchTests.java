package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
}
