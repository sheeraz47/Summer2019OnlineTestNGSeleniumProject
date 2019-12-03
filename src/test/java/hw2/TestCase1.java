package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BrowserFactory;

public class TestCase1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        String expectedResultOne ="Thank you for signing up. Click the button below to return to the home page.";
        String expectedResultTwo = "Home";
​
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys("Cagri Durmaz");
        Thread.sleep(3000);
        driver.findElement(By.name("email")).sendKeys("testcaseone@maildrop.cc");
        Thread.sleep(3000);
        driver.findElement(By.name("wooden_spoon")).click();
​

        String actualResultOne = driver.findElement(By.name("signup_message")).getText();
        String actualResultTwo = driver.findElement(By.id("wooden_spoon")).getText();
​
        System.out.println("First Verification");
        System.out.println("Expected Result " + expectedResultOne);
        System.out.println("Actual Result "+ actualResultOne);
        Assert.assertEquals(expectedResultOne,actualResultOne);
        System.out.println("Second Verification");
        System.out.println("Expected Result " + expectedResultTwo);
        System.out.println("Actual Result "+ actualResultTwo);
        Assert.assertEquals(expectedResultTwo, actualResultTwo);
​
        driver.close();
    }


}
