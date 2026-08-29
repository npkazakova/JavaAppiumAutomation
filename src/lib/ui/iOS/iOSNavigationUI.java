package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
        static {
            //DEFAULT_LIST = "id:Saved";
            NAVIGATE_BACK_BUTTON = "id:Back";
            NAVIGATE_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
            NAVIGATE_SAVED_BUTTON = "id:tabbar-save";
            IOS_CLOSE_LOGIN_TO_SYNC_BUTTON = "id:Close";
    }
    public iOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
