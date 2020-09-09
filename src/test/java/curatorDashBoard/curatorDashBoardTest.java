package curatorDashBoard;

import base.TestBase;
import com.aventstack.extentreports.Status;
import helper.WaitHelper;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.Curation;
import sections.CuratorDashBoard;
import utility.ObjectReader;
import utility.UserRoles;


/**
 * @author Shariar Ahmed
 * @author Pooja Shah
 * Updated 04/06/2020
 */


@Log4j
public class curatorDashBoardTest extends TestBase {

    WaitHelper wh;



    @Test(priority = 1, groups = "regression")
    public void POD2026_New_Patient_Continue_Curation_Btn_Test() throws Exception {
        // Calling the App and Login
        testLogin();
        Curation c = new Curation(driver);
        String MRN = c.patientMRNLabelDisplay();
        if (MRN != null) {
            System.out.println(">>>>>>>>> " + MRN);
            Assert.assertNotNull(MRN);
            test.log(Status.PASS, "Buttons Working");
        } else {
            test.log(Status.FAIL, "test Failed");
        }

        c.flagPatient("POD2026_New_Patient_Continue_Curation_Btn_Tested at ");


    }



    @Test(priority = 2, groups = "regression")
    public void POD2027_All_Curator_Workflow_Button_Test() throws Exception {

        //testLogin();
        Curation cs = new Curation(driver);

       // cs.flagPatient("Flagging the Existing Patient!");
        CuratorDashBoard wf = new CuratorDashBoard(driver);

        //Resume Flagged patient Button Checking
        try{
            boolean reply1;
            if(ObjectReader.reader.env().equals("prod")){
                reply1 = wf.openSpecificPatientInProduction(driver,"3635935");
            }else{
                reply1 = wf.resumeFlaggedPatient();
            }

            if (reply1) {
                boolean status = cs.flagButton();
                takeScreenShotWhenEver("POD2027_All_Curator_Workflow_Button_Test");
                Assert.assertTrue(status, "Resume Curation from Flagged Patient Button is not Working");
                test.log(Status.PASS, "Resume Curation from Flagged Patient Button is Working");
            } else {
                System.out.println("Skipping Test");
                TestBase.logExtentReport("Skipping Resume Curation Flagged Test because No Patient Available!");
            }
        }catch (Exception e){
            System.out.println(e);
        }


       try{
            // View Patient Button Check
            boolean reply2 =  wf.viewPatientBtnFromPendingQAReviewClick();

            try {
                if(reply2){
                    boolean reply = cs.flagButtonAvailability();
                    takeScreenShotWhenEver("viewPatientBtnFromPendingQAReviewClick");
                    Assert.assertFalse(reply,"Flag Button Found!");
                    test.log(Status.PASS, "View Patient Button is Working");
                    cs.returnToMainMenuBtnClick();
                }else {
                    System.out.println("Skipping Test");
                    TestBase.logExtentReport("Skipping View Patient Button Test because No Patient Available!");
                }
            } catch (Exception e) {
                System.out.println(e);
                cs.returnToMainMenuBtnClick();
            }

        }catch (Exception e){
            System.out.println(e);
        }

        try{

            //Resume Curation From Correction Needed Button
            boolean reply3 = wf.resumeCorrectionNeededClick();
            takeScreenShotWhenEver("resumeCorrectionNeededClick");
            if (reply3) {
                System.out.println("Step 8 ");
                boolean status = cs.flagButton();
                Assert.assertTrue(status, "Resume Correction Needed Button is not Working");
                test.log(Status.PASS, "Resume Correction Needed Button is not Working");
            } else {
                //System.out.println("Step 9 ");
                System.out.println("Skipping Test");
                TestBase.logExtentReport("Skipping Correction Needed Test because No Patient Available!");
            }
        }catch (Exception e){

        }

        //System.out.println("Step10 ");

        try{
            //Reopen Button Check

            boolean result4 = wf.reopenButtonClick(driver);
            takeScreenShotWhenEver("reopenButtonClick");
            if(result4){

                //System.out.println("Step 12 ");
                boolean status = cs.flagButton();

                Assert.assertTrue(status, "Resume Correction Needed Button is not Working");
                test.log(Status.PASS, "Resume Patient Needed Button is not Working");
            }else {
                //System.out.println("Step 9 ");
                System.out.println("Skipping Test");
                TestBase.logExtentReport("Skipping Reopen Test because No Patient Available!");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        //View Patient from Reopen Button Check
       try{

           boolean result5 = wf.viewPatientButtonFromCurationCompletedtoDateSection(driver);
           takeScreenShotWhenEver("viewPatientButtonFromCurationCompletedtoDateSection");
           try {
               if(result5){

                   boolean reply = cs.flagButtonAvailability();

                   Assert.assertFalse(reply);
                   test.log(Status.PASS, "View Patient Button is Working From Curation to Date Section");

                   cs.returnToMainMenuBtnClick();
               }else {
                   System.out.println("Skipping Test");
                   TestBase.logExtentReport("Skipping View Patient Button is Working From Curation to Date Section because No Patient Available!");
               }

           } catch (Exception e) {
               System.out.println(e);

               cs.returnToMainMenuBtnClick();
           }
       }catch (Exception e){
           System.out.println(e);
       }
    }

//    @Test(priority = 0, groups = "regression")
//    public void POD2025_Curator_QA_Power_User_Login_Test() throws Exception {
//        // testLogin();
//        //Thread.sleep(5000);
//
//        //Checking Status of the User
//        CuratorDashBoard wf = new CuratorDashBoard(driver);
//        String qamodestatus = wf.quModeStatus();
//        System.out.println(qamodestatus);
//
//        // Finding User Roles
//        String username = ObjectReader.reader.getUserName();
//        UserRoles ur = new UserRoles();
//        String roles = ur.Roles(username);
//
//        switch (qamodestatus) {
//
//            case "QA MODE":
//
//                Assert.assertEquals(roles, qamodestatus, "He is Not A Power User");
//                test.log(Status.PASS, username + " Your Are a Power User");
//                // actualTestResult ="POWER USER";
//
//                break;
//            case "Other Actions":
//                Assert.assertEquals(roles, qamodestatus, "He is Not A QA User");
//                test.log(Status.PASS, username + " Your Are a QA User");
//                //actualTestResult ="QA";
//                break;
//
//            case "Curator":
//                Assert.assertEquals(roles, qamodestatus, "He is Not A Curator");
//                test.log(Status.PASS, username + " Your are a curator");
//                //actualTestResult ="PASS";
//                break;
//
//            case "Null":
//                Assert.assertEquals(roles, qamodestatus, "He is Valid User");
//                test.log(Status.PASS, username + " Not A Valid User");
//
//        }
//
//    }


}

