package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
    SEARCH_SKIP_BUTTON ="id:org.wikipedia:id/fragment_onboarding_skip_button";
    SEARCH_INIT_ELEMENT ="xpath://*[contains(@text, 'Search Wikipedia')]";
    SEARCH_INPUT ="xpath://*[contains(@text, 'Search Wikipedia')]";
    SEARCH_INPUT_PLACEHOLDER ="id:org.wikipedia:id/search_src_text";
    SEARCH_CANCEL_BUTTON ="id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL ="xpath://*[contains(@text,'{TITLE}')]/following-sibling::*[contains(@text,'{DESCRIPTION}')]";
    SEARCH_RESULTS_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
    SEARCH_RESULTS_IN_LIST_BY_TITLE_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]";
    SEARCH_RESULT_ELEMENT ="xpath:(//*[@resource-id='org.wikipedia:id/search_container']/*[@class='android.widget.FrameLayout'])[position()>1]";
    SEARCH_MULTIPLE_RESULTS ="xpath://*[@resource-id='org.wikipedia:id/search_results_display']//*[contains(@class, 'ViewGroup')]";
    SEARCH_EMPTY_RESULT_ELEMENT ="xpath://*[@text='No results']";
    SEARCH_KEYWORD_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
    }

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
