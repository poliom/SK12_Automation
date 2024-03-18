package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.AbstractSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExampleTests {
    ChromeDriver webDriver;

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
    }

    @Test
    public void testPromtBox(){
        webDriver.get("https://demoqa.com/alerts");
        WebElement promptBoxButton = webDriver.findElement(By.id("alertButton"));
        promptBoxButton.click();

        Alert alertBox = webDriver.switchTo().alert();
        alertBox.accept();
        //alertBox.dismiss();
        System.out.println("");
    }

    @Test
    public void testPromtBoxFillText(){
        webDriver.get("https://demoqa.com/alerts");
        WebElement promptBoxButton = webDriver.findElement(By.id("promtButton"));
        promptBoxButton.click();

        String textForFill = "Test alert Text";

        Alert alertBox = webDriver.switchTo().alert();

        String alertBoxText = alertBox.getText();

        alertBox.sendKeys(textForFill);

        alertBox.accept();

        System.out.println("The Alert box text is: " + alertBoxText);
    }

    @Test
    public void testIFrame(){
        webDriver.get(("https://demoqa.com/frames"));
        List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));

        System.out.println("Nuber of iFrames is: "+ frames.size());

        webDriver.switchTo().frame("frame1");
        String textOfIFrame = webDriver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(textOfIFrame, "This is a sample page");
    }

    @Test
    public void checkboxDynamicGetAndExpand(){
        webDriver.get("https://demoqa.com/checkbox");
        WebElement checkboxHomeButton = webDriver.findElement(By.xpath("//*[@for='tree-node-home']/preceding-sibling::button[@title='Toggle']"));

        Wait expWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        expWait.until(ExpectedConditions.elementToBeClickable(checkboxHomeButton));

        checkboxHomeButton.click();
        System.out.println("");

/*      //will create for demo after the lecture
        //This code will do the following
        //Get All elements for that meet the condition of locator
        List<WebElement> checkboxListElements = webDriver.findElements(By.xpath("//*[@id='tree-node']//li"));
        //Get the number of elements that are get
        int countElements = checkboxListElements.size();
        //Condition of what we are searching for
        String searchedElementText = "Desktop";
        //Perform two filtrations of the elements for find the element we need
        for (int i = 0; i<countElements; i++){
            //Get the class property of a single element
            String classText = checkboxListElements.get(i).getAttribute("Class");
            //Compare if the property contains specific condition
            if (classText.contains("collapsed")) {
                //Get the text of every element
                String elementText = checkboxListElements.get(i).getText();
                //Check if the text of the element meet the condition we need
                if (elementText.contains(searchedElementText)){
                    //Check the checkbox/the element that is meeting our conditions
                    checkboxListElements.get(i).click();
                }
            }
            //webDriver.findElement()
        }
 */
        WebElement nextCheckboxButton = webDriver.findElement(By.xpath("//*[@id='tree-node']//li//span[text()='Desktop']"));
        expWait.until(ExpectedConditions.elementToBeClickable(nextCheckboxButton));

        nextCheckboxButton.click();
        // fail due to not expand checkbox
        // new click for toggle need to be added for fixing the test
        WebElement verificationCheckbox = webDriver.findElement(By.xpath("//*[@class='rct-title'][text()='Notes']"));
        Assert.assertTrue(verificationCheckbox.isDisplayed());


    }
}
