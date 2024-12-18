package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        IOS_ARTICLE_TITLE = "id:JavaScript";
        IOS_ARTICLE_DESCRIPTION = "id:Object-oriented programming language";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        SAVE_BUTTON = "xpath://*[contains(@text, 'Save')]";
        IOS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        IOS_ADD_TO_MY_LIST = "id:add-to-list";
        IOS_CREATE_NEW_LIST = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        IOS_INPUT_READING_LIST_TITLE = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        IOS_CREATE_READING_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        IOS_MY_LISTS_LINK = "id:Saved. Activate to unsave.";
        //CLOSE_ARTICLE_BUTTON = "id:Back";
        NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
        SAVED_ARTICLE = "xpath://*[@text='Java (programming language)']";
        ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']";
        VIEW_LIST_BUTTON = "xpath://*[@text='View list']";
        INPUT_NAME_OF_LIST = "xpath://*[@text='Name of this list']";
        OK_BUTTON = "xpath://*[@text='OK']";
        ARTICLES_LIST_TPL = "xpath://*[contains(@name, '{SUBSTRING}')]";
        ARTICLES_NEW_LIST_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        ANDROID_ARTICLE_DESCRIPTION_TPL = "xpath://*[contains(@name,'{SUBSTRING}')]";
        IOS_QUESTION_ADD_TO_LIST_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'to a reading list?')]";
        IOS_ARTICLE_ADDED_TO_READING_LIST_TPL = "xpath://XCUIElementTypeStaticText[@name='Article added to '{SUBSTRING}'']]";
        IOS_ARTICLES_LIST_NAME_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
