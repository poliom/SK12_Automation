import org.testng.Assert;
import org.testng.annotations.*;

public class DataProvidersTests {

    @DataProvider(name="generateText")
    public Object[][] generateText(){
        return new Object[][]{{"Letter A"}, {"Letter B"}};
    }

    @Test(dataProvider = "generateText")
    public void testMethod(String text){
        System.out.println("Passed Parameter is: " + text);
    }

    @DataProvider(name = "generateNumbers")
    public Object[][] generateNumbers() {
        return new Object[][]{{2, 3, 5}, {5, 7, 9}};
    }

    @Test(dataProvider = "generateNumbers")
    public void testDataProviderMultipleParameters(int a, int b, int expectedSum) {
        int sum = a + b;
        System.out.println("Expected sum: " + expectedSum);
        System.out.println("Actual sum: " + sum);
        Assert.assertEquals(sum, expectedSum);
    }
}
