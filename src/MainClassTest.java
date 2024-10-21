import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass mainClass = new MainClass();

    // Test 1
    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        int actual = mainClass.getLocalNumber();

        if (actual == expected) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed. Wrong value: " + actual);
        }
    }

    // Test 2
    @Test
    public void testGetClassNumber() {
        int classNumber = mainClass.getClassNumber();

        if (classNumber > 45) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed. ClassNumber is less than or equal to 45. Value: " + classNumber);
        }
    }

    // test 3
    @Test
    public void testGetClassString() {
        String classString = mainClass.getClassString();

        if (classString.contains("hello") || classString.contains("Hello")) {
            System.out.println("Test passed");
        } else {
            Assert.fail("Test failed. The string does not contain 'hello' or 'Hello'. Actual string: " + classString);
        }
    }
}
