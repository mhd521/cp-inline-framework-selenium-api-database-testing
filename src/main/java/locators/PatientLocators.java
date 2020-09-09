package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientLocators {
    @FindBy(css = "#dateOfFirstVisit input")
    public WebElement dateofFirstVisitToDFCIcss;

    @FindBy(css = "#dateOfFirstVisitPrecision input")
    public WebElement estimationDateofFirstVisit;

    @FindBy(xpath = "//div[@class='mat-slide-toggle-thumb']")
    public WebElement CurationDirectivesToggle;

    @FindBy(css = "#dateOfFirstVisitPrecision > app-field-label > div:nth-child(2) > app-attribute-directive > div")
    public WebElement DirectiveText;

    @FindBy(css = "#cdk-accordion-child-3 > mat-action-row > button")
    public WebElement saveButtonCss;

    @FindBy(xpath = "//span[contains(text(),'Save Section')]")
    public WebElement saveButtonXpath;

    @FindBy(xpath = "//span[@class='patient-mrn-info']")
    public WebElement dfciMRN;

    @FindBy(xpath = "//span[contains(text(),'Patient Identification')]")
    public WebElement PatientIdentificationSectionHeader;

    @FindBy(xpath = "//div[@class='section-container form-section-type active']//div[3]//app-form-cell[1]//div[1]//div[3]")
    public WebElement FirstName;

    @FindBy(xpath = "//div[@class='section-container form-section-type active']//div[2]//app-form-cell[1]//div[1]//div[3]")
    public WebElement dateOfBirth;

    @FindBy(css = "#vitalStatus input")
    public WebElement vitalStatus;

    @FindBy(css = "#hybridDateOfDeath input")
    public WebElement dateofDeath;

    @FindBy(css = "#hybridDateOfDeathSource input")
    public WebElement dateOfDeathSource;

    @FindBy(css = "#hybridDateOfDeathPrecision input")
    public WebElement estimationDateofDeath;

    @FindBy(css = "#lastContactDate input")
    public WebElement dateOfLastDfciContact;

    @FindBy(id = "toggle-notes-panel")
    public WebElement referencePanelBar;

    @FindBy(xpath = "//mat-icon[contains(text(),'history')]")
    public WebElement curationHistoryIcon;

    @FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'No')]")
    public WebElement SelectNo;

    @FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'Yes')]")
    public WebElement SelectYes;

    @FindBy(css = "#lastContactDatePrecision input")
    public WebElement estimationDateofLaastDFCIVisit;

    @FindBy(xpath = "//span[contains(text(),'Day Estimated')]")
    public WebElement DayEstimated;

    @FindBy(xpath = "//span[contains(text(),'NDI')]")
    public WebElement selectNDI;

    @FindBy(xpath = "//mat-action-row[@class='mat-action-row']")
    public WebElement matActionRow;

    @FindBy(xpath = "//*[contains(text(),'Last Saved')]")
    public WebElement LastSaved;

    @FindBy(xpath = "//div[@class='patient-mrn-label']")
    public WebElement patientMRNLabel;

    @FindBy(id = "log-out")
    public WebElement LogOut;
}
