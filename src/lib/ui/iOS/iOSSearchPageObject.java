package lib.ui.iOS;
import lib.ui.SearchPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSSearchPageObject extends SearchPageObject {
    static {
    //SEARCH_SKIP_LINK ="xpath://*[@name='Skip']";
    SEARCH_INIT_ELEMENT ="id:Search Wikipedia";
    SEARCH_INPUT ="id:Search Wikipedia";
    SEARCH_INPUT_PLACEHOLDER ="id:Search Wikipedia";
    SEARCH_CANCEL_BUTTON ="xpath://XCUIElementTypeStaticText[@name='Cancel']";
    SEARCH_RESULT_ELEMENT ="xpath://XCUIElementTypeStaticText";
    SEARCH_MULTIPLE_RESULTS ="xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell";
    SEARCH_EMPTY_RESULT_ELEMENT ="xpath://*[@text='No results']";
    SEARCH_NO_RESULTS ="id:No results found";

    //SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL ="id:{TITLE}:{DESCRIPTION};
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[contains(@text,'{TITLE}')]/following-sibling::*[contains(@text,'{DESCRIPTION}')]";
    SEARCH_RESULTS_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
    SEARCH_RESULTS_IN_LIST_BY_TITLE_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]";
    SEARCH_KEYWORD_BY_SUBSTRING_TPL = "xpath://*[contains(@name, '{SUBSTRING}')]";
    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
