import org.testng.annotations.Test;

import java.io.IOException;

public class ExceptionsTests {

    @Test
    public void runTime(){
        throw new RuntimeException("Message of Exception");
    }

    @Test
    public void ioException() throws Exception{
        throw new IOException("Fail");
    }

    @Test
    public void nullExceptionPass(){
        throw  new NullPointerException("Pass");
    }

    @Test
    public void nullExceptionFail(){
        throw  new NullPointerException("Fail");
    }
}
