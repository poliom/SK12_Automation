import org.testng.Assert;
import org.testng.annotations.*;
public class MyFirstTestClass {

    @BeforeSuite
    public void testBeforeSuite(){
        System.out.println("Before Suite: Clean all DBs");
    }

    @BeforeTest(alwaysRun = true)
    public void testBeforeTest(){
        System.out.println("Before Test: Open Site URL");
    }

    @BeforeMethod
    public void testBeforeMethod(){
        System.out.println("Before Method: Verify Site is Open");
    }

    @Test(groups = "smoke")
    public  void aTestName(){
        System.out.println("My First Check");
    }

    @Test(groups = "testGroup")
    public  void exampleTestName(){
        System.out.println("My Second Check");
    }

    @AfterMethod (groups = "smoke")
    public void testAfterMethod(){
        System.out.println("After Method: Verify Check");
    }

    @AfterTest(alwaysRun = false)
    public void testAfterTest(){
        System.out.println("After Method: Close Site");
    }

    @AfterSuite(groups = "test",alwaysRun = true)
    public void testAfterSuite(){
        System.out.println("After Suite: Check DB");
    }
}
