package h4;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;

import java.util.List;

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
        String[] options = {"a[href='#']", "a[href='/calendar/event/update/184']", "a[href='/calendar/event/view/184']"};
        WebElement option;
        for (String each : options) {
            option = Driver.get().findElement(By.cssSelector(each));
            Assert.assertTrue(option.isDisplayed());
        }
    }

    @Test(description = "Verify that “Title” column still displayed")
    public void Test2() {
        Driver.get().findElement(By.cssSelector("[class='fa-cog hide-text']")).click();

        WebElement name;
        for (int i = 2; i <= 7; i++) {
            name = Driver.get().findElement(By.xpath("//tr[" + i + "]//td[3]//input"));
            name.click();
            Assert.assertFalse(name.isSelected(), "Option is still selected");
        }

        WebElement titleColumn = Driver.get().findElement(By.xpath("//span[@class='grid-header-cell__label'][contains(text(),'Title')][1]"));
        Assert.assertTrue(titleColumn.isDisplayed(), "Title column is not displayed");

        }
    }

















