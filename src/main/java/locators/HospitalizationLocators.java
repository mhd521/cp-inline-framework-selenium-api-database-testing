package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HospitalizationLocators {


    @FindBy(xpath = "//div[@class='mat-slide-toggle-thumb']")
    public WebElement CurationDirectivesToggle;

    @FindBy(xpath = "//div[contains(text(),'Hospitalization Summary')]")
    public WebElement hospitalizationSection;

    @FindBy(css = "#admissionDate > app-field-label > div:nth-child(2) > app-attribute-directive > div")
    public WebElement DirectiveText;

    @FindBy(xpath = "//span[contains(text(),'Save Section')]")
    public WebElement saveButtonXpath;

    @FindBy(xpath = "//span[@class='patient-mrn-info']")
    public WebElement dfciMRN;

    @FindBy(xpath = "//span[contains(text(),'Add Outside Hospitalization')]")
    public WebElement addHospitalization;

    @FindBy(xpath = "//span[contains(text(),'Accept')]")
    public WebElement addHospitalizationSourceForm;

    String accept = "//span[contains(text(),'Accept')]";

    @FindBy(css = "#admissionDate input")
    public WebElement hospitalAdmissionDate;

    @FindBy(css = "#admissionDatePrecision input")
    public WebElement hospitalAdmissionDateSource;

    @FindBy(css = "#dischargeDate input")
    public WebElement dischargeDate;

    @FindBy(css = "#dischargeDatePrecision input")
    public WebElement dischargeDateSource;

    @FindBy(css = "#institution input")
    public WebElement institution;

    @FindBy(xpath = "//span[contains(text(),'Day Estimated')]")
    public WebElement selectDayEstimatedAdmissionEstimated;

    @FindBy(xpath = "//span[contains(text(),'Day and Month Estimated')]")
    public WebElement selectDayAndMonthAdmissionEstimated;

    @FindBy(xpath = "//span[contains(text(),'Day, Month, Year Estimated')]")
    public WebElement selectDayMonthYearAdmissionEstimated;


    @FindBy(xpath = "//span[contains(text(),'External Institution')]")
    public WebElement selectInstitution;

    @FindBy(xpath = "//span[contains(text(),' Day Estimated ')]")
    public WebElement selectDischargeDateSource;

    @FindBy(id = "toggle-notes-panel")
    public WebElement referencePanelBar;

    @FindBy(xpath = "//*[contains(text(),'Last Saved')]")
    public WebElement LastSaved;

    @FindBy(xpath = "//mat-icon[contains(text(),'history')]")
    public WebElement curationHistoryIcon;

    @FindBy(xpath = "//mat-action-row[@class='mat-action-row']")
    public WebElement matActionRow;

    @FindBy(xpath = "//div[@class='navbar-container']")
    public WebElement nevBar;

    @FindBy(xpath = "//span[contains(text(),'Hospitalization')]")
    public WebElement hospitalizationBar;

    @FindBy(xpath = "//span[contains(text(),'Curation Directives')]")
    public WebElement curationDirectiveText;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    public WebElement deleteButton;

    @FindBy(id = "delete-window-delete-button")
    public WebElement deletewindowbutton;

    @FindBy(xpath = "//mat-dialog-container")
    public WebElement itemContainer;

    @FindBy(xpath = "/html/body/app-root/app-app-container/div/app-curation-container/div/mat-sidenav-container/mat-sidenav-content/div/div[3]/app-hospitalization-form/div[1]/mat-expansion-panel/div/div/app-hospitalization-table/div/mat-table")
    public WebElement hospitalizationSummaryTable;

    @FindBy(xpath = "/html/body/app-root/app-app-container/div/app-curation-container/div/mat-sidenav-container/mat-sidenav-content/div/div[3]/app-hospitalization-form/div[1]/mat-expansion-panel/div/div/app-hospitalization-table/div/mat-table/mat-row")
    public WebElement hospitalizationSummaryRow;

    @FindBy(xpath = "//span[@class='expansion-section-label']")
    public WebElement hospitalizationSummaryTableHeadline;

    @FindBy(xpath = "//span[contains(text(),' View Form ')]")
    public WebElement firstViewForm;

    @FindBy (xpath = "//*[@id=\"admissionDatePrecision\"]//mat-icon[contains(text(),'toc')]")
    public WebElement itemDirective;

    @FindBy(xpath = "//div[@class='directive-title']")
    public WebElement directiveTitle;

    @FindBy(xpath = "//mat-cell[contains(text(),' Day Estimated ')]")
    public WebElement itemDirectiveText1;

    @FindBy(xpath = "//div[contains(text(),'Hospitalization Summary')]")
    public WebElement hospitalizationSummeryLink;

    @FindBy(xpath = "//mat-cell[contains(text(),'ELECTIVE')]")
    public WebElement admimissionType;

    @FindBy(id = "hosp-sum-accept-button")
    WebElement hospitalizationAcceptButton;
}

