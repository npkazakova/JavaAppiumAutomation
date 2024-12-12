package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String


        STEP_LEARN_MORE_LINK = "xpath://*[@name='Learn more about Wikipedia']",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG = "xpath://*[@name='Add or edit preferred languages']",
        STEP_LEARN_MORE_ABOUT_OUR_PRIVACY = "xpath://*[@name='Learn more about our privacy policy and terms of use']",
        NEXT_LINK = "xpath://*[@name='Next']",
        GET_STARTED_BUTTON = "xpath://*[@name='Get started']",
        SKIP = "xpath://*[@name='Skip']";


    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' Text", 10);
    }

    public void waitForAddOrEditPreferredLangLink()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutOurPrivacyLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_OUR_PRIVACY, "Cannot find 'Learn more about our privacy policy and terms of use' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' Button", 10);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 5);
    }
}
