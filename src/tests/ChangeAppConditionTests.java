package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;

public class ChangeAppConditionTests extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


}
