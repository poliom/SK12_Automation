import org.testng.annotations.Test;

public class PriorityTest {

    @Test(groups = "smoke", priority = 0)
    public  void testName(){
        System.out.println("My 1 Check");
    }

    @Test(groups = "smoke", priority = 1)
    public  void testName1(){
        System.out.println("My 2 Check");
    }

    @Test(groups = "smoke", priority = -1)
    public  void testName2(){
        System.out.println("My 3 Check");
    }
}
