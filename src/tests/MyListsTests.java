package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveFirstArticleToMyList () {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForDescriptionElement();
        ArticlePageObject.AddArticleSave();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickDefaultList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        String article_title = "Java (programming language)";
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }
}
