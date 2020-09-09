package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sections.Patient;

public class CurationLocators {



    @FindBy(xpath = "//div[@class='start_dt_time_stamp']")
    public WebElement dateandtime;

    @FindBy(xpath = "//div[@class='patient-info-container-horizontal ng-star-inserted']//div[1]//div[2]")
    public WebElement PatientName;

    @FindBy(id = "return-to-main-menu")
    public WebElement returnToMainMenuBtn;

    @FindBy(xpath = "//h1[contains(text(),'Curation In Progress')]")
    public WebElement curationInProgressText;

    @FindBy(id = "flag-patient-button")
    public WebElement flagButton;

    @FindBy(id = "comment-accept-button")
    public WebElement flagAcceptButton;

    @FindBy(xpath = "//textarea[@placeholder='Add Comment']")
    public WebElement flagCommentsArea;

    @FindBy(id = "complete-curation-button")
    public WebElement completeCurationButton;

    @FindBy(xpath = "//a[@class='ng-star-inserted']")
    public WebElement returnBtn;

    @FindBy(xpath = "//div[@class='error-container ng-star-inserted']")
    public WebElement errorContainer;

    @FindBy(id = "error-close-button")
    public WebElement errorCloseBtn;

    @FindBy(xpath = "//div[@class='validation-container']")
    public WebElement validationContainer;

    @FindBy(id = "qa-accept-button")
    public WebElement qaAcceptBtn;

    @FindBy(xpath = "//div[@class='window-button']")
    public WebElement windowBtn;

    @FindBy(id = "close-validation-window")
    public WebElement closeValidationWindow;

    @FindBy(xpath = "//div[@class='patient-mrn-label']")
    public WebElement patientMRNLabel;

    @FindBy(xpath = "//div[@class='validation-content-container']")
    public WebElement validationContentContainer;

    @FindBy(id = "log-out")
    public WebElement LogOut;

    @FindBy(xpath = "//span[contains(text(),'Flag Patient')]")
    public WebElement flagingPatient;

}
