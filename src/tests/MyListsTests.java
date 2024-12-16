package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList () {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        String search_line = "Java";
        String article_title = "Java (programming language)";

        if (!isPlatformIOS()) {
            SearchPageObject.clickSkipButton();
        }
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article_title);
        ArticlePageObject.waitForDescriptionElement();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleSaveToDefaultList();
            NavigationUI.clickDefaultList();
            MyListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            ArticlePageObject.addArticleToDefaultSaved();
            NavigationUI.goToDefaultSavedArticlesFromArticle();
            NavigationUI.closeSyncLogin();
            MyListsPageObject.swipeByArticleToDelete(article_title);
        }
    }

    @Test
    public void testSaveTwoArticlesToMyList () {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

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
        ArticlePageObject.addArticleSaveToNewList(list_name);

        SearchPageObject.clickByArticleWithSubstring(second_article_title);
        ArticlePageObject.waitForDescriptionElement();
        ArticlePageObject.addArticleToMyList(list_name);

        SearchPageObject.getSearchElementInListByTitle(first_article_title);
        SearchPageObject.getSearchElementInListByTitle(second_article_title);

        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        SearchPageObject.getArticleInListNotPresentByTitle(first_article_title);

        SearchPageObject.clickByArticleWithTitle(second_article_title);
        ArticlePageObject.waitForDescriptionElement();
        ArticlePageObject.verifyArticleDescriptionPresent(second_article_description);
    }
}
