package hw4;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCases extends VyTrack {

    private WebDriverWait wait;

    {
        wait = new WebDriverWait(Driver.get(), 15);
    }

    @Test(description = "Verify that view, edit and delete options are available")
    public void test1() {
        int row = 2;
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
    public void Test2() {
        Driver.get().findElement(By.cssSelector("[title = 'Grid Settings']")).click();

        WebElement name;
        for (int i = 2; i <= 7; i++) {
            name = Driver.get().findElement(By.xpath("//tr[" + i + "]//td[3]//input"));
            name.click();
            Assert.assertTrue(name.isDisplayed(), "Title is not displayed");
        }

    }

    @Test(description = "Verify that 'Save And Close', 'Save And New' and 'Save' options are available")
    public void Test3() {
        Driver.get().findElement(By.cssSelector("a[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        Driver.get().findElement(By.cssSelector("a[class='btn-success btn dropdown-toggle']")).click();
        List<WebElement> alloptions = Driver.get().findElements(By.cssSelector("button[type='submit']"));

        for (int i = 1; i <= 3; i++) {
            alloptions.get(i).getText();

            Assert.assertTrue(alloptions.get(i).isDisplayed(), "its not displayed ");
        }
    }

    @Test(description = "Verify that “All Calendar Events” page subtitle is displayed")
    public void Test4() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        Driver.get().findElement(By.cssSelector("[title='Cancel']")).click();
        WebElement subtitle = Driver.get().findElement(By.className("oro-subtitle"));
        Assert.assertTrue(subtitle.isDisplayed(), "Subtile is not displayed");


    }

    @Test(description = "Verify that difference between end and start time is exactly 1 hour")
    public void Test5() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement startingTime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
        startingTime.click();
        Driver.get().findElement(By.xpath("//li[contains(text(),'12:00 AM')]")).click();
        Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input end ui-timepicker-input']")).click();
        WebElement endingTime = Driver.get().findElement(By.cssSelector("[class='ui-timepicker-am ui-timepicker-selected']:nth-of-type(3)"));
        String actualEndingTime = endingTime.getText();
        String expectedEndingTime = "1:00 AM";
        Assert.assertTrue(endingTime.isDisplayed());
        Assert.assertEquals(actualEndingTime, expectedEndingTime, "Start time and end time both have no time difference ");
    }

    @Test(description = "Verify that end time is equals to '10:00 PM' ")
    public void Test6() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement startTime = Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input start ui-timepicker-input']"));
        startTime.click();
        Driver.get().findElement(By.xpath("//li[contains(text(),'9:00 PM')]")).click();
        Driver.get().findElement(By.cssSelector("[class='input-small timepicker-input end ui-timepicker-input']")).click();
        WebElement endTime = Driver.get().findElement(By.cssSelector("[class='ui-timepicker-pm ui-timepicker-selected']:nth-of-type(3)"));
        String actualEndTime = endTime.getText();
        String expectedEndTime = "10:00 PM";
        Assert.assertEquals(actualEndTime, expectedEndTime, "End time is not equals to 10");
    }

    @Test(description = "Verif 'All Day Event' button is selected, start/end time boxes aren't displayed but date ")
    public void Test7() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement SelectallDayEvent = Driver.get().findElement(By.cssSelector("input[name='oro_calendar_event_form[allDay]']"));
        SelectallDayEvent.click();
        Assert.assertTrue(SelectallDayEvent.isSelected(), "All Day Event button is not selected");
        WebElement startDate = Driver.get().findElement(By.xpath("//label[contains(text(),'Start')]"));
        startDate.getText();
        Assert.assertTrue(startDate.isDisplayed(), "Start date input box is not displayed");
        WebElement endDate = Driver.get().findElement(By.xpath("//label[contains(text(),'End')]"));
        endDate.getText();
        Assert.assertTrue(endDate.isDisplayed(), "End date input box is not displayed");

    }

    @Test(description = "Verify that 'Daily' is selected by default and following options are available in 'Repeats' drop-down:")
    public void Test8() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Assert.assertTrue(RepeatCheckBox.isSelected(), "Repeat checkbox is not selected");
        WebElement hoverOptions = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeats']"));
        Select select = new Select(hoverOptions);
        String actualDefualt = select.getFirstSelectedOption().getText();
        String expectedDefualt = "Daily";
        Assert.assertEquals(actualDefualt, expectedDefualt, "'Daily' is not selected by Defualt");
        List<WebElement> AllDropDownrOptions = select.getOptions();
        ArrayList<String> ActualDropDownOptions = new ArrayList<>();
        ArrayList<String> ExpectedDropDownOptions = new ArrayList<>();
        ExpectedDropDownOptions.addAll(Arrays.asList("Daily", "Weekly", "Monthly", "Yearly"));
        for (WebElement each : AllDropDownrOptions) {
            ActualDropDownOptions.add(each.getText());
        }
        Assert.assertEquals(ActualDropDownOptions, ExpectedDropDownOptions, "Drop Down options are not displayed");
    }

    @Test(description = "Verify that 'Repeat'checkbox/'Repeat Every'&'Never'radio buttons are selected and message summary is displayed ")
    public void Test9() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Assert.assertTrue(RepeatCheckBox.isSelected(), "Repeat checkbox is not selected");
        WebElement RepeatEveryRadioButton = Driver.get().findElement(By.cssSelector("input[checked='checked']"));
        Assert.assertTrue(RepeatEveryRadioButton.isSelected(), "'Repeat Every' radio button is not selected");
        WebElement NeverRadioButton = Driver.get().findElement(By.xpath("//label/input[1][@checked='']"));
        Assert.assertTrue(NeverRadioButton.isSelected());
        WebElement Message = Driver.get().findElement(By.xpath("//*[@data-name='recurrence-summary']//span "));
        String ActualMessage = Message.getText();
        String ExpectedMessage = "Daily every 1 day";
        Assert.assertEquals(ActualMessage, ExpectedMessage, "Message summary is not displayed");
    }

    @Test(description = "Verify that following message as a summary is displayed: 'Daily every 1 day, end after 10 occurrences'")
    public void Test10() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Driver.get().findElement(By.xpath("//*[text()='After']")).click();
        Driver.get().findElement(By.cssSelector("[data-related-field='occurrences']")).sendKeys("10", Keys.ENTER);
        WebElement Message = Driver.get().findElement(By.xpath("//div[@data-name='recurrence-summary']//div"));
        String ActualMessage = Message.getText();
        String ExpectedMessage = "Daily every 1 day, end after 10 occurrences";
        Assert.assertEquals(ActualMessage, ExpectedMessage, "Message summary is not displayed");
    }

    @Test(description = "Verify that following message as a summary is displayed: 'Daily every 1 day, end by Nov 18, 2021'")
    public void Test11() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        Driver.get().findElement(By.xpath("//*[text()='By']")).click();
        WebElement Date = Driver.get().findElement(By.cssSelector("[class='datepicker-input hasDatepicker']"));
        Date.sendKeys("Nov 18, 2021", Keys.ENTER);
        WebElement Message = Driver.get().findElement(By.xpath("//div[@data-name='recurrence-summary']//div"));
        String ActualMessage = Message.getText();
        String ExpectedMessage = "Daily every 1 day, end by Nov 18, 2021";
        Assert.assertEquals(ActualMessage, ExpectedMessage, "Message summary is not displayed");

    }

    @Test(description = "Verify that following message as a summary is displayed: 'Weekly every 1 week on Monday, Friday'")
    public void Test12() {
        Driver.get().findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        WebElement RepeatCheckBox = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        RepeatCheckBox.click();
        WebElement SelectedWeekly = Driver.get().findElement(By.cssSelector("[data-name='recurrence-repeats']"));
        Select select = new Select(SelectedWeekly);
        select.selectByVisibleText("Weekly");
        WebElement SelectMonday = Driver.get().findElement(By.xpath("//*[@type='checkbox'][@value='monday']"));
        SelectMonday.click();
        WebElement SelectFriday = Driver.get().findElement(By.xpath("//*[@type='checkbox'][@value='friday']"));
        SelectFriday.click();
        WebElement Message = Driver.get().findElement(By.xpath("//div[@data-name='recurrence-summary']//div"));
        String ActualMessage = Message.getText();
        String ExpectedMessage = "Weekly every 1 week on Monday, Friday";
        Assert.assertEquals(ActualMessage, ExpectedMessage, "Message summary is not displayed");




    }
}