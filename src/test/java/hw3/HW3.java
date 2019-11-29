package hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HW3 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.cssSelector("input[id='prependedInput']")).sendKeys("storemanager85");
        driver.findElement(By.cssSelector("input[id='prependedInput2']")).sendKeys("UserUser123", Keys.ENTER);

        WebElement loaderMask= null;

        if(driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size()>0) {
            loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        }

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        wait.until(ExpectedConditions.invisibilityOf(loaderMask));

    }
    @Test(description = "verify that page option is displayed")
    public void Test1(){
        WebElement actualOption = driver.findElement(By.cssSelector("div[class='btn btn-link dropdown-toggle']"));
       // wait.until(ExpectedConditions.visibilityOf(actualOption));
        //wait.until(ExpectedConditions.elementToBeClickable(actualOption));
        actualOption.getText();
        Assert.assertTrue(actualOption.isDisplayed(), "page option is not displayed");
    }

    @Test(description = "Verify That page number equal to 1")
    public void Test2(){
        WebElement pageNumber = driver.findElement(By.xpath("//input[@value='1']"));
       // wait.until(ExpectedConditions.visibilityOf(pageNumber));
       // wait.until(ExpectedConditions.elementToBeClickable(pageNumber));
        pageNumber.getText();
       // String expectedPageNumber = "1";
        Assert.assertTrue(pageNumber.isDisplayed(), "page number is not displayed ");
    }

    @Test(description = "Verify that veiw per page no is equal to 25")
    public void Test3(){
        String veiwPerPage = driver.findElement(By.xpath("//button[contains(text(),'25')]")).getText();
        String expectedViewPage = "25";
        Assert.assertEquals(veiwPerPage, expectedViewPage);

    }

    @Test(description = "Verify that number of calendar events is equals to number of records")
    public void Test4(){
        int actualrows = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action']")).size();
         String Acualrow = "Total Of "+actualrows+" Records";

        String ExpectedRow = driver.findElement(By.xpath("//label[@class='dib']/following-sibling::label[2]")).getText();

        Assert.assertEquals(Acualrow, ExpectedRow);

        }

        @Test
        public void Test5(){

        }




    @AfterMethod
    public void teardown(){
       // driver.quit();
    }
}
