package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserFactory;

public class TestCase8 {

    private WebDriver driver;

    @Test(description = "Uploading file on practice website")
    public void TestCase8() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.cssSelector("input[type='button']")).click();
        String actualResult = driver.findElement(By.id("result")).getText();
        String expectedResult = "You selected: United States of America";
        Assert.assertEquals(actualResult, expectedResult, "file is not uploaded");

        driver.quit();

    }
}
