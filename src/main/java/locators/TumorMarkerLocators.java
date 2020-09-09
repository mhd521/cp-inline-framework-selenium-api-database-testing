package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TumorMarkerLocators {
    @FindBy(xpath = "//div[@class='mat-slide-toggle-thumb']")
    public WebElement CurationDirectivesToggle;

    @FindBy(xpath = "//span[contains(text(),'Tumor Marker Summary')]")
    public WebElement tumorMarkerSection;

    @FindBy(css = "#testName > app-field-label > div:nth-child(2) > app-attribute-directive > div")
    public WebElement DirectiveText;

    @FindBy(xpath = "//span[contains(text(),'Save Section')]")
    public WebElement saveButtonXpath;

    @FindBy(xpath = "//span[@class='patient-mrn-info']")
    public WebElement dfciMRN;

    @FindBy(xpath = "//span[contains(text(),'Add Outside Tumor Marker Result')]")
    public WebElement addTumorMarker;

    @FindBy(css = "#testName input")
    public WebElement testName;

    @FindBy(css = "#labInstitution input")
    public WebElement labInstitution;

    @FindBy(css = "#specimenDate input")
    public WebElement specimenCollectionDate;

    @FindBy(css = "#specimenDatePrecision input")
    public WebElement specimenCollectionDateSource;

    @FindBy(css = "#numericResult input")
    public WebElement numericResult;

    @FindBy(css = "#resultUnit  input")
    public WebElement resultUnit ;

    @FindBy(css = "#textResult input")
    public WebElement textResult;

    @FindBy(css = "#normalRange input")
    public WebElement normalRange;

    @FindBy(css = "#resultIndicator input")
    public WebElement resultOutOfRange;

    @FindBy(xpath = "//span[contains(text(),'CA 125')]")
    public WebElement selectTestName;

    @FindBy(xpath = "//span[contains(text(),'External Institution')]")
    public WebElement selectLabInstitution;

    @FindBy(xpath = "//span[contains(text(),' Day Estimated ')]")
    public WebElement selectCollectionDateSource;

    @FindBy(xpath = "//span[contains(text(),'U/L')]")
    public WebElement selectResultUnit;

    @FindBy(xpath = "//span[contains(text(),'High')]")
    public WebElement selectResultOutOfRange;

    @FindBy(id = "toggle-notes-panel")
    public WebElement referencePanelBar;

    @FindBy(xpath = "//mat-icon[contains(text(),'history')]")
    public WebElement curationHistoryIcon;

    @FindBy(xpath = "//mat-action-row[@class='mat-action-row']")
    public WebElement matActionRow;

    @FindBy(xpath = "//*[contains(text(),'Last Saved')]")
    public WebElement LastSaved;

    @FindBy(xpath = "//div[@class='navbar-container']")
    public WebElement nevBar;

    @FindBy(xpath = "//div[contains(text(),'Tumor Marker Summary')]")
    public WebElement tumorMerkerSummerLink;

    @FindBy(xpath = "//span[@class='add-row-txt']")
    public WebElement addTumorMarkerForm;

    @FindBy(xpath = "//span[contains(text(),'Tumor Marker Test Result')]")
    public WebElement tumorMarkerTestResultBar;

    @FindBy (xpath = "//*[@id=\"testName\"]//mat-icon[contains(text(),'toc')]")
    public WebElement itemDirective;

    @FindBy(xpath = "//div[@class='directive-title']")
    public WebElement directiveTitle;

    @FindBy(xpath = "//mat-cell[contains(text(),'AFP')]")
    public WebElement itemDirectiveTextAFPl;

    @FindBy(id = "notes-tumor-markers-tab")
    public WebElement tumorMarkerNotesTab;

    //div[@class='mat-sort-header-container mat-sort-header-sorted']//button[@class='mat-sort-header-button'][contains(text(),'TEST NAME')]
    @FindBy(xpath = "/html/body/app-root/app-app-container/div/app-curation-container/div/mat-sidenav-container/mat-sidenav/div/app-notes/div[2]/app-tumor-marker-table/div/mat-table/mat-row")
    public WebElement tumorNotesTableRows;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    public WebElement deleteButton;

    @FindBy(id = "delete-window-delete-button")
    public WebElement deletewindowbutton;

    @FindBy(xpath = "//mat-dialog-container")
    public WebElement itemContainer;

    @FindBy(xpath = "/html/body/app-root/app-app-container/div/app-curation-container/div/mat-sidenav-container/mat-sidenav-content/div/div[3]/app-outside-tumor-marker-form/div[1]/mat-expansion-panel/div/div/app-outside-tumor-marker-table/div/mat-table")
    public WebElement tumorMarkerSummeryTable;

    @FindBy(xpath = "/html/body/app-root/app-app-container/div/app-curation-container/div/mat-sidenav-container/mat-sidenav-content/div/div[3]/app-outside-tumor-marker-form/div[1]/mat-expansion-panel/div/div/app-outside-tumor-marker-table/div/mat-table/mat-row")
    public WebElement tumorMarkerSummeryRow;

    @FindBy(xpath = "//span[@class='expansion-section-label']")
    public WebElement tumorMarkerSummeryTableHeadline;

    @FindBy(css = "#view-path-form-button")
    public WebElement viewFormButton;

    @FindBy(xpath = "//app-outside-tumor-marker-form/div[1]/mat-expansion-panel/div/div/app-outside-tumor-marker-table/div/mat-table/mat-row[1]/mat-cell[7]/div/button")
    public WebElement firstViewForm;
}
