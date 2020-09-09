package hospitalization;

import base.TestBase;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.*;
import utility.CommonMethods;

import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * @author Pooja Sah
 * Updated 04/14/2020
 */

public class hospitalizationTest extends TestBase {


    //This will test if any hospitalization source form is available for the patient, if available it will save the form
    // Match the data with the database. If source form is not available it will skip the test

     @Test(priority = 1)
    public void pod_2138_SourceFormSaveTest() throws Exception {

        testLogin();
        //driver.manage().window().maximize();

        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();

        //Total Hospitalization Count from Summary table
        Hospitalization hp = new Hospitalization(driver);
        int count = hp.hospitalizationViewFormCount(driver);

        System.out.println("TOTAL COUNT OF REPORT >>>>>>>>>"+count);
        //Thread.sleep(5000);

        // If total Hospitalization Form Count is "0" it will skip this test
        if(count==0){
            System.out.println(">>>>>>>>>>>>>  NO SOURCE DATA OR NEW DATA OR PREVIOUS DATA AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
            logExtentReport(">>>>>>>>>>>>>  NO SOURCE DATA OR NEW DATA OR PREVIOUS DATA AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
        }

        //If any hospitalization form is available, it will check if its came from Source or Not!
        else {

            //Thread.sleep(5000);
            try {
               // Thread.sleep(5000);
                //Now this funtion will try to find the source data from the summery table
                String reply = hp.findingNewSourceForm(driver, count);

                // If we find the source form it will collect the UI Information and will try to match with Database
                if (reply.equals("SUCCESS")) {
                    LinkedHashMap<String, String> map = hp.collectInformationFromUI();
                    for (String key : map.values()) {
                        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from UI " + key);
                        logExtentReport(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from UI " + key);
                    }

                    //Thread.sleep(5000);
                    //Now Making a Database query and trying to find the record for same form
                    HospitalizationDatabaseTest hdt = new HospitalizationDatabaseTest();
                    LinkedHashMap<String, String> map1 = hdt.sourceDataSaveChecking(PID);

                    for (String key : map1.values()) {
                        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from Database " + key);
                        logExtentReport(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from Database " + key);
                    }
                    Assert.assertEquals(map, map1, "Hospitalization Source Data is Not Saving Correctly");
                    test.log(Status.PASS, "Hospitalization Source Data Saved Correctly");

                } else {
                    System.out.println("NO NEW SOURCE DATA AVAILABLE OR ALREADY ACCEPTED, SKIPPING TEST ");

                }
            } catch (Exception e) {
                System.out.println("NO NEW SOURCE DATA AVAILABLE OR ALREADY ACCEPTED, SKIPPING TEST >> "+ e);
            }

        }

    }

    // This test will match available UI count of reports with Database Count of Reports!
    @Test(priority = 2)
    public void countOfReportsTest() throws Exception {
        //testLogin();
        //********** if you want to test only this test please activate the commented section///
        System.out.println(">>>>>>>>>>>    Testing Save Button!");
        logExtentReport("Hospitalization Section Save Button Testing");

        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();

        //Total Hospitalization Count from Summary table
        Hospitalization hp = new Hospitalization(driver);
        int uiCount = hp.hospitalizationViewFormCount(driver);
        logExtentReport("UI COUNT >>>>>> "+ uiCount);

        HospitalizationDatabaseTest hdt = new HospitalizationDatabaseTest();
        int  databaseCount = hdt.countOfReports(PID);
        logExtentReport("DATABASE COUNT >>>>>> "+ databaseCount);

        Assert.assertEquals(uiCount,databaseCount, "Report Count is Not Matching");
        test.log(Status.PASS,"REPORT COUNT MATCHING! Database Count >>> "+databaseCount+ " >>>>> UI COUNT >>>> "+uiCount);

        Curation cs = new Curation(driver);
        cs.flagPatient("countOfReports Test Completed");
        Thread.sleep(5000);
        cs.LogOut();
        Thread.sleep(5000);

    }


    // This test will  Insert Valid Data to the Hospitalization Form and Save it! Than it will match the UI data with Database Data.
    @Test(priority = 3)
    public void pod_2140_hospitalizationFormSaveTest() throws Exception {
        testLogin();
        //********** if you want to test only this test please activate the commented section///
        System.out.println(">>>>>>>>>>>    Testing Save Button!");
        logExtentReport("Hospitalization Section Save Button Testing");
        Hospitalization hp = new Hospitalization(driver);
        hp.hospitalizationSummeryTableClick();
        Thread.sleep(5000);
        hp.newForm();
        //Filling all the fields of the UI Form
        LinkedHashMap<String, String> map = hp.dataEntry();
        for (String key : map.values()) {
            logExtentReport("Reply from UI >>>>>>> " + key);
            System.out.println("Reply from UI >>>>>>> " + key);
        }

        //Clicking Save Button
        hp.saveButtonClick();
        Thread.sleep(10000 );

        //Connecting to Database to Collect Patient ID
        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();
        System.out.println(">>>>>>>>>>>>>" + PID);

        // Connecting to Database to Collect the Saved Data of the Form
        HospitalizationDatabaseTest hpdt = new HospitalizationDatabaseTest();
        LinkedHashMap<String, String> map1 = hpdt.hospitalizationDatabaseTest(PID);

        for (String key : map1.values()) {
            logExtentReport("Reply from Database >>>>>>> " + key);
            System.out.println("Reply from Database >>>>>>> " + key);
        }

        // Matching UI Data with Database Data
        Assert.assertEquals(map, map1, "Save Button is Not Saving Hospitalization Data Correctly");
        test.log(Status.PASS, "Save Button is Saving Hospitalization Data Correctly");

        //Deleting the Form
        hp.deleteButtonClick();
        Thread.sleep(5000);

        //After Deleting the Form we need to check the data is deleted from Database or Not!
        String actual = hpdt.deleteQuery(PID);
        System.out.println(actual);
        String expected = "RECORD DELETED";

        Assert.assertEquals(actual,expected,"RECORD NOT DELTED");
        test.log(Status.PASS,"NO RECORD FOUND ON IN THE DATABASE FOR DELETED FORM" );

    }

    //This test will check hospitalization curation directive is loading or not
    @Test (priority = 4)
    public void pod_2139_hospitalizationToggleButtonTest() throws Exception{
        //testLogin();
        Hospitalization hp = new Hospitalization(driver);
        hp.hospitalizationSummeryTableClick();
        //hp.hideNavBar();
        hp.newForm();
        // wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);

        // Clicking the toggle button to switch off to Disable the Directive
        hp.curationDirectivesToggleClick();
        String actualText = hp.directiveText();

        System.out.println(">>>>>>>>>>> " + actualText);
        String emptyText = "Empty Text";
        logExtentReport(actualText);
        Assert.assertEquals(actualText, emptyText, "Not Matching");
        test.log(Status.PASS, "No Directive Found!! Curation Directive is OFF Now");

        // Clicking the toggle button to Switch On to Enable the Directive
        hp.curationDirectivesToggleClick();
        String realText = hp.directiveText();
        String expectedText = "Enter the Admission Date for the Hospitalization";
        System.out.println(">>>>>>>>>>> " + realText);
        logExtentReport(realText);
        Assert.assertEquals(realText, expectedText, "Not Matching");
        test.log(Status.PASS, "Directive Found! Curation Directive is ON Now");

    }

    // This test will check if the Item Directive Button is working and item directive is displaying dictionary data or not.
    @Test(priority = 5)
    public void pod_2141_itemDirectiveButtonTest() throws InterruptedException {
        //testLogin();
        //Taking focus to the Hospitalization section
        Hospitalization hp = new Hospitalization(driver);
       // hp.hospitalizationSummeryTableClick();

        System.out.println(">>>>>>>>>>>    Testing Hospitalization Item Directive Button !");
        logExtentReport("Testing Hospitalization Item Directive Button!");

        String actualItem = hp.itemDirectiveButtonClick();
        System.out.println(">>>>>>>>>>>>>>>>>>>>> " +actualItem);
        String expectedItem = " Day Estimated ";
        Assert.assertEquals(actualItem,expectedItem,"Item Directive is not Loaded!");
        test.log(Status.PASS,"Item Directive is Loaded Successfully");

        //Delete the Form
        hp.deleteButtonClick();
        Thread.sleep(5000);

    }

    //This Test will compare last saved date and time with current saved date and time.
    @Test(priority = 6)
    public void pod_2142_lastUpdatedDateTest() throws Exception {
         //testLogin();
         //Taking focus to the Hospitalization section
        Hospitalization hp = new Hospitalization(driver);
        hp.hospitalizationSummeryTableClick();

        //Opening a new form
        //hp.hideNavBar();
        hp.newForm();

        //Changing the Focus to the Form by clicking toggle button
        hp.curationDirectivesToggleClick();

        //Entering New Data
        LinkedHashMap<String, String> map = hp.dataEntry();
        for (String key : map.values()) {
            logExtentReport("Reply from UI >>>>>>> " + key);
            System.out.println("Reply from UI >>>>>>> " + key);
        }

        //Clicking Save Button
        hp.saveButtonClick();
        Thread.sleep(5000 );

        //Checking Last Update Date
        String actualDt = hp.lastUpdateDate();
        System.out.println(actualDt);
        logExtentReport("Form Last Saved Date is : " + actualDt);
        Calendar rightNow = Calendar.getInstance();

        java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM/dd/YY");

        String expecteddt = df1.format(rightNow.getTime());
        System.out.println(expecteddt);
        logExtentReport("Form Last Saved Expected Date is : " + actualDt);
        Assert.assertEquals(actualDt, expecteddt, "Save Date is not Matching!");
        test.log(Status.PASS, "Saved Date and Current Date is Matching!");

        //Deleting the form
        hp.deleteButtonClick();
        Thread.sleep(5000);

        //Flagging the Patient

        Curation cs = new Curation(driver);
        cs.flagPatient("AUTOMATION TESTING DONE BY ");
        Thread.sleep(5000);
        cs.LogOut();
        Thread.sleep(5000);

    }


    // This Test will check the Delete Button is working or not.
//    @Test(priority = 6)
//    public void pod_2143_deleteButtonTest() throws InterruptedException {
//
//        try{
//
//            Hospitalization hp = new Hospitalization(driver);
//            hp.deleteButtonClick();
//            Thread.sleep(5000);
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//        Curation cs = new Curation(driver);
//        cs.flagPatient("DO NOT CLOSE THIS PATIENT! PLEASE! This patient is the part of automation testing ");
//
//    }


}
