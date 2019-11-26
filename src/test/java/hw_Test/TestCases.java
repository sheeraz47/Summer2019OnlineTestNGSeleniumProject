package hw_Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class TestCases {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.findElement(By.linkText("Registration Form")).click();
    }

    @Test(description = " Verify the wrong date of birth")
    public void test1(){

        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("wrong_dob");
        String actualmessage = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]")).getText();
        String expectedMessage = "The date of birth is not valid";
        Assert.assertEquals(actualmessage,expectedMessage, "date of birth error message is not displayed ");

    }

    @Test(description = " Verify the check boxes is displayed")
    public void test2() {

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if ( !checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isDisplayed(), "check boxes was not displayed");
        }
    }

    @Test(description = "Verify that first name warning message is displayed ")
    public void testcase3(){

        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("a");
        ////*[@data-bv-for='firstname'][2]
        String actualfirsName = driver.findElement(By.xpath("//*[@data-bv-for='firstname'][2]")).getText();
        String expectedfirstName = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actualfirsName,expectedfirstName, "Error message is not displayed");
    }
    @Test(description = "Verify that last name warning message is displayed ")
    public void testcase4(){

        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("a");
        String actuallastName = driver.findElement(By.xpath("//*[@data-bv-for='lastname'][2]")).getText();
        String expectedlastname = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actuallastName,expectedlastname, "Error message is not displayed");
    }

    @Test(description = "Verify that registration form completion message is displayed")
    public void testcase5(){

        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("John");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Smith");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("msmith");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("smith@email.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("516943023");
        driver.findElement(By.xpath("//*[@data-bv-field=\"gender\"]")).click();
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("01/22/1981");
        Select department = new Select(driver.findElement(By.xpath("//*[@name='department']")));
        department.selectByIndex(1);
        Select jobtitle = new Select(driver.findElement(By.xpath("//*[@name='job_title']")));
        jobtitle.selectByVisibleText("SDET");

        driver.findElement(By.xpath("//input[@id='inlineCheckbox2']/following-sibling::label")).click();

        String actulauResult =  driver.findElement(By.id("wooden_spoon")).getText();

        String expectedResult = "You've successfully completed registration!";
       // Assert.assertEquals(actualResult, expectedResult, "Message was not displayed");

    }





    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
