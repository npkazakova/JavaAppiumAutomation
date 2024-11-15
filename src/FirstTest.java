import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }







        // delete first article
//        MainPageObject.swipeElementToLeft(
//                By.xpath("//*[@text='Java (programming language)']"),
//                "Cannot swipe to delete saved article 'Java (programming language)'"
//        );

//        MainPageObject.waitForElementNotPresent(
//                By.xpath("//*[@text='Java (programming language)']"),
//                "Cannot delete saved article 'Java (programming language)'",
//                5
//        );

        // check the second article still saved and go to it
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='JavaScript']"),
//                "Cannot find saved article 'JavaScript'",
//                5
//        );

        // check the description of the article 'JavaScript'
//        String article_description = "pcs-edit-section-title-description";
//        MainPageObject.waitForElementPresent(
//                By.id(article_description),
//                "Cannot find article description 'JavaScript'",
//                15
//        );
//
//        MainPageObject.assertElementHasText(
//                By.id(article_description),
//                "High-level programming language",
//                "Cannot find description of the article 'JavaScript'",
//                5
//        );
//    }



}