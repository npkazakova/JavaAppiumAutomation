package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList () {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        String search_line = "Java";
        String article_title = "Java (programming language)";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article_title);
        ArticlePageObject.waitForDescriptionElement();

        ArticlePageObject.AddArticleSaveToDefaultList();
        NavigationUI.clickDefaultList();
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList () {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        String search_line = "Java";
        String first_article_title = "Java (programming language)";
        String second_article_title = "JavaScript";
        String second_article_description = "High-level programming language";
        String list_name = "Programming languages";

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(first_article_title);
        ArticlePageObject.waitForDescriptionElement();
        ArticlePageObject.AddArticleSaveToNewList(list_name);

        SearchPageObject.clickByArticleWithSubstring(second_article_title);
        ArticlePageObject.waitForDescriptionElement();
        ArticlePageObject.AddArticleToMyList(list_name);

        SearchPageObject.getSearchElementInListByTitle(first_article_title);
        SearchPageObject.getSearchElementInListByTitle(second_article_title);

        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        SearchPageObject.getArticleInListNotPresentByTitle(first_article_title);

        SearchPageObject.clickByArticleWithTitle(second_article_title);
        ArticlePageObject.waitForDescriptionElement();
        ArticlePageObject.verifyArticleDescriptionPresent(second_article_description);
    }
}
