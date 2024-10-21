import org.junit.Test;

public class MainClassTest {
    MainClass mainClass = new MainClass();

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

    @Test
    public void testGetClassNumber() {
        int classNumber = mainClass.getClassNumber();

        if (classNumber > 45) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed. ClassNumber is less than or equal to 45. Value: " + classNumber);
        }
    }
}
