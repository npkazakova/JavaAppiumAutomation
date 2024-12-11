package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String


        STEP_LEARN_MORE_LINK = "//*[@name='Learn more about Wikipedia']",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG = "//*[@name='Add or edit preferred languages']",
        STEP_LEARN_MORE_ABOUT_OUR_PRIVACY = "//*[@name='Learn more about our privacy policy and terms of use']",
        NEXT_LINK = "//*[@name='Next']",
        GET_STARTED_BUTTON = "//*[@name='Get started']";


    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_LINK), "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Cannot find 'New ways to explore' Text", 10);
    }

    public void waitForAddOrEditPreferredLangLink()
    {
        this.waitForElementPresent(By.xpath(STEP_ADD_OR_EDIT_PREFERRED_LANG), "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutOurPrivacyLink()
    {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_ABOUT_OUR_PRIVACY), "Cannot find 'Learn more about our privacy policy and terms of use' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(By.xpath(NEXT_LINK), "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON), "Cannot find and click 'Get started' Button", 10);
    }
}
