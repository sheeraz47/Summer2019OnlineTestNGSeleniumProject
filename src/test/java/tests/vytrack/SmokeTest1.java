package tests.vytrack;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import tests.TestBase;

public class SmokeTest1 extends TestBase {

    @Test(dataProvider = "navigationInfo")
    public void SmokeTest1(String moduleName, String subModuleName, String pageSubTitle ){
        extentTest = extentReports.createTest("Verify that page subtitle is equal to "+pageSubTitle);
        LoginPage loginPage = new LoginPage();
        loginPage.login("storemanager85", "UserUser123");
        loginPage.navigateTo(moduleName, subModuleName );
        loginPage.waitUntilLoaderMaskDisappear();
        Assert.assertEquals(loginPage.getPageSubTitle(), pageSubTitle);

        extentTest.pass("Verified that page subtitle '"+pageSubTitle+"' is displayed");

    }
    @DataProvider(name = "navigationInfo")
    public Object[][]navigationInfo(){
            return new Object[][]{
                    {"Marketing", "Campaigns", "All Campaigns"},
                    {"Sales", "Opportunities", "Open Opportunities"},
                    {"Marketing", "Email Campaigns", "All Email Campaigns"}
            };
    }
}
