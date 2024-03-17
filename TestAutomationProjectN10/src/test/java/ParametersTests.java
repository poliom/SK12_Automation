import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTests {

    @Parameters({"user-name"})
    @Test
    public void testParameters(String name) {
        System.out.println("This is " + name);
    }

    @Parameters({"user-name"})
    @Test
    public void testParametersOptional(@Optional("guest") String name) {
        System.out.println("This is " + name);
    }
}
