package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            DEFAULT_LIST,
            NAVIGATE_BACK_BUTTON,
            NAVIGATE_CANCEL_BUTTON,
            NAVIGATE_SAVED_BUTTON,
            IOS_CLOSE_LOGIN_TO_SYNC_BUTTON;


    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickDefaultList()
    {
        this.waitForElementAndClick(DEFAULT_LIST, "Cannot press navigation button to 'Default list for your saved articles", 5);
    }

    public void goToDefaultSavedArticlesFromArticle()
    {
        this.waitForElementAndClick(NAVIGATE_BACK_BUTTON, "Cannot find and click navigate back button", 5);
        this.waitForElementAndClick(NAVIGATE_CANCEL_BUTTON, "Cannot find and click navigate cancel button", 5);
        this.waitForElementAndClick(NAVIGATE_SAVED_BUTTON, "Cannot find and click navigate saved button", 5);
    }

    public void closeSyncLogin()
    {
        this.waitForElementAndClick(IOS_CLOSE_LOGIN_TO_SYNC_BUTTON, "Cannot find and click close sync login button", 5);
    }

    public void clickBackButton()
    {
        this.waitForElementAndClick(NAVIGATE_BACK_BUTTON, "Cannot find and click navigate back button", 5);
    }
}
