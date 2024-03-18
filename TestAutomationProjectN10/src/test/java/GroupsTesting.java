import org.testng.Assert;
import org.testng.annotations.*;

public class GroupsTesting {

    @Test(groups = "smoke")
    public  void testName(){
        System.out.println("My 1 Check");
    }

    @Test(groups = "smoke")
    public  void testName1(){
        System.out.println("My 2 Check");
    }

    @Test(groups = "smoke")
    public  void testName2(){
        System.out.println("My 3 Check");
    }

    @Test(groups = "testGroup", priority = -3)
    public  void testNameSecond(){
        System.out.println("My First Group Check");
    }

    @Test(groups = "testGroup")
    public  void testNameSecond2(){
        System.out.println("My Second Group Check");
    }

    @Test()
    public  void testNameTest(){
        System.out.println("My test Check");
    }

    @Test()
    public  void testNametest2(){
        System.out.println("My test 1 Check");
    }
}
