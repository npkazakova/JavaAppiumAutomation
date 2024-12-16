package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

public abstract class ArticlePageObject extends MainPageObject
{
    protected static String
            DESCRIPTION,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_ADD_TO_MY_LIST,
            OPTIONS_CREATE_NEW_LIST,
            OPTIONS_INPUT_READING_LIST_TITLE,
            OPTIONS_CREATE_READING_LIST_BUTTON,
            NAVIGATE_UP_BUTTON,
            SAVED_BUTTON,
            SAVED_ARTICLE,
            ADD_TO_LIST_BUTTON,
            VIEW_LIST_BUTTON,
            INPUT_NAME_OF_LIST,
            OK_BUTTON,
            ARTICLES_LIST_TPL,
            ARTICLE_DESCRIPTION_TPL,
            ARTICLES_NEW_LIST_TPL,
            OPTIONS_ARTICLE_ADDED_TO_LIST_TPL;

    /* TEMPLATES METHODS */
    private static String getArticlesNewList(String substring)
    {
        return ARTICLES_NEW_LIST_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getArticlesList(String substring)
    {
        return ARTICLES_LIST_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getArticleDescription(String substring)
    {
        return ARTICLE_DESCRIPTION_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getArticleAddedToList(String substring)
    {
        return OPTIONS_ARTICLE_ADDED_TO_LIST_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForDescriptionElement()
    {
        return this.waitForElementPresent(DESCRIPTION, "Cannot find article description on page", 15);

    }

    public void assertArticleDescriptionPresent()
    {
        this.assertElementPresent(DESCRIPTION, "Description is not present on the article page");
    }

    public void verifyArticleDescriptionPresent(String description)
    {
        this.assertElementPresent(getArticleDescription(description), "Description is not present on the article page");
    }

    public String getArticleDescription()
    {
        WebElement decsription_element = waitForDescriptionElement();
        if (Platform.getInstance().isAndroid()) {
            return decsription_element.getAttribute("text");
        } else {
            return decsription_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else {
            this.scrollUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }
    }

    public void typeArticlesListName(String list_name)
    {
        if(Platform.getInstance().isAndroid()){
            this.waitForElementAndSendKeys(INPUT_NAME_OF_LIST, list_name, "Cannot find input to set name of articles list", 5);
        } else {
            this.waitForElementAndSendKeys(OPTIONS_INPUT_READING_LIST_TITLE, list_name, "Cannot find input to clear name of articles list", 5);
        }
    }


    public void AddArticleSaveToDefaultList()
    {
        this.waitForElementAndClick(SAVE_BUTTON,"Cannot find and click button to save article",5);
        this.waitForElementAndClick(NAVIGATE_UP_BUTTON,"Cannot press button to go back to list of articles",5);
        this.waitForElementAndClick(NAVIGATE_UP_BUTTON,"Cannot press button to go back to search page",5);
        this.waitForElementAndClick(SAVED_BUTTON,"Cannot press 'Saved' button",5);
        this.waitForElementNotPresent(SAVED_ARTICLE,"Cannot delete saved article",5);
    }

    public void AddArticleSaveToNewList(String list_name)
    {
        this.waitForElementAndClick(SAVE_BUTTON,"Cannot find and click button to save article",5);
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON,"Cannot find 'Add to list' button for the article",5);
        this.waitForElementPresent(INPUT_NAME_OF_LIST,"Cannot find input to set name of articles list",5);
        this.typeArticlesListName(list_name);
        this.waitForElementAndClick(OK_BUTTON,"Cannot press 'OK' button",5);
        this.waitForElementAndClick(NAVIGATE_UP_BUTTON,"Cannot press button to go back to list of articles",5);
    }

    public void AddArticleToMyList(String list_name)
    {
        this.waitForElementAndClick(SAVE_BUTTON,"Cannot find and click button to save article",5);
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON,"Cannot find 'Add to list' button for the article",5);
        this.waitForElementAndClick(getArticlesList(list_name),"Cannot find list with name " + list_name,5);
        this.waitForElementAndClick(VIEW_LIST_BUTTON,"Cannot press 'View list' button",5);
        this.waitForElementPresent(getArticlesList(list_name),"Cannot find list with name " + list_name,5);
    }

    public void addArticleToMySaved(String list_name)
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list",5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST,"Cannot find option to add article to reading list",5);
        this.waitForElementAndClick(OPTIONS_CREATE_NEW_LIST,"Cannot find option to create new reading list",5);
        this.waitForElementAndClick(OPTIONS_INPUT_READING_LIST_TITLE,"Cannot find input to set name of articles list",5);
        this.typeArticlesListName(list_name);
        this.waitForElementAndClick(OPTIONS_CREATE_READING_LIST_BUTTON,"Cannot press 'Create reading list' button",5);
        this.assertElementPresent(getArticleAddedToList(list_name),"Cannot find list with name " + list_name);
    }

}
