package Demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class Examples {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        myTestSelenium();
    }
    public static void myTestSelenium(){
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get("http://training.skillo-bg.com:4300/posts/all");
        webDriver.manage().window().maximize();
        webDriver.close();
    }
}
