import org.testng.annotations.*;

public class BaseClass {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Base Before Test method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Base After Class method");
    }

    @Test
    public void testBase() {
        System.out.println("My name is name");
    }
}
