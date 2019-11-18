package tests.day10;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class PopUpPractice {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");

    }
    @Test(description = "Click on button 1 and click ok in pop up message")
    public void test1(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        BrowserUtils.wait(2);
        //to deal with popup, we can create object of Alert
        //Switches to the currently active modal dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();//to click ok
        BrowserUtils.wait(2);
    }
    @Test(description = "Click on button 2 and click cancel in pop up message")
    public void test2(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[2]")).click();
        BrowserUtils.wait(2);
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        BrowserUtils.wait(2);
        System.out.println(driver.findElement(By.id("result")).getText());
    }

    @Test(description = "Click on button 3, and enter somme text and then click")
    public void test3(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();
        BrowserUtils.wait(2);
        driver.switchTo().alert().sendKeys("Java is fun!");
        BrowserUtils.wait(2);
        driver.switchTo().alert().accept();
        System.out.println(driver.findElement(By.id("result")).getText());
    }




    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}