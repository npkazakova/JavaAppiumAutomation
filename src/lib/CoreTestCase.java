package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.1");
            //capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "D:/Репозитории/JavaAppiumAutomation/apks/org.wikipedia.apk");
            //сapabilities.setCapability("app","/Users/nataliakazakova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        }
        else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:platformVersion", "18.1");
            capabilities.setCapability("appium:deviceName", "iPhone 16 Plus");
            capabilities.setCapability("appium:app", "/Users/natalia/Desktop/Wikipedia.app");
            capabilities.setCapability("appium:automationName", "XCUITest");
        } else {
            throw new Error("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }
}

