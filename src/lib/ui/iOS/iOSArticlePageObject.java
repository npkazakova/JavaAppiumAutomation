package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        DESCRIPTION = "id:Object-oriented programming language";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        SAVE_BUTTON = "xpath://*[contains(@text, 'Save')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        OPTIONS_ADD_TO_MY_LIST = "id:add-to-list";
        OPTIONS_CREATE_NEW_LIST = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        OPTIONS_INPUT_READING_LIST_TITLE = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OPTIONS_CREATE_READING_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        OPTIONS_MY_LISTS_LINK = "id:Saved. Activate to unsave.";
        //CLOSE_ARTICLE_BUTTON = "id:Back";
        NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
        SAVED_ARTICLE = "xpath://*[@text='Java (programming language)']";
        ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']";
        VIEW_LIST_BUTTON = "xpath://*[@text='View list']";
        INPUT_NAME_OF_LIST = "xpath://*[@text='Name of this list']";
        OK_BUTTON = "xpath://*[@text='OK']";
        ARTICLES_LIST_TPL = "xpath://*[contains(@name, '{SUBSTRING}')]";
        ARTICLES_NEW_LIST_TPL = "xpath://*[contains(@name,'{SUBSTRING}')]";
        ARTICLE_DESCRIPTION_TPL = "xpath://*[contains(@name,'{SUBSTRING}')]";
        OPTIONS_ARTICLE_ADDED_TO_LIST_TPL = "xpath://XCUIElementTypeStaticText[@name='Article added to '{SUBSTRING}'']]";

    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
