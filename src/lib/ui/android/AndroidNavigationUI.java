package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        DEFAULT_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_description'][@text='Default list for your saved articles']";
    }

    public AndroidNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
