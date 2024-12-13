package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.SkipWelcomePageForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));;
    }

    private void SkipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    protected boolean isPlatformIOS() {
        String platform = System.getenv("PLATFORM");
        return platform != null && platform.equalsIgnoreCase("ios");
    }
}

