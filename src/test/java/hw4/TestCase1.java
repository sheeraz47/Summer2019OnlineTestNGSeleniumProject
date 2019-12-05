package hw4;


import org.apache.commons.compress.compressors.brotli.BrotliUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase1 extends VyTrack {

    private WebDriverWait wait;

    {
        wait = new WebDriverWait(Driver.get(), 15);
    }

    @Test(description = "Verify that view, edit and delete options are available")
    public void test1() {
        int row = 14;
        int column = 9;
        WebElement HoverPoint = Driver.get().findElement(By.xpath("//tbody//tr[" + row + "]//td[" + column + "]"));
        Actions action = new Actions(Driver.get());
        action.moveToElement(HoverPoint).perform();
        String[] options = { "a[href='#']", "a[href='/calendar/event/update/184']","a[href='/calendar/event/view/184']"};
        WebElement option;
        for (String each : options) {
            option = Driver.get().findElement(By.cssSelector(each));
            Assert.assertTrue(option.isDisplayed());
            }
        }

        @Test(description = "Verify that “Title” column still displayed")
        public void Test2(){
            Driver.get().findElement(By.cssSelector("[class='visibility-cell']")).click();
            WebElement name;

        }
        @Test(description = "Verify that 'Save And Close', 'Save And New' and 'Save' options are available")
        public void Test3(){
            Driver.get().findElement(By.cssSelector("a[title='Create Calendar event']")).click();
            WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            Driver.get().findElement(By.cssSelector("a[class='btn-success btn dropdown-toggle']")).click();
            List<WebElement> alloptions = Driver.get().findElements(By.cssSelector("button[type='submit']"));

            for(int i = 1; i <= 3; i++ ){
                alloptions.get(i).getText();

              Assert.assertTrue(alloptions.get(i).isDisplayed(), "its not displayed ");
            }
        }

        @Test(description = "Verify that “All Calendar Events” page subtitle is displayed")
        public void Test4(){
            Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
            WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            Driver.get().findElement(By.cssSelector("[title='Cancel']")).click();
            WebElement subtitle = Driver.get().findElement(By.className("oro-subtitle"));
            Assert.assertTrue(subtitle.isDisplayed(), "Subtile is not displayed");


        }

        @Test(description = "Verify that difference between end and start time is exactly 1 hour")
        public void Test5(){
            Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
            WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            WebElement startingtime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
            startingtime.click();
            Driver.get().findElement(By.xpath("//li[contains(text(),'12:00 AM')]")).click();
            WebElement endingtime = Driver.get().findElement(By.cssSelector("[class='ui-timepicker-am ui-timepicker-selected']:nth-of-type(3)"));
            String actualendingTime = endingtime.getText();                 //"[class='ui-timepicker-am ui-timepicker-selected']:nth-of-type(3)"
            String expectedendingTime = "1:00 AM";
            Assert.assertEquals(actualendingTime, expectedendingTime, "Start time and end time both have no time difference ");


        }


    }


