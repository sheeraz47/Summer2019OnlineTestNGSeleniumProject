package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase7 {
    private WebDriver driver;

    @Test(description = "TXT file uploading on practice website")
    public void TestCase7(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys("C:/Users/MYPC/Desktop/java.txt");
        driver.findElement(By.id("file-submit")).click();
        String Actulfileuploaded = driver.findElement(By.xpath("//*[text()='File Uploaded!']")).getText();
        String Expectedfileuploaded = "File Uploaded!";
        Assert.assertEquals(Actulfileuploaded, Expectedfileuploaded, "file is not uploaded");
        WebElement uplodedfiles = driver.findElement(By.id("uploaded-files"));
        Assert.assertTrue(uplodedfiles.isDisplayed());
        BrowserUtils.wait(2);
        driver.quit();
    }
}
