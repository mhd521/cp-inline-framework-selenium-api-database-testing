package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QALocators {

    @FindBy(xpath = "//span[contains(text(),'Assign Curators')]")
    public WebElement AssignCuroatorsButton;


    @FindBy(xpath = "//span[contains(text(),'Return to Project Selection')]")
    public WebElement ReturnToProjectSelection;

    @FindBy(xpath = "//span[contains(text(),'Continue Review')]")
    public WebElement ContinueReview;

    @FindBy(xpath = "//div[@class='flagged-patients section-container']//mat-row[1]//mat-cell[4]//button")
    WebElement FlaggedPatientReviewButton;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-475 ng-star-inserted']")
    WebElement FlaggedTableSortArrow;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-483 ng-star-inserted']//div[@class='mat-sort-header-pointer-right ng-trigger ng-trigger-rightPointer']")
    WebElement PendingQAReviewSortArrow;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-489 ng-star-inserted']//div[@class='mat-sort-header-stem']")
    WebElement QAReviewedReturnedToTheCorrectionSortArrow;

    @FindBy(xpath = "//div[@class='in-review-patients section-container']//mat-row[1]//mat-cell[4]//button")
    public WebElement PendingQAReviewButton;

    @FindBy(xpath = "//div[@class='correction-needed-patients section-container']//mat-row[1]//mat-cell[4]//button")
    public WebElement CorrectionNeededViewPatientButton;

    @FindBy(xpath = "//div[@class='mat-slide-toggle-thumb']")
    public WebElement selectQAMode;

    @FindBy(xpath = "//h1[contains(text(),'QA In Progress')]")
    public WebElement qaInProgressText;

    @FindBy(id = "correction-needed-button")
    public WebElement CorrectionNeededButton;

    @FindBy(id = "approve-curation-button")
    public WebElement Approve;

    @FindBy(id = "return-to-main-menu")
    public WebElement ReturnToTheMainMenu;

    @FindBy(xpath = "//span[@class='patient-mrn-info']")
    public WebElement MRN;

    @FindBy(xpath = "//div[@class='main-section-label ng-star-inserted']")
    public WebElement AssignCuratorsLabel;

    @FindBy(xpath = "//div[@class='assign-unassign-container']")
    public WebElement assignUnassignContainer;

    @FindBy(xpath = "//h3[@class='assign-unassign-info-text']")
    public WebElement assignUnassignInfoText;

    @FindBy(xpath = "//button[@class='pop-accept-button accept-check mat-raised-button mat-button-base']")
    public WebElement assignUnassignPopUpAcceptButton;

    @FindBy(xpath = "//textarea[@id='mrns-input']")
    public WebElement mrnInputArea;

    @FindBy(id = "clear-assignee-button")
    public WebElement clearAssignee;

    @FindBy(id = "submit-assign-curators-button")
    public WebElement SubmitButton;

    @FindBy(id = "assignee-input")
    public WebElement assignee;

    @FindBy(xpath = "//span[contains(text(),'Service, ONCOTracker-DFCI')]")
    public WebElement oncoTracker;

    @FindBy(id = "correction-needed-button")
    public WebElement correctionNeeded;

    @FindBy(xpath = "//textarea[@placeholder='Add Comment']")
    public WebElement correctionNeededTextArea;

    @FindBy(xpath = "//div[@class='start_dt_time_stamp']")
    public WebElement dateandtime;

    @FindBy(xpath = "//button[@id='comment-accept-button']")
    public WebElement correctionNeededAcceptButton;

    @FindBy(xpath = "//button[@id='flag-patient-button']")
    public WebElement flagButton;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-25 ng-star-inserted']")
    public WebElement flaggedSortArrow;

    @FindBy(xpath = "//div[@class='flagged-patients section-container']//mat-row[1]//mat-cell[4]//button")
    public WebElement reviewButton;

    @FindBy(xpath = "//div[@class='in-review-patients section-container']//mat-row[1]//mat-cell[4]//button")
    public WebElement pendingQAReviewButton;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-133 ng-star-inserted']")
    public WebElement pendingQAReviewsSortArrow;

    @FindBy(xpath = "//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c14-139 ng-star-inserted']")
    public WebElement correctionNeededSortArrow;

    @FindBy(xpath = "//div[@class='flagged-patients section-container']//button[@class='mat-paginator-navigation-last mat-icon-button mat-button-base ng-star-inserted']")
    public WebElement lastPage;

    @FindBy(xpath = "//div[@class='correction-needed-patients section-container']//button[@class='mat-paginator-navigation-last mat-icon-button mat-button-base ng-star-inserted']")
    public WebElement correctionNeededLastPage;

    @FindBy(xpath = "//html[1]/body[1]/app-root[1]/app-app-container[1]/div[1]/app-patient-selection[1]/div[1]/app-qa-wrapper[1]/div[1]/div[1]/app-qa[1]/div[5]/app-patient-selection-table[1]/div[1]/mat-paginator[1]/div[1]/div[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]/div[1]/div[1]\n")
    public WebElement itemsPerPageCurationCompleteToDate;

    @FindBy(xpath = "//span[contains(text(),'50')]")
    public WebElement select50;

    @FindBy(css = "body > app-root > app-app-container > div > app-patient-selection > div.patient-selection-container > app-qa-wrapper > div > div > app-qa > div.completed-patients.section-container > app-patient-selection-table > div > mat-paginator > div > div > div.mat-paginator-range-actions > div")
    public WebElement range;

    public String curationComepleteToDateTableRow = "//div[@class='completed-patients section-container']//mat-row";

    @FindBy(xpath = "//div[@class='completed-patients section-container']//button[@class='mat-paginator-navigation-next mat-icon-button mat-button-base']")
    public WebElement curationCompleteToDateNextPage;

    @FindBy(xpath = "//div[@class='new-comment']")
    public WebElement newComment;

    @FindBy(xpath = "//span[contains(text(),'Flag Patient')]")
    public WebElement flagPatient;

    @FindBy(id = "approve-curation-button")
    public WebElement acceptButton;
}
