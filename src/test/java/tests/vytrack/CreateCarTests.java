package tests.vytrack;

import org.testng.annotations.Test;
import pages.CreateCarPage;
import pages.LoginPage;
import pages.VehiclesPage;
import tests.TestBase;
import utils.ConfigurationReader;
import utils.ExcelUtil;

import java.util.List;
import java.util.Map;

public class CreateCarTests extends TestBase {

    @Test(description = "Create some random car")
    public void test1(){
        extentTest = extentReports.createTest("Create a new car");

        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        CreateCarPage createCarPage = new CreateCarPage();

        loginPage.login("storemanager85", "UserUser123");
        loginPage.navigateTo("Fleet", "Vehicles");

        loginPage.waitUntilLoaderMaskDisappear();

        vehiclesPage.clickToCreateACar();

        loginPage.waitUntilLoaderMaskDisappear();

        createCarPage.licensePlateElement.sendKeys("Random");
        createCarPage.selectTags("Compact");
        createCarPage.selectFuelType("Diesel");

        loginPage.waitUntilLoaderMaskDisappear();
        createCarPage.saveAndCloseButtonElement.click();

        extentTest.pass("New car was created");
    }

    @Test(description = "Crearte a car by reading test data from excel file")
    public void createCartest(){
        extentTest = extentReports.createTest("Crearte a car by reading test data from excel file");

        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        CreateCarPage createCarPage = new CreateCarPage();

        String username = ConfigurationReader.getProperty("user_name");
        String password = ConfigurationReader.getProperty("password");

        loginPage.login(username, password);

        loginPage.navigateTo("Fleet", "Vehicles");

        loginPage.waitUntilLoaderMaskDisappear();

        vehiclesPage.clickToCreateACar();

        loginPage.waitUntilLoaderMaskDisappear();

        ExcelUtil excelUtil = new ExcelUtil("cars.xlsx", "cars");

       List<Map<String, String> > testData = excelUtil.getDataList();

        createCarPage.licensePlateElement.sendKeys(testData.get(0).get("License Plate"));
        createCarPage.driverElement.sendKeys(testData.get(0).get("Driver"));
        createCarPage.modelYearElement.sendKeys(testData.get(0).get("Model Year"));
        createCarPage.colorElement.sendKeys(testData.get(0).get("Color"));
        loginPage.waitUntilLoaderMaskDisappear();
        createCarPage.saveAndCloseButtonElement.click();

        extentTest = extentReports.createTest("Crearte a new car");

        //with many rows of data
        //for(Map<String, String> value: testData){
            //createCarPage.licensePlateElement.sendKeys(value.get("License Plate"));
       // }

    }
}