import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

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

//    @Test
//    public void testCompareArticleDescription() {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "Cannot find 'Skip' button",
//                5
//        );
//
//        SearchPageObject SearchPageObject = new SearchPageObject(driver);
//
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
//
//        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//        String article_description = ArticlePageObject.getArticleDescription();
//
//        assertEquals(
//                "We see unexpected description!",
//                "Object-oriented programming language",
//                article_description
//        );
//    }

//    @Test
//    public void testSwipeArticle() {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "Cannot find 'Skip' button",
//                5
//        );
//
//        SearchPageObject SearchPageObject = new SearchPageObject(driver);
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Appium");
//        SearchPageObject.clickByArticleWithSubstring("Appium");
//
//        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//        ArticlePageObject.waitForDescriptionElement();
//        ArticlePageObject.swipeToFooter();
//    }

    @Test
    public void testCompareTopicsInputText() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement text_element = MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Cannot find topics input text",
                15
        );

        String topic_input_text = text_element.getAttribute("text");

        assertEquals(
                "We see unexpected text!",
                "Search Wikipedia",
                topic_input_text
        );
    }

    @Test
    public void testFindMultipleArticles() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Thailand",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        MainPageObject.assertMultipleElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']//*[contains(@class, 'ViewGroup')]"),
                "Cannot find multiple items",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        MainPageObject.waitForMultipleElementsNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']//*[contains(@class, 'ViewGroup')]"),
                "Multiple elements are still present on the page",
                5
        );
    }

    @Test
    public void testSearchResultsContainKeyword() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "JAVA",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        MainPageObject.verifyResultsContainKeyword(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_display']" +
                        "//*[contains(@class, 'ViewGroup')][position() > 1]" +
                        "//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "java",
                "Search results do not contain the keyword ",
                15
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park discography";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        String search_result_locator = "(//*[@resource-id='org.wikipedia:id/search_container']" +
                                        "/*[@class='android.widget.FrameLayout'])[position()>1]";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );


        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator + "//*[@resource-id='org.wikipedia:id/page_list_item_title']")
        );

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "zgdjgdvvcs";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        String empty_result_label = "//*[@text='No results']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        MainPageObject.assertElementNotPresent(
                By.xpath(empty_result_label),
                "We've found some results by request " + search_line
        );
    }

//    @Test
//    public void testSaveFirstArticleToMyList () {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "Cannot find 'Skip' button",
//                5
//        );
//
//        SearchPageObject SearchPageObject = new SearchPageObject(driver);
//
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
//
//        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//        ArticlePageObject.waitForDescriptionElement();
//        ArticlePageObject.AddArticleSave();
//
//        NavigationUI NavigationUI = new NavigationUI(driver);
//        NavigationUI.clickDefaultList();
//
//        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
//        String article_title = "Java (programming language)";
//        MyListsPageObject.swipeByArticleToDelete(article_title);
//    }

    @Test
    public void testSaveTwoArticlesToMyList () {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        // first article 'Java (programming language)'
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' title",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article description 'Java (programming language)'",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find button to save article 'Java (programming language)'",
                5
        );

        // add first article to list
        String add_to_list_action = "org.wikipedia:id/snackbar_action";

        MainPageObject.waitForElementAndClick(
                By.id(add_to_list_action),
                "Cannot find 'Add to list' action for the first article",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Name of this list']"),
                "Programming languages",
                "Cannot find input to set name of articles list 'Programming languages'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot press button to go back to list of articles",
                5
        );

        // second article 'JavaScript'
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' title",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article description 'JavaScript'",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Save')]"),
                "Cannot find button to save article 'JavaScript'",
                5
        );

        // add second article to list
        MainPageObject.waitForElementAndClick(
                By.id(add_to_list_action),
                "Cannot find 'Add to list' action for the second article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Programming languages']"),
                "Cannot find 'Programming languages' list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'View list' action for the second article",
                5
        );

        // open list of articles and check that both articles are saved
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='Programming languages']"),
                "Cannot find saved list 'Programming languages'",
                5
        );

        // check that both articles are saved
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article 'Java (programming language)'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find saved article 'JavaScript'",
                5
        );

        // delete first article
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot swipe to delete saved article 'Java (programming language)'"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article 'Java (programming language)'",
                5
        );

        // check the second article still saved and go to it
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find saved article 'JavaScript'",
                5
        );

        // check the description of the article 'JavaScript'
        String article_description = "pcs-edit-section-title-description";
        MainPageObject.waitForElementPresent(
                By.id(article_description),
                "Cannot find article description 'JavaScript'",
                15
        );

        MainPageObject.assertElementHasText(
                By.id(article_description),
                "High-level programming language",
                "Cannot find description of the article 'JavaScript'",
                5
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' topic searching by " + search_line,
                15
        );

        String description_before_rotaion = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find article description",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String description_after_rotaion = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find article description",
                15
        );

        assertEquals(
                "Article description has been changed after screen rotation",
                description_before_rotaion,
                description_after_rotaion
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String description_after_second_rotaion = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find article description",
                15
        );

        assertEquals(
                "Article description has been changed after screen rotation",
                description_before_rotaion,
                description_after_second_rotaion
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

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find 'Java' title",
                5
        );

        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Java (programming language)']"),
                "Cannot find article after returning from background",
                5
        ); // баг в приложении, поиск сбрасывается после возвращения из фона
    }

    @Test
    public void testArticleHasDescription() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find 'Search Wikipedia' topics input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article title'",
                5
        );

        // Check that description is present on the article page
        MainPageObject.assertElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Description is not present on the article page"
                );
    }
}