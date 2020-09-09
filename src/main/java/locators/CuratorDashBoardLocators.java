package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CuratorDashBoardLocators {



    @FindBy(id = "patient_682785_resume-flagged-curation-button")
    public WebElement p682785_resume_flagged;

    @FindBy(xpath = "//div[@class='in-review-patients']//mat-row[1]//mat-cell[3]//button")
    public WebElement viewPatientBtnFromPendingQAReview;

    @FindBy(id = "patient_653736_reopen-for-curation-button")
    public WebElement reopenBtn;

    @FindBy(xpath = "//h1[contains(text(),'Curation In Progress')]")
    public WebElement curationInProgressText;

    @FindBy(xpath = "//h1[@class='project-title']")
    public WebElement projectTitle;

    @FindBy(xpath = "//button[@id='new-patient-button']")
    public WebElement newPateintBtn;

    @FindBy(xpath = "//*[contains(text(),'Continue Curation')]")
    public WebElement contCurationBtn;

    @FindBy(xpath = "//a[contains(text(),'Log Out')]")
    public WebElement logOut;

    @FindBy(xpath = "//div[@class='mat-slide-toggle-thumb']")
    public WebElement qaToggle;

    @FindBy(xpath = "//div[contains(text(),'PATIENT NAME')]")
    public WebElement patientNameLabel;

    @FindBy(xpath = "//span[@class='QA-toggle-text ng-star-inserted']")
    public WebElement QAMode;

    @FindBy(xpath = "//div[@class='main-section-label']")
    public WebElement OtherActions;

    @FindBy(xpath = "//button[@id='patient_698057_resume-flagged-curation-button']")
    public WebElement P698057_Resume_Review;

    @FindBy(xpath = "//*[@class='flagged-patients']//mat-table[@class='mat-table'][1]//mat-row[1]//mat-cell[3]//button")
    public WebElement resumeFlaggedCurationButton;

    @FindBy(xpath = "//*[@class='in-review-patients']//mat-table[@class='mat-table'][1]//mat-row[1]//mat-cell[3]//button")
    public WebElement pendingQAReviewViewPatient;

    @FindBy(xpath = "//*[@class='correction-needed-patients']//mat-table[@class='mat-table'][1]//mat-row[1]//mat-cell[3]//button")
    public WebElement patientFromCorrectionNeeded;

    @FindBy(xpath = "//*[@class='completed-patients']//mat-table[@class='mat-table'][1]//mat-row[1]//mat-cell[3]//button[2]")
    public WebElement reopenPatient;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-3 ng-star-inserted']")
    public WebElement sortArrow;



    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c15-3 ng-star-inserted']")
    public WebElement patchFlaggedSortArrow;

    @FindBy(id = "patient_657317_resume-flagged-curation-button")
    public WebElement patient657317;

    @FindBy(xpath = "//div[@class='patient-mrn-label']")
    public WebElement patientMrnLabel;

    @FindBy(id = "patient_365935_resume-flagged-curation-button")
    public WebElement patient_365935;
}
