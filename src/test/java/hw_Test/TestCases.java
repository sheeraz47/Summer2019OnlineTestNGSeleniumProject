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
    }

    @Test(description = " Verify the wrong date of birth")
    public void test1(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("wrong_dob");
        String actualmessage = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]")).getText();
        String expectedMessage = "The date of birth is not valid";
        Assert.assertEquals(actualmessage,expectedMessage, "date of birth error message is not displayed ");

    }

    @Test(description = " Verify the check boxes displayed")
    public void test2() {
        driver.findElement(By.linkText("Registration Form")).click();
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
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("a");
        ////*[@data-bv-for='firstname'][2]
        String actualfirsName = driver.findElement(By.xpath("//*[@data-bv-for='firstname'][2]")).getText();
        String expectedfirstName = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actualfirsName,expectedfirstName, "Error message is not displayed");
    }
    @Test(description = "Verify that last name warning message is displayed ")
    public void testcase4(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("a");
        String actuallastName = driver.findElement(By.xpath("//*[@data-bv-for='lastname'][2]")).getText();
        String expectedlastname = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actuallastName,expectedlastname, "Error message is not displayed");
    }

    @Test(description = "Verify that registration form completion message is displayed")
    public void testcase(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Muhammad");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Saeed");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("sheeraz47");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("sheeraz@email.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("5169430453");

        driver.findElement(By.cssSelector("input[value='male']")).click();
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("01/22/1981");


        BrowserUtils.wait(3);
    }





    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
