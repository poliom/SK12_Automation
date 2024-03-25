package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import factory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTestPOM {

    ChromeDriver webDriver;
    //FirefoxDriver webDriver;
    //EdgeDriver webDriver;
    //SafariDriver webDriver;
    private boolean userReg = false;

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.chromedriver().clearDriverCache().setup();
        webDriver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //webDriver = new FirefoxDriver();
        //WebDriverManager.edgedriver().setup();
        //webDriver = new EdgeDriver();
        //WebDriverManager.safaridriver().setup();
        //webDriver = new SafariDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        if (webDriver != null) {
            webDriver.close();
        }
        if(userReg == true){
            //DB call for detele user with id .....
            // Automation-[10 random numbers]-[10 random chars]-test@domain.com
        }
    }

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                //To pass the first data object the userId needs to be changed to 5508
                {"p0li0m","TGdd7EDby83jdAC", "5508"},
                //{"Ivakis","Qwerty1", "5511"}
        };
    }

    @Test(dataProvider = "getUser")
    public void loginTest(String username, String password, String userId){
        //String homeURL = "http://training.skillo-bg.com:4200/posts/all";
        //webDriver.get(homeURL);
        //String currentURL = webDriver.getCurrentUrl();
        //Assert.assertEquals(currentURL,homeURL);

        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        //WebElement loginLink = webDriver.findElement(By.id("nav-link-login"));
        //loginLink.click();
        header.clickLogin();

        //String loginURL = "http://training.skillo-bg.com:4200/users/login";

        //WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        //wait.until(ExpectedConditions.urlToBe(loginURL));

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        //WebElement usernameTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultLoginFormUsername"))));
        //usernameTextField.sendKeys(username);

        //WebElement passwordTestField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//form/input[@id='defaultLoginFormPassword']"))));
        //passwordTestField.sendKeys(password);

        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);

        //WebElement rememberMeCheckbox = webDriver.findElement(By.xpath("//*[@class='remember-me']/input[@type='checkbox']"));
        //wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        //rememberMeCheckbox.click();

        loginPage.checkRememberMe();

        //Assert.assertTrue(rememberMeCheckbox.isSelected());

        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        //WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("sign-in-button"))));
        //signInButton.click();

        loginPage.clickSignIn();

        header.clickProfile();

        //String userProfileURL = "http://training.skillo-bg.com:4200/users/" + userId;
        //wait.until(ExpectedConditions.urlToBe(userProfileURL));
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        //profilePage.isUrlLoaded();
        //profilePage.isUrlLoaded("5511");
        //Assert.assertEquals(webDriver.getCurrentUrl(),"http://training.skillo-bg.com:4200/users/" + userId);

        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");
    }
}
