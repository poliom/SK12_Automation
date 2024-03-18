import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionTests {
    @Test
    public void testHardAssertions() {
        Assert.assertTrue(3 > 2);
        Assert.assertEquals("Text", "Text1");
        Assert.assertEquals("Text2", "Text1");
    }

    @Test
    public void testSoftAssertions() {
        // Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        // Add all assertions
        softAssert.assertTrue(6 == 6);
        softAssert.assertFalse(6 < 5);

        // Validate all collected assertions
        softAssert.assertAll(); // If any assertion fails – this is the line where the code will stop its execution.
    }
}
