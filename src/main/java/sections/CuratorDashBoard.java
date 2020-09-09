package sections;

import base.TestBase;

import helper.JavaScriptHelper;
import helper.LoggerHelper;
import locators.CuratorDashBoardLocators;
import locators.PatientLocators;
import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ObjectReader;
import helper.WaitHelper;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Shariar Ahmed
 * @author Pooja Shah
 */

@Log4j
public class CuratorDashBoard extends CuratorDashBoardLocators {
    private WebDriver driver;

    //private final Logger log = LoggerHelper.getLogger(WorkFlowPage.class);

    WaitHelper wh;
    JavaScriptHelper jh;
    PatientLocators pl;

    public CuratorDashBoard(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        pl = new PatientLocators();
        TestBase.logExtentReport("WorkFlow Page Object Created");
        new TestBase().getNavigationScreen(driver);

    }

    public void selectingPatientForCuration() throws InterruptedException {
        String env = ObjectReader.reader.env();

        if (env.equals("st")) {
            System.out.println("<<<<<<<<<<<<<<< THIS IS STAGING >>>>>>>>>>>>>>>>>>>>>>>>>>");
        } else if (env.equals("qa")) {
            System.out.println("<<<<<<<<<<<<<<< THIS IS QA >>>>>>>>>>>>>>>>>>>>>>>>>>");
        } else if (env.equals("prod")) {
            System.out.println("<<<<<<<<<<<<<<< THIS IS PRODUCTION >>>>>>>>>>>>>>>>>>>>>>>>>>");
            jh.hightligtandCLick(patient_365935);
            Thread.sleep(5000);
        } else if (env.equals("dev")) {
            System.out.println("<<<<<<<<<<<<<<< THIS IS DEVELOPMENT >>>>>>>>>>>>>>>>>>>>>>>>>>");
        } else if (env.equals("patch")) {
            System.out.println("<<<<<<<<<<<<<<< THIS IS PATCH ENVIRONMENT >>>>>>>>>>>>>>>>>>>>>>>>>>");
            try {
                jh.hightligtandCLick(contCurationBtn);
                wh.WaitForElementVisibleWithPollingTime(patientNameLabel, 30, 5);
            } catch (Exception e) {
                System.out.println("+++++++++++++++++  " + e);
                jh.hightligtandCLick(patchFlaggedSortArrow);
                //jh.hightligtandCLick(patchFlaggedSortArrow);
                jh.hightligtandCLick(resumeFlaggedCurationButton);
                wh.WaitForElementVisibleWithPollingTime(patientMrnLabel, 30, 5);
            }
        } else {
            System.out.println("<<<<<<<<<<<<<<< NO ENVIRONMENT SELECTED >>>>>>>>>>>>>>>>>>>>>>>>>>");
        }


        try {
            jh.hightligtandCLick(contCurationBtn);
            wh.WaitForElementVisibleWithPollingTime(patientNameLabel, 30, 5);
        } catch (Exception e) {
            System.out.println("+++++++++++++++++  " + e);
            jh.hightligtandCLick(sortArrow);
            //jh.hightligtandCLick(sortArrow);
            jh.hightligtandCLick(resumeFlaggedCurationButton);
            Thread.sleep(5000);
            wh.WaitForElementVisibleWithPollingTime(patientMrnLabel, 30, 5);
        }


    }

    public boolean curationContinueBtn() {

        contCurationBtn.isEnabled();
        log.info("Curation Continue Button is Clicking");
        jh.highLightElement(contCurationBtn);
        contCurationBtn.click();
        return true;
    }

    // 352510


    // LogOut //
    public void logOutApplicaiton() {

        log.info("logging out");
        logOut.click();
        TestBase.logExtentReport("Logout Successful");
    }

    public boolean qaToggle() {

        try {
            wh.WaitForElementVisibleWithPollingTime(qaToggle, 30, 5);
            jh.hightligtandCLick(qaToggle);
            // wh.WaitForElementVisibleWithPollingTime(otherActionLabel,5,5);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void onlyNewPatient() {

        System.out.println("Im here 4");
        jh.highLightElement(newPateintBtn);
        jh.clickElement(newPateintBtn);
        wh.WaitForElementVisibleWithPollingTime(patientNameLabel, 30, 5);
    }

    public String collectProjectTitle() {

        String pjtitle = projectTitle.getText();
        return pjtitle;

    }

    public String quModeStatus() throws Exception {

        String qamodestatus = "";
        try {

            try {
                QAMode.isDisplayed();
                qamodestatus = "POWER USER";
                return qamodestatus;

            } catch (Exception e) {
                OtherActions.isDisplayed();
                qamodestatus = "QA";
                return qamodestatus;
            }

        } catch (Exception e) {
            qamodestatus = "Curator";
            return qamodestatus;
        }


    }

    public boolean resumeFlaggedPatient() {

        try {
            wh.WaitForElementVisibleWithPollingTime(resumeFlaggedCurationButton, 30, 5);
            jh.hightligtandCLick(resumeFlaggedCurationButton);
            System.out.println("Clicked Resume Patient from Flagged Status....");
            return true;
        } catch (Exception e) {
            System.out.println("No Patient is Available for Resume Patient Button Check");
            return false;
        }

    }

    public boolean viewPatientBtnFromPendingQAReviewClick() {
        try {
            wh.WaitForElementVisibleWithPollingTime(pendingQAReviewViewPatient, 30, 5);
            jh.scrollIntoViewAndClick(pendingQAReviewViewPatient);
            // jh.hightligtandCLick(pendingQAReviewViewPatient);
            System.out.println("Pending QA Review Button Checked!");
            return true;
        } catch (Exception e) {
            System.out.println("No Patient is Available for View Patient Button Check");
            return false;
        }

    }

    public String curationInProgressText() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(curationInProgressText, 5, 5);
        jh.hightligtandCLick(curationInProgressText);
        Thread.sleep(5000);
        return curationInProgressText.getText();
    }

    public boolean resumeCorrectionNeededClick() throws Exception {

        try {
            wh.WaitForElementVisibleWithPollingTime(patientFromCorrectionNeeded, 30, 5);
            System.out.println("Clicking Resume Correction needed Button....");
            jh.scrollIntoViewAndClick(patientFromCorrectionNeeded);
            //jh.clickElement(patientFromCorrectionNeeded);

            Patient ps = new Patient(driver);
            Thread.sleep(5000);
            String MRN = ps.collectDFCIMRN();
            System.out.println(MRN);
            return true;
        } catch (Exception e) {
            System.out.println("No Patient is Available for Correction");
            return false;
        }

    }

    public boolean reopenButtonClick(WebDriver driver) throws InterruptedException {
        boolean status = true;
        try {

            String firstPath = "//*[@class='completed-patients']//mat-table[@class='mat-table'][1]//mat-row[";
            String secondPath = "]//mat-cell[3]//button[2]";
            System.out.println("Building XPATH to location Reopen Patient...");

            int counter = 1;


            List<WebElement> count = driver.findElements(By.xpath("//*[@class='completed-patients']//mat-table[@class='mat-table'][1]//mat-row"));
            System.out.println(count.size());


            for (int i = 0; i <= count.size(); i++) {
                System.out.println("Looking for Reopen Patient on Row " + counter);
                String completePath = firstPath + counter + secondPath;
                WebElement reopenButton = driver.findElement(By.xpath(completePath));
                Thread.sleep(5000);
                if (reopenButton.isEnabled()) {
                    System.out.println("Found Reopen Patient on Row " + counter);
                    TestBase.logExtentReport("Found Reopen Patient on Row " + counter);

                    // wh.WaitForElementVisibleWithPollingTime(reopenButton,30,5);
                    jh.scrollIntoViewAndClick(reopenButton);
                    // jh.hightligtandCLick(reopenButton);

                    Patient ps = new Patient(driver);
                    Thread.sleep(5000);
                    String MRN = ps.collectDFCIMRN();
                    System.out.println(MRN);
                    status = true;
                    //reopenButton.click();

                    break;
                } else {
                    //System.out.println("Step 10 D");
                    counter++;
                }
                //System.out.println("Step 10 E");
            }
        } catch (Exception e) {
            System.out.println("No Patient Available for Reopen!");
            status = false;
        }
        return status;
    }


    public int countResumeCurationButtons(WebDriver driver) throws InterruptedException {

        //Thread.sleep(5000);
        List<WebElement> count = driver.findElements(By.xpath("//*[@class='flagged-patients']//mat-table[@class='mat-table'][1]//mat-row"));
        int totalCount = count.size();
        System.out.println(count.size());
        return totalCount;

    }


    public boolean viewPatientButtonFromCurationCompletedtoDateSection(WebDriver driver) throws InterruptedException {
        boolean status = true;
        try {

            System.out.println("Building XPATH to location View Patient Button from Reopen Patient...");
            String firstPath = "//*[@class='completed-patients']//mat-table[@class='mat-table'][1]//mat-row[";
            String secondPath = "]//mat-cell[3]//button[1]";


            int counter = 1;

            List<WebElement> count = driver.findElements(By.xpath("//*[@class='completed-patients']//mat-table[@class='mat-table'][1]//mat-row"));
            System.out.println(count.size());


            for (int i = 0; i <= count.size(); i++) {
                System.out.println("Looking for View Patient Button form Curation Completed to Date Section Row No " + counter);
                String completePath = firstPath + counter + secondPath;
                WebElement reopenViewButton = driver.findElement(By.xpath(completePath));

                //wh.WaitForElementVisibleWithPollingTime(reopenViewButton,30,5);
                if (reopenViewButton.isEnabled()) {
                    System.out.println("Found View Patient Button form Curation Completed to Date Section Row " + counter);
                    TestBase.logExtentReport("Found View Patient Button form Curation Completed to Date Section Row " + counter);

                    jh.scrollIntoViewAndClick(reopenViewButton);
                    //jh.hightligtandCLick(reopenViewButton);
                    Patient ps = new Patient(driver);
                    Thread.sleep(5000);
                    String MRN = ps.collectDFCIMRN();
                    System.out.println(MRN);
                    status = true;
                    //reopenViewButton.click();

                    break;
                } else {
                    //System.out.println("Step 10 D");
                    counter++;
                }
                //System.out.println("Step 10 E");
            }
        } catch (Exception e) {
            System.out.println("No Patient Available for View Patient from Curation Completed to Date Section!");

            status = false;
        }
        return status;
    }

//        if(QAMode.isDisplayed()){
//        qamodestatus = QAMode.getText();
//        System.out.println(">>>>>" +qamodestatus);
//
//    }else if(otherActionLabel.isDisplayed()) {
//        qamodestatus = OtherActions.getText() ;
//        System.out.println(">>>>"+ qamodestatus);
//
//    }else {
//        System.out.println("Not Enabled");
//        qamodestatus="Not Enabled";
//    }

    public void openSpecificPatient(WebDriver driver, String MRN) throws InterruptedException {
        String buildResumeButton = "patient_" + MRN + "_resume-flagged-curation-button";
        System.out.println(">>>>>>>>>>>>>>>>>> Build Button >>>>>>>>>>>>>> " + buildResumeButton);
        WebElement resumeButton = driver.findElement(By.id(buildResumeButton));
        jh.hightligtandCLick(resumeButton);
        Thread.sleep(5000);
    }

    public boolean openSpecificPatientInProduction(WebDriver driver, String MRN) throws InterruptedException {
        String buildResumeButton = "patient_" + MRN + "_resume-flagged-curation-button";
        System.out.println(">>>>>>>>>>>>>>>>>> Build Button >>>>>>>>>>>>>> " + buildResumeButton);
        WebElement resumeButton = driver.findElement(By.id(buildResumeButton));
        if (resumeButton.isEnabled()) {

            jh.hightligtandCLick(resumeButton);
            Thread.sleep(5000);
            return true;
        } else {
            return false;
        }

    }


}
