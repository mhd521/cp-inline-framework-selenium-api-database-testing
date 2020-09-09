package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonLocators {

    @FindBy(id = "flag-patient-button")
    WebElement FlagPatient;

    @FindBy(id = "toggle-notes-panel")
    public WebElement referencePanelBar;

}
