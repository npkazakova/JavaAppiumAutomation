package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.ScreenOrientation;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {

    protected void backgroundApp(int seconds)
    {
        //driver.runAppInBackground(seconds);
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:platformVersion", "18.1");
        capabilities.setCapability("appium:deviceName", "iPhone 16 Plus");
        capabilities.setCapability("appium:app", "/Users/natalia/Desktop/Wikipedia.app");
        capabilities.setCapability("appium:automationName", "XCUITest");


        driver = new IOSDriver(new URL(AppiumURL), capabilities);
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
//
//    protected void backgroundApp(int seconds)
//    {
//        driver.runAppInBackground(Duration.ofSeconds(seconds));
//    }
}

