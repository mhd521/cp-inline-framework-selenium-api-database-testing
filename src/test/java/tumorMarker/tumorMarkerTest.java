package tumorMarker;

import base.TestBase;
import com.aventstack.extentreports.Status;
import database.DatabaseTesting;
import listener.Retry;
import lombok.extern.log4j.Log4j;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.*;
import utility.CommonMethods;

import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/05/2020
 */


@Log4j
public class tumorMarkerTest extends TestBase {

    //This test will count the Tumor Marker Report from UI and Match it With Database Count

    @Test(priority = 1)
    public void POD_2115_totalTumorMarkerCountTest() throws Exception {
        testLogin();

        //Connecting to Database for Getting the PID

        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();
        System.out.println(">>>>>>>>>>>>>" + PID);
        logExtentReport("Patient PID "+PID);

        //Total Tumor Marker Count from UI Notes Panel
        TumorMarker tm = new TumorMarker(driver);
        int count = tm.tumorMarkerTableRowCount(driver);


        boolean status = tm.addTumorMarkerFormClick();
        Assert.assertTrue(status,"Tumor Marker Form Not Created");
        test.log(Status.PASS, "Tumor Marker Blank Form Successfully Created");

        int countOldReport = tm.tumorMarkerSummeryTableRowCount(driver);

        int totalUICount = count+countOldReport;
        System.out.println(">>>>>>>>>>>>>> " + totalUICount);
        logExtentReport("Total Tumor Marker Report Count in UI = "+totalUICount);


        Curation cp = new Curation(driver);
        cp.completCurationClick();

        Thread.sleep(5000);

       // Connecting to Database for Total Tumor Marker Count
        TumorMarkerDatabaseTesting tmdt = new TumorMarkerDatabaseTesting();
        int count1 = tmdt.tumorMarkerCountfromDatabase(PID);

        System.out.println(">>>>>>>>>>>>>> " + count1);


        logExtentReport("Total Tumor Marker Report Count in Database = "+count1);
        Assert.assertEquals(totalUICount,count1,"Tumor Marker Total Count of Report is not Matching!");
        test.log(Status.PASS,"Tumor Marker Total Count of the Report is Matching!");
    }


    //This Test will Check if we can create the Blank Tumor Marker Form Or Not
    // This test is discontiuned! Because we have incorporated this tests with the previous test.
//    @Test(priority = 2, groups = {"smoke", "regression"})
//    public void POD_2116_tumorMarkerBlankFormCreationTest() throws Exception {
//        Thread.sleep(10000);
//        logExtentReport("Curation Directive Toggle Button Testing");
//
//        //Its a continuous test, no need to activate testlogin(); If you are testing this test method alone, activate it.
//        //testLogin();
//
//        TumorMarker tm = new TumorMarker(driver);
//        //Clicking the Nevigation Panel
//        tm.tumorMarkerSummeryClick();
//        Thread.sleep(5000);
//        boolean status = tm.addTumorMarkerFormClick();
//
//        //Asserting that we can create the blank form
//        Assert.assertTrue(status,"Tumor Marker Form Not Created");
//        test.log(Status.PASS, "Tumor Marker Blank Form Successfully Created");
//
//    }


    //This Test will check if the Tumor Marker Curation Directive Toggle Button is Working or Not
    @Test (priority = 2)
    public void POD_2117_tumorMarkerToggleButtonTest() throws Exception{


        TumorMarker tm = new TumorMarker(driver);

        tm.viewFormOrNewForm();

        // Clicking the toggle button to switch off to Disable the Directive
        tm.curationDirectivesToggleClick();
        String actualText = tm.directiveText();

        System.out.println(">>>>>>>>>>> " + actualText);
        String emptyText = "Empty Text";
        Assert.assertEquals(actualText, emptyText, "Not Matching");
        test.log(Status.PASS, "No Directive Found!! Curation Directive is OFF Now");


        // Clicking the toggle button to Switch On to Enable the Directive
        tm.curationDirectivesToggleClick();
        String realText = tm.directiveText();

        String expectedText = "Select the name of the Tumor Marker Test";
        System.out.println(">>>>>>>>>>> " + realText);
        Assert.assertEquals(realText, expectedText, "Not Matching");
        test.log(Status.PASS, "Directive Found! Curation Directive is ON Now");
    }


    // This test will  Insert Valid Data to the Tumor Marker Form and Save it! Than it will match the UI data with Database Data.
    @Test(priority = 3)
    public void POD_2118_tumorMarkerFormSaveTest() throws Exception {

        //********** if you want to test only this test please activate the commented section///
        System.out.println(">>>>>>>>>>>    Testing Save Button!");
        logExtentReport("Tumor Marker Section Save Button Testing");
        TumorMarker tm = new TumorMarker(driver);
        tm.viewFormOrNewForm();
        //Filling all the fields of the UI Form
        LinkedHashMap<String, String> map = tm.dataEntry();

        for (String key : map.values()) {
            logExtentReport("Reply from UI >>>>>>> " + key);
            System.out.println("Reply from UI >>>>>>> " + key);
        }



        //Clicking Save Button
        tm.saveButtonClick();


        //Connecting to Database to Collect Patient ID
        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();
        // Converting MRN to PID
        System.out.println(">>>>>>>>>>>>>" + PID);


        // Connecting to Database to Collect the Saved Data of the Form
        TumorMarkerDatabaseTesting tmdt = new TumorMarkerDatabaseTesting();
        LinkedHashMap<String, String> map1 = tmdt.tumorMarkerDatabaseTest(PID);

       for (String key : map1.values()) {
            logExtentReport("Reply from Database >>>>>>> " + key);
            System.out.println("Reply from Database >>>>>>> " + key);
        }
        // Matching UI Data with Database Data
        Assert.assertEquals(map, map1, "Save Button is Not Saving Tumor Marker Data Correctly");
        test.log(Status.PASS, "Save Button is Saving Tumor Marker Data Correctly");

    }

    // This test will check if the Item Directive Button is working and item directive is displaying dictonary data or not.
    @Test(priority = 4)
    public void POD_2119_itemDirectiveButtonTest() throws InterruptedException {

        System.out.println(">>>>>>>>>>>    Testing Tumor Marker Item Directive Button !");
        logExtentReport("Testing Tumor Marker Item Directive Button!");

        TumorMarker tm = new TumorMarker(driver);
        String actualItem = tm.itemDirectiveButtonClick();
        System.out.println(">>>>>>>>>>>>>>>>>>>>> " +actualItem);
        String expectedItem = "AFP";
        Assert.assertEquals(actualItem,expectedItem,"Item Directive is not Loaded!");
        test.log(Status.PASS,"Item Directive is Loaded Successfully");
    }

    //This Test will compare last saved date and time with current saved date and time.
    @Test(priority = 5)
    public void POD_2120_lastUpdatedDateTest() throws Exception {

        // testLogin();
        TumorMarker tm = new TumorMarker(driver);

        tm.curationDirectivesToggleClick();

        String actualDt = tm.lastUpdateDate();

        System.out.println(actualDt);
        logExtentReport("Form Last Saved Date is : " + actualDt);
        Calendar rightNow = Calendar.getInstance();

        java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM/dd/YY");

        String expecteddt = df1.format(rightNow.getTime());

        System.out.println(expecteddt);
        logExtentReport("Form Last Saved Expected Date is : " + actualDt);
        Assert.assertEquals(actualDt, expecteddt, "Save Date is not Matching!");
        test.log(Status.PASS, "Saved Date and Current Date is Matching!");
    }


    // This Test will check the Delete Button is working or not.
    @Test(priority = 6)
    public void POD_2121_deleteButtonTest() throws InterruptedException {

        try{

            TumorMarker tm = new TumorMarker(driver);
            tm.deleteButtonClick();

        }catch (Exception e){
            System.out.println(e);
        }

        Curation cs = new Curation(driver);
        cs.flagPatient("DO NOT CLOSE THIS PATIENT! PLEASE! This patient is the part of automation testing ");

        Thread.sleep(5000);
        cs.LogOut();

        Thread.sleep(5000);

    }



//    @Test
//    public void countofexistingreport() throws  Exception{
//        testLogin();
//        TumorMarker tm = new TumorMarker(driver);
//        int count = tm.tumorMarkerSummeryTableRowCount(driver);
//        System.out.println(count);
//
//    }

//
//
//    @Test(priority = 3)
//    public void POD_2072_noOfFieldsTest() throws Exception {
////********** if you want to test only this test please activate the commented section///
//        //testLogin();
//        TumorMarker tm = new TumorMarker(driver);
//        int count = tm.countOfHistoryIcons(driver);
//        System.out.println(count);
//        if (count == 8) {
//            Assert.assertEquals(count, 8, "Count of History Button is Right!");
//            test.log(Status.PASS, "All the Fields are appeared >>>" + count);
//
//        } else {
//            Assert.assertEquals(count, "Count of History Button is Wrong!");
//            test.log(Status.PASS, "All the Fields are not displayed>>>" + count);
//
//        }
//
//    }
//
//
//



}
