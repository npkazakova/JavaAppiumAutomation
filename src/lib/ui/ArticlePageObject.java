package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            DESCRIPTION = "pcs-edit-section-title-description",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_BUTTON = "//*[contains(@text, 'Save')]",
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SAVED_BUTTON = "//android.widget.FrameLayout[@content-desc='Saved']",
            SAVED_ARTICLE = "//*[@text='Java (programming language)']";




    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForDescriptionElement()
    {
        return this.waitForElementPresent(By.id(DESCRIPTION), "Cannot find article description on page", 15);

    }

    public String getArticleDescription()
    {
        WebElement description_element = waitForDescriptionElement();
        return description_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20
        );
    }

    public void AddArticleSave()
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find and click button to save article",
                5
        );

        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot press button to go back to list of articles",
                5
        );

        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot press button to go back to search page",
                5
        );

        this.waitForElementAndClick(
                By.xpath(SAVED_BUTTON),
                "Cannot press 'Saved' button",
                5
        );

//        this.waitForElementAndClick(
//                By.xpath(DEFAULT_LIST),
//                "Cannot press navigation button to 'Default list for your saved articles",
//                5
//        );

//        this.swipeElementToLeft(
//                By.xpath(SAVED_ARTICLE),
//                "Cannot find saved article"
//        );

        this.waitForElementNotPresent(
                By.xpath(SAVED_ARTICLE),
                "Cannot delete saved article",
                5
        );

    }
}
