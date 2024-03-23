import org.testng.Assert;
import org.testng.annotations.*;

public class ExercisesTestsN11 {
    @DataProvider(name = "generateNumbersData")
    public Object[][] generateNumbersData(){
        return new Object[][]{
                {1,1,1},
                {2,1,2},
                {0,1,0},
                {-1,-1,1},
                {2,-1,-2},
                {10,1,10},
                {2,3,4},
        };
    }

    @Test(dataProvider ="generateNumbersData", groups = "testGroupA")
    public void testMultiplication(int a, int b, int expectedResults){
        int actualResult = a * b;
        System.out.println("Actual sum is: " + actualResult);
        System.out.println("Expected sum is: " + expectedResults);
        Assert.assertEquals(actualResult,expectedResults, "Expected sum is: " + expectedResults +"; Actual sum is: " + actualResult);
    }

    @Test(dataProvider ="generateNumbersData", groups = "testGroupB")
    public void groupTestMultiplication(int a, int b, int expectedResults){
        int actualResult = a * b;
        System.out.println("Actual sum is: " + actualResult);
        System.out.println("Expected sum is: " + expectedResults);
        Assert.assertEquals(actualResult,expectedResults, "Expected sum is: " + expectedResults +"; Actual sum is: " + actualResult);
    }
}