package hw1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;




public class Tests {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
    }
    @Test(description = "Sign up for mailing list and Sign up button verifications")
    public void testcase1(){
       driver.findElement(By.linkText("Sign Up For Mailing List")).click();
       driver.findElement(By.name("full_name")).sendKeys("Muhammad Saeed");
       driver.findElement(By.name("email")).sendKeys("random@email.com");
       driver.findElement(By.name("wooden_spoon")).click();
       String acTualSignup = driver.findElement(By.name("signup_message")).getText();
       String expectedSignup = "Thank you for signing up. Click the button below to return to the home page.";
       Assert.assertEquals(acTualSignup,expectedSignup, "Sign up not same");
       String actualHome = driver.findElement(By.id("wooden_spoon")).getText();
       String expectedHome = "Home";
       Assert.assertEquals(actualHome,expectedHome, "Home is not the same");
    }
    @Test(description = "Verify the numbers of listed examples is equal to 48")
    public void  testcase2(){
        int expectedResult = 48;
        int actualresult = driver.findElements(By.className("list-group-item")).size();
        Assert.assertEquals(actualresult,expectedResult,"Result size is not same");
        System.out.println(actualresult);
    }

    @Test(description = "Clicked on button one")
    public void testcase3(){
       driver.findElement(By.linkText("Multiple Buttons")).click();
       driver.findElement(By.cssSelector(".btn.btn-primary")).click();
       String actualResult = driver.findElement(By.id("result")).getText();
       String expextedResult = "Clicked on button one!";
       Assert.assertEquals(actualResult,expextedResult, "Button is not click");
       System.out.println(actualResult);

    }
    @Test(description = "verify the warning of wrong first name")
    public void testcase4() throws InterruptedException {
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("123");
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[3]")).getText();
        String expectedresult = "first name can only consist of alphabetical letters";
        Assert.assertEquals(actualResult,expectedresult, "warning is not displayed");

    }
    @Test(description = "Verify the warning message of wrong last name")
    public void testcase5(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("123");
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[3]")).getText();
        String expectedresult = "The last name can only consist of alphabetical letters and dash";
        Assert.assertEquals(actualResult, expectedresult, " warning is not displayed");
    }
    @Test(description = "Verify the warning message of wrong user name")
    public void testcase6(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("user");
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[3]/div/small[2]")).getText();
        String expectedResult = "The username must be more than 6 and less than 30 characters long";
        Assert.assertEquals(actualResult,expectedResult,"warning message is not displayed");

    }
    @Test(description = "Verify that warning messages of email")
    public void testcase7(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("testers@email");
        String actualresult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[4]/div/small[2]")).getText();
        String expectedresult = "email address is not a valid";
        Assert.assertEquals(actualresult,expectedresult, "Email warning is not displayed");
        String actualResult1 = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[4]/div/small[3]")).getText();
        String expectedresult2 = "Email format is not correct";
        Assert.assertEquals(actualResult1,expectedresult2, "email warning is not displayed");
        System.out.println(actualresult);
        System.out.println(actualResult1);
    }
    @Test(description = "Verify the warning message of wrong phone number")
    public void testcase8(){
        driver.findElement(By.linkText("Registration Form")).click();
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("5711234354” ");
        String actualResult = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[6]/div/small[2]")).getText();
        String expectedResult = "Phone format is not correct";
        Assert.assertEquals(actualResult,expectedResult, "phone warning is not displayed");
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
