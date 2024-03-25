package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class AutomationWaitPractice {

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
                    {"p0li0m","TGdd7EDby83jdAC", "5509"},
                    //{"Ivakis","Qwerty1", "5511"}
            };
    }

        @Test(dataProvider = "getUser")
        public void loginTest(String username, String password, String userId){
            String homeURL = "http://training.skillo-bg.com:4200/posts/all";
            webDriver.get(homeURL);

            String currentURL = webDriver.getCurrentUrl();
            Assert.assertEquals(currentURL,homeURL);

            WebElement loginLink = webDriver.findElement(By.id("nav-link-login"));
            loginLink.click();

            String loginURL = "http://training.skillo-bg.com:4200/users/login";
            // String loginCurrentURL = webDriver.getCurrentUrl();
            //Assert.assertEquals(loginCurrentURL,loginURL);

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.urlToBe(loginURL));

            //String loginCurrentURL1 = webDriver.getCurrentUrl();
            // System.out.println("Expected Login URL: "+ loginURL);
            //System.out.println("First get Login URL: "+ loginCurrentURL);
            //System.out.println("After waiting get Login URL: "+ loginCurrentURL1);

            //WebElement usernameTextField = webDriver.findElement(By.id("defaultLoginFormUsername1"));
            //usernameTextField.sendKeys("p0li0m");

            //wait.until(ExpectedConditions.visibilityOf(usernameTextField)).sendKeys("p0li0m");
            //wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultLoginFormUsername")))).sendKeys("p0li0m");

            WebElement usernameTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultLoginFormUsername1"))));
            usernameTextField.sendKeys(username);

            //String typedUserName = usernameTextField.getText();
            //Assert.assertEquals(typedUserName,"p0li0m");

            WebElement passwordTestField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//form/input[@id='defaultLoginFormPassword']"))));
            passwordTestField.sendKeys(password);

            //WebDriverWait waitButtons = new WebDriverWait(webDriver, Duration.ofSeconds(30));

            WebElement rememberMeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//*[@class='remember-me']/input[@type='checkbox']"))));
            rememberMeCheckbox.click();
            //rememberMeCheckbox.click();
            Assert.assertTrue(rememberMeCheckbox.isSelected());

            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("sign-in-button"))));
            //Assert.assertTrue(signInButton.isEnabled(), "The Sign In Button is disabled");
            signInButton.click();

            WebElement profilePageLink = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("nav-link-profile"))));
            profilePageLink.click();

            String userProfileURL = "http://training.skillo-bg.com:4200/users/" + userId;
            wait.until(ExpectedConditions.urlToBe(userProfileURL));

            //to pass the test with data needs to be updated the URL to use userId as userProfileURL
            Assert.assertEquals(webDriver.getCurrentUrl(),"http://training.skillo-bg.com:4200/users/5510");
        }

        @Test
    public void registrationTest(){
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

            String homeURL = "http://training.skillo-bg.com:4200/posts/all";
            webDriver.get(homeURL);

            String currentURL = webDriver.getCurrentUrl();
            Assert.assertEquals(currentURL,homeURL);

            WebElement loginLink = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("nav-link-login"))));
            loginLink.click();

            String loginURL = "http://training.skillo-bg.com:4200/users/login";
            wait.until(ExpectedConditions.urlToBe(loginURL));

            WebElement registrationLink = wait.until(
                    ExpectedConditions.visibilityOf(
                            webDriver.findElement(By.xpath("//form//p//a[text()='Register']"))));
            registrationLink.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='text-center mb-4']")));

            WebElement usernameTextField = wait.until(
                    ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@name='username']"))));
            usernameTextField.sendKeys("testUserUser");

            WebElement emailTextField = wait.until(
                    ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@type='email']"))));
            emailTextField.sendKeys(generateRandomEmail(10,15));

            String password = generateRandomAlphabeticString(10,15);
            WebElement passwordTextField = wait.until(
                    ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultRegisterFormPassword"))));
            passwordTextField.sendKeys(password);

            WebElement confirmPasswordTextField = wait.until(
                    ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultRegisterPhonePassword"))));
            confirmPasswordTextField.sendKeys(password);

            System.out.println("Registration button click and validation of registered user to be added");
            //this.userReg = true;
        }

    @Test
    public void loginErrorMassagesTest() {
        String username = "p0li0m";
        String password = "TGdd7EDby83jdAC";
        String homeURL = "http://training.skillo-bg.com:4200/posts/all";
        String loginURL = "http://training.skillo-bg.com:4200/users/login";

        webDriver.get(homeURL);

        String currentURL = webDriver.getCurrentUrl();
        Assert.assertEquals(currentURL, homeURL);

        WebElement loginLink = webDriver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe(loginURL));
/*
        WebElement usernameTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultLoginFormUsername1"))));
        usernameTextField.sendKeys(username);

        WebElement passwordTestField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//form/input[@id='defaultLoginFormPassword']"))));
        passwordTestField.sendKeys(password);
*/
        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//*[@class='remember-me']/input[@type='checkbox']"))));
        rememberMeCheckbox.click();

        Assert.assertTrue(rememberMeCheckbox.isSelected());

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("sign-in-button"))));

        signInButton.click();

        WebElement errorMessageBox = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions actionsForElements = new Actions(webDriver);
        actionsForElements.moveToElement(errorMessageBox).perform();

        String expectedText = "UsernameOrEmail cannot be empty";
                //" UsernameOrEmail cannot be empty ";
        String actualText = errorMessageBox.getText();
        Assert.assertEquals(actualText,expectedText, "The actual text is not matching the expected text");
    }


    private String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }
}
