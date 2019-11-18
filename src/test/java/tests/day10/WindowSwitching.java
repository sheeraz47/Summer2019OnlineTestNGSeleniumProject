package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.Set;

public class WindowSwitching {
    private WebDriver driver;

    @BeforeMethod
    public  void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }

    @Test(description = "Verify that title is still practice")
    public void test1(){
        driver.findElement(By.linkText("New tab")).click();
        // after 3 seconds another website will be open in the second window
        // selenium doesn't switch automatically in new window
        BrowserUtils.wait(5);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Practice", "Title is wrong!");
    }

    @Test(description = "Verify that user is able to see new window")
    public void test2(){
        driver.findElement(By.linkText("New tab")).click();
        // record id of original window, thta will open initially
        String oldwindow = driver.getWindowHandle();
        // after 3 seconds another website will be open in the second window
        // selenium doesn't switch automatically in new window
        BrowserUtils.wait(5);
        //in the selenium every window has an id. that id calls window handle
        //to read window handle we use method getWindowHandle()
        //after new window was opened, we can get list of all windows id's and window handles
        //list = its a data structure
        //set its's also data structure, like list, but it doesn't allow duplicates
        //also, you can not easily access anything from there
        //there is not .get() method
        //that's why, we need to loop through the set, to red a data from there
        //set can store only unique values
        Set<String> windowHandles = driver.getWindowHandles();
        // loop through the collection of window handles
        for(String windowHandle: windowHandles){
            // if its not an old window
            if(!windowHandle.equals(oldwindow)){
                // switch to that window
                driver.switchTo().window(windowHandle);
            }
        }
        // lets verify that title of new window is a fresh tab
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Fresh tab", "Title is wrong!");
        //comeback to original page
        // we can build a function, that will jump, in between windows
        //based on page title, we can determine where to stop
        String pageTitle = "Practice";// title of the page that we want

        for(String windowHandle : windowHandles){
            //keep jumping from window to window
            driver.switchTo().window(windowHandle);
            //once we found a correct page title
            if(driver.getTitle().equals(pageTitle)){
                //just exit
                //stop jumping
                break;
            }
        }
        System.out.println(driver.getTitle());
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
