package QAMode;

import base.TestBase;
import com.aventstack.extentreports.Status;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.QALocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import sections.Curation;
import sections.Patient;
import sections.Projects;
import utility.ObjectReader;

import java.util.List;


@Log4j
public class QADashboard extends QALocators {
    private WebDriver driver;
    WaitHelper wh;
    JavaScriptHelper jh;

    public QADashboard(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        jh = new JavaScriptHelper(driver);
        // wh.waitForElement(signin, ObjectReader.reader.getExplicitWait());
        TestBase.logExtentReport("We are at QA Deshboard");
        // driver.manage().window().maximize();
    }

    public boolean continueReviewClick() throws InterruptedException {
        boolean status;
        try{
            jh.hightligtandCLick(ContinueReview);
            System.out.println("Continue Curation Button is Available!");
            TestBase.logExtentReport("Continue Curation Button is Available!");
            Thread.sleep(5000);
            status =true;

        }catch (Exception e){
            System.out.println("Continue Curation Button is not Available!");
            TestBase.logExtentReport("Continue Curation Button is Not Available!");
            status=false;
        }
        return status;
    }

    public void assignCurators(){
        wh.WaitForElementVisibleWithPollingTime(AssignCuroatorsButton,30,5);
        jh.hightligtandCLick(AssignCuroatorsButton);
    }

    public String assignCuratorsLabel(){
        wh.WaitForElementVisibleWithPollingTime(AssignCuratorsLabel,30,5);
        String label = AssignCuratorsLabel.getText();
        return label;
    }




    public void approve() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(Approve,30,5);
        jh.hightligtandCLick(Approve);
        Thread.sleep(5000);
    }

    public boolean qaInProgressText(){

            if(qaInProgressText.isDisplayed()){
                return true;
            }else {
               return false;
            }

    }

    public void assignMRN(String MRN){

        jh.hightligtandCLick(mrnInputArea);
        mrnInputArea.sendKeys(MRN);

    }

    public void selectAsignee() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(assignee,30,5);
        jh.hightligtandCLick(assignee);
        wh.WaitForElementVisibleWithPollingTime(oncoTracker,30,5);
        jh.hightligtandCLick(oncoTracker);
        wh.WaitForElementVisibleWithPollingTime(SubmitButton,30,5);
        jh.hightligtandCLick(SubmitButton);
        Thread.sleep(5000);
    }

    public String verifyAsignee(){
        wh.WaitForElementVisibleWithPollingTime(assignUnassignContainer,30,5);
        jh.hightligtandCLick(assignUnassignContainer);
        wh.WaitForElementVisibleWithPollingTime(assignUnassignInfoText,30,5);
        String info = assignUnassignInfoText.getText();
        wh.waitWith30SecondAndClickElement(assignUnassignPopUpAcceptButton);
        return info;

    }

    public void returnToProjectSelectionPage() throws InterruptedException {
        jh.hightligtandCLick(ReturnToProjectSelection);
        Projects pj = new Projects(driver);
        pj.selectProject();
        Thread.sleep(5000);

    }

    public void flaggedPatientButton() throws InterruptedException {
        //jh.hightligtandCLick(lastPage);
       // Thread.sleep(5000);
        jh.hightligtandCLick(reviewButton);
        Thread.sleep(5000);

    }

    public boolean pendingQAReview() throws InterruptedException {
        boolean status;

        try{
            jh.hightligtandCLick(PendingQAReviewButton);
            Thread.sleep(5000);
            status = true;
        }catch (Exception e){
            System.out.println("No Review Button Available at Pending QA Review Section");
            status = false;
        }

        return status;
    }


    public boolean qaReviewedReturnToTheCorrection() throws Exception{

        boolean status;

        try{
            jh.hightligtandCLick(CorrectionNeededViewPatientButton);
            Thread.sleep(5000);
            status = true;
        }catch (Exception e){
            System.out.println("No View Patient Button Available at QA Reviewed, Returned for Correction Section");
            status = false;
        }

        return status;

    }

    public boolean rowSearch() throws InterruptedException {

        boolean status = false;

        System.out.println("Building XPATH to location View Patient Button from Curation Completed to Date...");
        String firstPath = "//div[@class='completed-patients section-container']//mat-row[";
        String secondPath = "]//mat-cell[5]//button";

        System.out.println("Step 1");
        int counter = 1;
        List<WebElement> count = driver.findElements(By.xpath(curationComepleteToDateTableRow));
        int rowcount = count.size();
        System.out.println(count.size());

       try{

           for(int j=0; j<=rowcount; j++){
               System.out.println("Step 2");
               System.out.println("Looking for View Patient Button form Curation Completed to Date Section Row No " + counter);
               String completePath = firstPath + counter + secondPath;
               WebElement ViewPatient = driver.findElement(By.xpath(completePath));
               System.out.println(ViewPatient);

               if (ViewPatient.isEnabled()) {
                   System.out.println("Step 3");
                   System.out.println("Found View Patient Button form Curation Completed to Date Section Row " + counter);
                   TestBase.logExtentReport("Found View Patient Button form Curation Completed to Date Section Row " + counter);
                   jh.scrollIntoViewAndClick(ViewPatient);
                   Patient ps = new Patient(driver);
                   wh.WaitForElementVisibleWithPollingTime(ps.patientMRNLabel,30,5);
                   wh.WaitForElementVisibleWithPollingTime(ps.dfciMRN,30,5);
                   String MRN = ps.collectDFCIMRN();
                   System.out.println(MRN);
                   status = true;
                   //ViewPatient.click();
                   break;
               } else {
                   System.out.println("Step 4");
                   //System.out.println("Step 10 D");
                   counter++;
               }
               System.out.println("Step 5");

           }
       }catch (Exception e){
           status = false;
       }
       return status;
    }


    public boolean curtionCompletetoDateViewPatientButtonSearch(WebDriver driver) throws InterruptedException {

        boolean status = false;

        try {
            // Selecting Maximum No of Rows for Pagination
            System.out.println("Step 6");
            Thread.sleep(5000);
            jh.hightligtandCLick(itemsPerPageCurationCompleteToDate);
            Thread.sleep(5000);
            jh.hightligtandCLick(select50);
            Thread.sleep(5000);
            // Finding how many pagination might be necessary
            String ranges = range();
            int rangeCount = rangeCounter(ranges);
            System.out.println("RANGE COUNT >>>> "+rangeCount);

            //Iterating through each pagination
            for (int i=0; i<=rangeCount; i++){
                System.out.println("Step 7");

                // This function will try to find the active view patient button, and if found it will reply true;
                boolean reply =rowSearch();

                if(reply){
                    // if it found view patient button it will discontinue search
                    System.out.println("FOUND");
                    status = true;
                    break;
                }else {

                    // if not found view patient it will continue clicking on the next set of patients
                    jh.hightligtandCLick(curationCompleteToDateNextPage);
                }

            }

        } catch (Exception e) {
            //If not at all found any patient it will return false.
            System.out.println("Step 8");
            System.out.println("No Patient Available for View Patient from Curation Completed to Date Section!");
            status = false;
        }
        System.out.println("Step 9");
        return status;
    }


    public String range() throws InterruptedException {
        jh.hightligtandCLick(range);
        Thread.sleep(5000);
        String ranges = range.getText();
        Thread.sleep(5000);
        System.out.println(">>>>>>>>>>" + ranges);
        return ranges;
    }

    public int rangeCounter(String range){
        System.out.println(">>>>>>>>>" + range);

        String[] test;
        int counter = 0;
        try{
            test = range.split(" of ");
            String one = test[0];
            String two = test[1];
            System.out.println(one);
            System.out.println(two);
            int Two = Integer.parseInt(two);
            System.out.println(Two);
            System.out.println(">>>>>>>>" + Two);

            if(Two>51 && Two <100){
                counter = 2;
                System.out.println("More Than 100" + counter);
            }else if (Two >101 && Two <150){
                counter = 3;
                System.out.println("Not More" + counter);
            }else if(Two >151 && Two <1000){
                counter = 4;
                System.out.println("Not More" + counter);
            }
        }catch (Exception e) {
            counter = 1;
            System.out.println("Not More" + counter);
        }
        return counter;
    }

}
