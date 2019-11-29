package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase6 {
    private WebDriver driver;
    @Test
   public void Testcase6(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://www.tempmailaddress.com");
        String tempEmail = driver.findElement(By.id("email")).getText();
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("John");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(tempEmail);
        driver.findElement(By.name("wooden_spoon")).click();
        WebElement signupText = driver.findElement(By.name("signup_message"));
        Assert.assertTrue(signupText.isDisplayed());
        BrowserUtils.wait(10);
        driver.navigate().to("https://www.tempmailaddress.com");
        // verify that email is received
        WebElement emailReceive = driver.findElement(By.xpath("//td[contains(text(),'do-not-reply@practice.cybertekschool.com')]"));
        String actualEmail = emailReceive.getText().trim();
        String expectedEmail ="do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(actualEmail, expectedEmail);
         emailReceive.click();
        // verify that the who is the sender of email
        String actualSender = driver.findElement(By.id("odesilatel")).getText();
        String expectedSender = "do-not-reply@practice.cybertekschool.com";
         Assert.assertEquals(actualSender, expectedSender);
        // verify the subject of email
        String actualSubject = driver.findElement(By.id("predmet")).getText();
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(actualSubject,expectedSubject);
        driver.quit();


        }
    }



