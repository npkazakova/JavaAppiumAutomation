import org.junit.Test;

public class MainClassTest
{
    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();

        int expected = 14;
        int actual = mainClass.getLocalNumber();

        if (actual == expected) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed. Wrong value: " + actual);
        }
    }
}
