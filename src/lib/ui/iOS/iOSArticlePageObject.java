package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        DESCRIPTION = "xpath://XCUIElementTypeStaticText[@name='Object-oriented programming language']";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        SAVE_BUTTON = "id:Save for later";
        //CLOSE_ARTICLE_BUTTON = "id:Back";
        NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
        SAVED_ARTICLE = "xpath://*[@text='Java (programming language)']";
        ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']";
        VIEW_LIST_BUTTON = "xpath://*[@text='View list']";
        INPUT_NAME_OF_LIST = "xpath://*[@text='Name of this list']";
        OK_BUTTON = "xpath://*[@text='OK']";
        ARTICLES_LIST_TPL = "xpath://*[contains(@name, '{SUBSTRING}')]";
        ARTICLES_NEW_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
        ARTICLE_DESCRIPTION_TPL = "xpath://*[@resource-id='pcs-edit-section-title-description'][contains(@text,'{SUBSTRING}')]";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
