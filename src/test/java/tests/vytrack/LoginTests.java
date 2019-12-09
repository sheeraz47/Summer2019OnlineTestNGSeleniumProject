package tests.vytrack;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.TestBase;
import utils.Driver;

public class LoginTests extends TestBase {

    @Test(description = "Verify that page title is a 'Dashboard'")
    public void test1(){

        LoginPage loginPage = new LoginPage();
        loginPage.login("storemanager85","UserUser123");

        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        wait.until(ExpectedConditions.titleIs("Dashboard"));
        Assert.assertEquals(Driver.get().getTitle(), "Dashboard");

    }
}
