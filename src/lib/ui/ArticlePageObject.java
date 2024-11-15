package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            DESCRIPTION = "pcs-edit-section-title-description",
            ARTICLE_DESCRIPTION_TPL = "//*[@resource-id='pcs-edit-section-title-description'][contains(@text,'{SUBSTRING}')]",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_BUTTON = "//*[contains(@text, 'Save')]",
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SAVED_BUTTON = "//android.widget.FrameLayout[@content-desc='Saved']",
            SAVED_ARTICLE = "//*[@text='Java (programming language)']",
            ARTICLES_NEW_LIST_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]",
            ARTICLES_LIST_TPL = "//*[@resource-id='org.wikipedia:id/item_title'][contains(@text,'{SUBSTRING}')]",
            ADD_TO_LIST_BUTTON = "//*[@text='Add to list']",
            VIEW_LIST_BUTTON = "//*[@text='View list']",
            INPUT_NAME_OF_LIST = "//*[@text='Name of this list']",
            OK_BUTTON = "//*[@text='OK']";

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
    /* TEMPLATES METHODS */

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForDescriptionElement()
    {
        return this.waitForElementPresent(By.id(DESCRIPTION), "Cannot find article description on page", 15);

    }

    public void assertArticleDescriptionPresent()
    {
        this.assertElementPresent(By.id(DESCRIPTION), "Description is not present on the article page");
    }

    public void verifyArticleDescriptionPresent(String description)
    {
        this.assertElementPresent(By.xpath(getArticleDescription(description)), "Description is not present on the article page");
    }

    public String getArticleDescription()
    {
        WebElement description_element = waitForDescriptionElement();
        return description_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of the article",20);
    }

    public void typeArticlesListName(String listName)
    {
        this.waitForElementAndSendKeys(By.xpath(INPUT_NAME_OF_LIST), listName, "Cannot find input to set name of articles list", 5);
    }


    public void AddArticleSaveToDefaultList()
    {
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON),"Cannot find and click button to save article",5);
        this.waitForElementAndClick(By.xpath(NAVIGATE_UP_BUTTON),"Cannot press button to go back to list of articles",5);
        this.waitForElementAndClick(By.xpath(NAVIGATE_UP_BUTTON),"Cannot press button to go back to search page",5);
        this.waitForElementAndClick(By.xpath(SAVED_BUTTON),"Cannot press 'Saved' button",5);
        this.waitForElementNotPresent(By.xpath(SAVED_ARTICLE),"Cannot delete saved article",5);
    }

    public void AddArticleSaveToNewList(String listName)
    {
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON),"Cannot find and click button to save article",5);
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST_BUTTON),"Cannot find 'Add to list' button for the article",5);
        this.waitForElementPresent(By.xpath(INPUT_NAME_OF_LIST),"Cannot find input to set name of articles list",5);
        this.typeArticlesListName(listName);
        this.waitForElementAndClick(By.xpath(OK_BUTTON),"Cannot press 'OK' button",5);
        this.waitForElementAndClick(By.xpath(NAVIGATE_UP_BUTTON),"Cannot press button to go back to list of articles",5);
    }

    public void AddArticleToMyList(String listName)
    {
        this.waitForElementAndClick(By.xpath(SAVE_BUTTON),"Cannot find and click button to save article",5);
        this.waitForElementAndClick(By.xpath(ADD_TO_LIST_BUTTON),"Cannot find 'Add to list' button for the article",5);
        this.waitForElementAndClick(By.xpath(getArticlesList(listName)),"Cannot find list with name " + listName,5);
        this.waitForElementAndClick(By.xpath(VIEW_LIST_BUTTON),"Cannot press 'View list' button",5);
        this.waitForElementPresent(By.xpath(getArticlesList(listName)),"Cannot find list with name " + listName,5);
    }

}
