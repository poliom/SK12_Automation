import org.testng.annotations.*;

public class ChildClass extends BaseClass{
    @BeforeMethod
    public void beforeMethodChild() {
        System.out.println("Child Before Test method");
    }

    @AfterClass
    public void afterClassChild() {
        System.out.println("Child After Class method");
    }

    @Test
    public void testChild() {
        System.out.println("My name is child name");
    }
}
