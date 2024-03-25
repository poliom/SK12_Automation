package lecture17;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;


public class PostTests extends TestObject{
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
                File postPicture = new File("src\\test\\resources\\upload\\testUpload.jpg");
                String caption = "Testing upload file";
        return new Object[][]{
                //To pass the first data object the userId needs to be changed to 5508
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                {"p0li0m","TGdd7EDby83jdAC", "5508", postPicture, caption},
                //{"Ivakis","Qwerty1", "5511"}
        };
    }
    @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPicture, String caption){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
        /*
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");
        //Assert.assertTrue(false);
        loginPage.clickSignIn();
        */

        loginPage.completeSingIn(username,password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostLoaded(), "The new post form is not loaded");

        postPage.uploadPicture(postPicture);
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("testUpload.jpg"), "Image is not uploaded");
        Assert.assertEquals(actualImageText, "testUpload.jpg", "Incorect image is uploaded");

        postPage.typePostCaption(caption);
        postPage.clickCreatePost();

        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");
    }
}
