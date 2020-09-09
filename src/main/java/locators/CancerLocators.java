package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CancerLocators {

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

    @FindBy(xpath = "//div[contains(text(),'Cancer Summary')]")
    public WebElement cancerSummeryLink;

    @FindBy(xpath = "//mat-cell[contains(text(),'ELECTIVE')]")
    public WebElement admimissionType;

    @FindBy(id = "hosp-sum-accept-button")
    WebElement hospitalizationAcceptButton;

    public String cancerTableRow = "//mat-table[@class='summary-table mat-table']//mat-row";
//mat-sidenav-content[@class='mat-drawer-content mat-sidenav-content']//mat-row

    @FindBy(xpath = "//mat-table[@class='summary-table mat-table']//mat-row[1]//mat-cell[8]")
    WebElement source;

    @FindBy (xpath = "//mat-sidenav-content[@class='mat-drawer-content mat-sidenav-content']//mat-row")
    public WebElement cancerTebleRowselection;

    @FindBy(xpath = "//mat-table[@class='summary-table mat-table']")
    public WebElement cancerSummeryTable;

    @FindBy(xpath = "//span[@class='mat-option-text']")
    public WebElement cancerTypeOption;

    @FindBy(css = "#cancerType input")
    public WebElement cancerType;

    @FindBy(css = "#dateOfDiagnosis input")
    public WebElement dateofDiagnosisLocked;

    @FindBy(css = "#dateOfDiagnosisCurated input")
    public WebElement curatedDateOfDiagnosis;

    @FindBy(css ="#dateOfDiagnosisCuratedSource input")
    public WebElement sourceOfCuratedDateofDiagnosis;

    @FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'Imaging')]")
    public WebElement selectImaging;

    @FindBy(css = "#diagnosingInstitution input")
    public WebElement diagnosisInstitution;

    @FindBy(xpath = "//span[contains(text(),'DFCI/Partners')]")
    public WebElement selectDfci;

    @FindBy(css = "#ajccGroupBestStage input")
    public WebElement ajccStage;

    @FindBy(xpath = "//span[contains(text(),'Add 1 Distant Metastasis')]")
    public WebElement add1DistantMetastasis;

    @FindBy(css = "#distantMetastasisDiagnosis input")
    public WebElement siteOfDistantMetastasisAtDiagnosis;

    @FindBy(xpath = "//span[contains(text(),'C00.0: External upper lip')]")
    public WebElement selectUpperLip;

    @FindBy(xpath = "//app-ssf-dictionary")
    public WebElement ssfDictionary;

    public String ssfDictionaryXpath= "//app-ssf-dictionary";

    @FindBy(xpath = "//mat-option[1]")
    public WebElement selectOption1;
    //html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/mat-option[1]/span[1]

    @FindBy(xpath = "//div[contains(text(),'There are no Site Specific Factors for this Cancer')]")
    public WebElement ssfComments;

    @FindBy(xpath = "//span[@class='add-row-txt']")
    public WebElement addCancerEpisode;

    @FindBy(xpath = "//span[contains(text(),'Breast Cancer')]")
    public WebElement breastCancer;

    @FindBy(css = "#sequenceNumber input")
    public WebElement sequenceNo;

    @FindBy(css = "#primarySite input")
    public WebElement primarySite;

    @FindBy(css = "#histologyAndBehavior input")
    public WebElement histologicType;

    @FindBy(css = "#confirmationMethod input")
    public WebElement diagnosticConfirmation;

    @FindBy(css = "#laterality input")
    public WebElement laterality;

    @FindBy(css = "#grade input")
    public WebElement grade;

    @FindBy(css = "#diagnosisStageIV input")
    public WebElement diagnosisStageIV;

    @FindBy(xpath = "//span[contains(text(),'Yes')]")
    public WebElement selectYes;

    @FindBy(xpath = "//span[@class='mat-option-text'][contains(text(),'No')]")
    public WebElement selectNo;

    @FindBy (css = "#ajccTnmVersion input")
    public WebElement AjccTnmEdition;

    @FindBy(css = "#clinicalStageGroup input")
    public WebElement clinStageGroup;

    @FindBy(css = "#pathologicStageGroup input")
    public WebElement pathStageGroup;

    @FindBy(xpath = "//span[contains(text(),'Delete and Reset')]")
    public WebElement deleteAndReset;

    @FindBy (id = "delete-window-delete-button")
    public WebElement deleteWindowDeleteButton;

    @FindBy(css = "#dateOfDiagnosisPrecision input")
    public WebElement dateOfDiagnosisEstimation;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    public WebElement onlyDelete;

    @FindBy(id = "log-out")
    public WebElement LogOut;
}

