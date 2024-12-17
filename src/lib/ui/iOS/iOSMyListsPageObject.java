package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        DELETE_ARTICLE_BUTTON = "id:swipe action delete";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
//XCUIElementTypeStaticText[@name="Java (programming language)"]