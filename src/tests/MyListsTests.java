package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList () {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
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
