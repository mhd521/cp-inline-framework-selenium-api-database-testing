package qa;

import QAMode.QACuration;
import QAMode.QADashboard;
import QAMode.QALogin;
import base.TestBase;
import com.aventstack.extentreports.Status;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.Curation;

import java.io.IOException;

@Log4j
public class QAButtonTests extends TestBase {

    @Test(priority = 1)
    public void POD_2162_continueReviewButtonTest() throws Exception {
        qaLogin();
        QALogin qal = new QALogin(driver);
        qal.clickQAMode();
        QADashboard qad = new QADashboard(driver);
        boolean status = qad.continueReviewClick();
        if (status) {
            QACuration qac = new QACuration(driver);
            System.out.println("Step 1");
            String MRN = qac.MRNInfo();
            takeScreenShotWhenEver("POD_2162_continueReviewButtonTest");
            System.out.println(">>>>>>>>> " + MRN);
            qac.flag_correction_needed();
            Thread.sleep(5000);
            Assert.assertNotNull(MRN, "CURATION CONTINUE BUTTON NOT WORKING");
            test.log(Status.PASS, "Curation Continue Button is Working");

        } else {

            System.out.println("CURATION CONTINUE BUTTON IS NOT ACTIVE!");
            logExtentReport("CURATION CONTINUE BUTTON NOT AVAILABLE, TEST SKIPPED!");
            test.log(Status.SKIP, "CURATION CONTINUE BUTTON NOT AVAILABLE, TEST SKIPPED!");

        }
    }

    @Test(priority = 2)
    public void POD_2163_assignCuratorButtonTest() throws Exception {
//        qaLogin();
//        QALogin qal = new QALogin(driver);
//        qal.clickQAMode();
        QADashboard qad = new QADashboard(driver);
        qad.assignCurators();
        String actualLabel = qad.assignCuratorsLabel();
        takeScreenShotWhenEver("POD_2163_assignCuratorButtonTest");
        System.out.println(">>>>>>>>>>> "+ actualLabel);
        String expectedLabel = "Assign Curators";
        Assert.assertEquals(actualLabel,expectedLabel,"Label Not Matching!");
        test.log(Status.PASS,"Assign Curator Button is Working!");
    }

    @Test(priority = 3)
    public void POD_2164_assignPatientToCuratorTest() throws InterruptedException, IOException {

        QADatabaseTesting qadt = new QADatabaseTesting();
        String MRN = qadt.patientForAssignCurator();
        takeScreenShotWhenEver("POD_2164_assignPatientToCuratorTest");
        System.out.println(MRN);
        QADashboard qad = new QADashboard(driver);
        qad.assignMRN(MRN);
        qad.selectAsignee();
        String actual = qad.verifyAsignee();
        takeScreenShotWhenEver(actual);
        System.out.println(">>>>>>>>>>>> "+actual);
        String expected = "Assignment complete.";

        Assert.assertEquals(actual,expected,"Assign Not Completed!");
        test.log(Status.PASS,MRN+"Correctly Assigned!");
        qad.returnToProjectSelectionPage();

    }

    @Test(priority = 4)
    public void POD_2165_flaggedReviewButtonTest() throws Exception {
        QADashboard qad = new QADashboard(driver);
        qad.flaggedPatientButton();
        takeScreenShotWhenEver("flaggedReviewButtonTest");
        QACuration qac = new QACuration(driver);
        String MRN = qac.MRNInfo();
        System.out.println(">>>>>>>>> " + MRN);
        qac.flag_correction_needed();
        Thread.sleep(5000);
        Assert.assertNotNull(MRN, "FLAGGED REVIEW PATIENT BUTTON IS NOT WORKING");
        test.log(Status.PASS, "FLAGGED REVIEW BUTTON IS WORKING");
    }

    @Test(priority = 5)
    public void POD_2166_pendingQAReviewButtonTest() throws Exception {
//        qaLogin();
//        QALogin qal = new QALogin(driver);
//        qal.clickQAMode();
        QADashboard qad = new QADashboard(driver);
        boolean status = qad.pendingQAReview();

        if(status){
            QACuration qac = new QACuration(driver);
            String MRN = qac.MRNInfo();
            takeScreenShotWhenEver(MRN);
            System.out.println(">>>>>>>>> " + MRN);
            qac.flag_correction_needed();

            Assert.assertNotNull(MRN, "PENDING QA REVIEW BUTTON IS NOT WORKING");
            test.log(Status.PASS, "PENDING QA REVIEW BUTTON IS WORKING");
        }else {
            System.out.println("No Review Button Available at Pending QA Review Section");
            test.log(Status.SKIP, "No Review Button Available at Pending QA Review Section");
        }


    }

    @Test(priority = 6)
    public void POD_2167_acceptButtonTest() throws Exception {
//        qaLogin();
//        QALogin qal = new QALogin(driver);
//        qal.clickQAMode();
        QADashboard qad = new QADashboard(driver);
        boolean status = qad.pendingQAReview();
        takeScreenShotWhenEver("POD_2167_acceptButtonTest");

        if(status){
            QACuration qac = new QACuration(driver);
            String MRN = qac.MRNInfo();
            takeScreenShotWhenEver(MRN);
            System.out.println(">>>>>>>>> " + MRN);
            String actual = qac.acceptButtonClick();
            System.out.println("............" +actual);
            String expected = "QA In Progress";

            Assert.assertEquals(actual,expected,"Accept Button is Not Working");
            test.log(Status.PASS, "Accept Button is Working");
        }else {
            System.out.println("Accept Button is Not Available!");
            test.log(Status.SKIP, "Accept Button is Not Available!");
        }


    }
    @Test(priority = 7)
    public void POD_2168_qaReviewedReturnedForCorrectionButtonTest() throws Exception {
//        qaLogin();
//        QALogin qal = new QALogin(driver);
//        qal.clickQAMode();
        QADashboard qad = new QADashboard(driver);
        boolean status = qad.qaReviewedReturnToTheCorrection();

        if(status){
            QACuration qac = new QACuration(driver);
            String MRN = qac.MRNInfo();
            takeScreenShotWhenEver(MRN);
            System.out.println(">>>>>>>>> " + MRN);
            Curation cs = new Curation(driver);
            cs.returnToMainMenuBtnClick();
            Thread.sleep(5000);
            Assert.assertNotNull(MRN, "View Patient Button at QA Reviewed, Returned for Correction Section NOT WORKING");
            test.log(Status.PASS, "View Patient Button at QA Reviewed, Returned for Correction Section IS WORKING");
        }else {
            System.out.println("No View Patient Button Available at QA Reviewed, Returned for Correction Section");
            test.log(Status.SKIP, "No View Patient Button Available at QA Reviewed, Returned for Correction Sectionn");
        }


    }

    @Test(priority = 8)
    public void POD_2169_viewPatientfromCurationCompletedtoDateButtonTest() throws Exception {
//        qaLogin();
//        QALogin qal = new QALogin(driver);
//        qal.clickQAMode();

        //Trying to Find View Patient Button from the Completed To Date Section
        QADashboard qad = new QADashboard(driver);
        boolean status = qad.curtionCompletetoDateViewPatientButtonSearch(driver);

        if(status){
            QACuration qac = new QACuration(driver);
            String MRN = qac.MRNInfo();
            takeScreenShotWhenEver(MRN);
            System.out.println(">>>>>>>>> " + MRN);
            Curation cs = new Curation(driver);
            cs.returnToMainMenuBtnClick();
            Thread.sleep(5000);
            Assert.assertNotNull(MRN, "View Patient Button at QA Reviewed, Returned for Correction Section NOT WORKING");
            test.log(Status.PASS, "View Patient Button at QA Reviewed, Returned for Correction Section IS WORKING");
        }else {
            System.out.println("No View Patient Button Available at QA Reviewed, Returned for Correction Section");
            test.log(Status.SKIP, "No View Patient Button Available at QA Reviewed, Returned for Correction Section");
        }
    }

}
