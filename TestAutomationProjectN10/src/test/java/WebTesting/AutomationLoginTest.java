package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AutomationLoginTest {

    //ChromeDriver webDriver;
    //FirefoxDriver webDriver;
    EdgeDriver webDriver;
    //SafariDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(){
        //WebDriverManager.chromedriver().setup();
        //webDriver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //webDriver = new FirefoxDriver();
        WebDriverManager.edgedriver().setup();
        webDriver = new EdgeDriver();
        //WebDriverManager.safaridriver().setup();
        //webDriver = new SafariDriver();

        webDriver.manage().window().maximize();
    }

    @Test
    public void loginTest(){
        String homeURL = "http://training.skillo-bg.com:4200/posts/all";
        webDriver.get(homeURL);

        String currentURL = webDriver.getCurrentUrl();
        Assert.assertEquals(currentURL,homeURL);

        WebElement loginLink = webDriver.findElement(By.id("nav-link-login"));
        loginLink.click();

        String loginURL = "http://training.skillo-bg.com:4200/users/login";
        String loginCurrentURL = webDriver.getCurrentUrl();
        Assert.assertEquals(loginCurrentURL,loginURL);

        WebElement usernameTextField = webDriver.findElement(By.id("defaultLoginFormUsername"));
        usernameTextField.sendKeys("p0li0m");
        //String typedUserName = usernameTextField.getText();
        //Assert.assertEquals(typedUserName,"p0li0m");

        WebElement passwordTestField = webDriver.findElement(By.xpath("//form/input[@id='defaultLoginFormPassword']"));
        passwordTestField.sendKeys("TGdd7EDby83jdAC");

        WebElement rememberMeCheckbox = webDriver.findElement(By.xpath("//*[@class='remember-me']/input[@type='checkbox']"));
        rememberMeCheckbox.click();
        //rememberMeCheckbox.click();
        Assert.assertTrue(rememberMeCheckbox.isSelected());

        WebElement signInButton = webDriver.findElement(By.id("sign-in-button"));
        Assert.assertTrue(signInButton.isEnabled(), "The Sign In Button is disabled");
        signInButton.click();

        WebElement profilePageLink = webDriver.findElement(By.id("nav-link-profile"));
        profilePageLink.click();

        Assert.assertEquals(webDriver.getCurrentUrl(),"http://training.skillo-bg.com:4200/users/5508");
    }

    @Test
    public void homePageScrollTest() {
        String homeURL = "http://training.skillo-bg.com:4200/posts/all";
        webDriver.get(homeURL);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,2750)", "");

        List<WebElement> numberOfImages = webDriver.findElements(By.xpath("//*[@class='post-feed-img']"));
        int numberOfLoadedImage = numberOfImages.size();
        System.out.println("The number of loaded images is: " + numberOfLoadedImage);
        Assert.assertNotEquals(numberOfLoadedImage,3);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        webDriver.close();
    }

}
