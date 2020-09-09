package temp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClinicalLocator {

    @FindBy(xpath = "//input[@id='mat-input-0']")
    public WebElement user;

    @FindBy(xpath = "//input[@id='mat-input-1']")
    public WebElement pass;

    @FindBy(xpath = "//button[@class='mat-button']")
    public WebElement submit;

}
