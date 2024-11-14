package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject
{
    private static final String
            DEFAULT_LIST = "//*[@resource-id='org.wikipedia:id/item_description'][@text='Default list for your saved articles']";


    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickDefaultList()
    {
        this.waitForElementAndClick(
                By.xpath(DEFAULT_LIST),
                "Cannot press navigation button to 'Default list for your saved articles",
                5
        );
    }
}
