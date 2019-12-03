package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BrowserFactory;

public class TestCases {

​
        private WebDriver driver;
        private WebDriverWait wait;
​
        @BeforeMethod
        public void setup(){
            driver = BrowserFactory.getDriver("Chrome");
            driver.get("https://practice-cybertekschool.herokuapp.com");
            driver.findElement(By.linkText("Status Codes")).click();
            wait = new WebDriverWait(driver, 10);
        }
​
        @DataProvider(name = "TestData")
        public Object[][] data(){
            return new Object[][]{{"200", "This page returned a 200 status code"},
                    {"301", "This page returned a 301 status code"},
                    {"404", "This page returned a 404 status code"},
                    {"500", "This page returned a 500 status code"}
            };
        }
​
        @Test(dataProvider = "TestData")
        public void TestCases(String statuscode, String expectedResult){
​
            WebElement button = driver.findElement(By.linkText(statuscode));
            wait.until(ExpectedConditions.visibilityOf(button));
            button.click();
            WebElement result = driver.findElement(By.xpath("//p"));
            wait.until(ExpectedConditions.visibilityOf(result));
            String actualResult = result.getText();
            int dotIndex = actualResult.indexOf(".");
            actualResult = actualResult.substring(0,dotIndex);
            Assert.assertEquals(actualResult, expectedResult);
        }
​
        ​
        ​
        @AfterMethod
        public void teardown() {
            driver.quit();
        }
​
    }

