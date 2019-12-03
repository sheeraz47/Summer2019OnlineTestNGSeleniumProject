package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateCalendarEventPage {

    @FindBy(css = "[class='select2-chosen']")
    public WebElement owner;

}
