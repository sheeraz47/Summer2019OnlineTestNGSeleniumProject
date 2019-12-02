package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

//according to page object model design
// we have to create corresponded page class for each page of application
//login page = login page class
//every page class will store webelements and methods related to the page
public class LoginPage extends BasePage {

    @FindBy(id= "prependedInput")// this line will initialize web element
    public WebElement userNameInput;

    @FindBy(id= "prependedInput2")// withpout findby, web element will be null
    public WebElement passwordInput;

    @FindBy(css = "[class='alert alert-error']")
    public WebElement warningMessage;


    public LoginPage() {
        //it's mandotary if you want to use @FindBy annotation
        //this means the class we woking on (login page)
        //Driver.get() return webdriver object
        PageFactory.initElements(Driver.get(), this);
    }

        public void login(String userName, String password){

        userNameInput.sendKeys(userName);

        passwordInput.sendKeys(password, Keys.ENTER);
    }
}
