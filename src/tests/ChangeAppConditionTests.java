package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class ChangeAppConditionTests extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
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
        String description_before_rotation = ArticlePageObject.getArticleDescription();

        this.rotateScreenLandscape();
        String description_after_rotation = ArticlePageObject.getArticleDescription();

        assertEquals(
                "Article description has been changed after screen rotation",
                description_before_rotation,
                description_after_rotation
        );

        this.rotateScreenPortrait();
        String description_after_second_rotation = ArticlePageObject.getArticleDescription();

        assertEquals(
                "Article description has been changed after screen rotation",
                description_before_rotation,
                description_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");

        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Java (programming language)"); // баг в приложении, поиск сбрасывается после возвращения из фона
    }
}
