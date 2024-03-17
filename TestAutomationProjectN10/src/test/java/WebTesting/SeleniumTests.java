package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTests {
    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
            WebDriverManager.chromedriver().setup();
    }

    @Test
    public void mySeleniumTest1(){
        ChromeDriver webDriver = new ChromeDriver();

        webDriver.get("http://training.skillo-bg.com:4300/posts/all");
        webDriver.manage().window().maximize();

        WebElement login = webDriver.findElement(new By.ByXPath("//*[@id='nav-link-login']"));

        String loginText = login.getText();
        System.out.println("This is the login text: " + loginText);
        login.click();

        webDriver.close();
    }

    @Test
    public void mySeleniumTest2(){
        ChromeDriver webDriver = new ChromeDriver();

        webDriver.get("http://training.skillo-bg.com:4300/posts/all");
        webDriver.manage().window().maximize();

        List<WebElement> images = webDriver.findElements(new By.ByXPath("//*[@class='post-feed-img']/img"));

       System.out.println("The number of images is: " + images.size());

        webDriver.close();
    }

    @Test
    public void mySeleniumTest3(){
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("http://training.skillo-bg.com:4300/posts/all");
        webDriver.manage().window().maximize();

        WebElement login = webDriver.findElement(By.id("nav-link-login"));

        String loginText = login.getText();
        System.out.println("This is the login text: " + loginText);
        login.click();

        WebElement register = webDriver.findElement(new By.ByLinkText("Register"));
        String registerText = register.getText();
        System.out.println("This is the register text: " + registerText);

        webDriver.close();
    }

    @Test
    public void mySeleniumTest4(){
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("http://training.skillo-bg.com:4300/posts/all");
        webDriver.manage().window().maximize();

        WebElement login = webDriver.findElement(By.id("nav-link-login"));

        String loginText = login.getText();
        System.out.println("This is the login text: " + loginText);
        login.click();

        WebElement usernameInput = webDriver.findElement(By.id("defaultLoginFormUsername"));
        usernameInput.sendKeys("Vidko");
        usernameInput.clear();

        webDriver.close();
    }

    @Test
    public void mySeleniumTest5(){
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("http://training.skillo-bg.com:4300/users/login");
        webDriver.manage().window().maximize();

        WebElement rememberMeCheckBox = webDriver.findElement(new By.ByXPath("//*[@class='remember-me']/input"));

        rememberMeCheckBox.click();
        boolean isRememberMeSelected = rememberMeCheckBox.isSelected();
        System.out.println("Is remember me selected: " + isRememberMeSelected);

        rememberMeCheckBox.click();
        isRememberMeSelected = rememberMeCheckBox.isSelected();
        System.out.println("Is remember me selected: " + isRememberMeSelected);
        webDriver.close();
    }

    @Test
    public void mySeleniumTest6(){
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("http://training.skillo-bg.com:4300/users/login");
        webDriver.manage().window().maximize();

        WebElement rememberMeCheckBox = webDriver.findElement(new By.ByXPath("//*[@class='remember-me']/input"));

        boolean isRememberMeDisplayed = rememberMeCheckBox.isDisplayed();

        Assert.assertEquals(isRememberMeDisplayed, false, "Remember me check box is displayed");

        //Following code will be not executed due to error above
        rememberMeCheckBox.click();
        boolean isRememberMeSelected = rememberMeCheckBox.isSelected();
        System.out.println("Is remember me selected: " + isRememberMeSelected);

        rememberMeCheckBox.click();
        isRememberMeSelected = rememberMeCheckBox.isSelected();
        System.out.println("Is remember me selected: " + isRememberMeSelected);

        webDriver.close();
    }

    @Test
    public void mySeleniumTest7(){
        ChromeDriver webDriver = new ChromeDriver();

        webDriver.get("https://www.mobile.bg/");
        webDriver.manage().window().maximize();

        Select dropDownMarka = new Select(webDriver.findElement(By.name("marka")));
        dropDownMarka.selectByValue("Lada");
        dropDownMarka.selectByVisibleText("Volvo");

        webDriver.close();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){

    }
}
