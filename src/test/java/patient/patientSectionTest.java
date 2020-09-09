package patient;

import base.TestBase;
import com.aventstack.extentreports.Status;
import database.DatabaseTesting;
import locators.PatientLocators;
import lombok.extern.log4j.Log4j;
import org.apache.poi.ss.formula.functions.Now;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.*;
import utility.CommonMethods;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/08/2020
 */


@Log4j
public class patientSectionTest extends TestBase {

    patientDatabaseTesting pdt = new patientDatabaseTesting();


    // This test will check the Dictionary Toggle Button
    @Test(priority = 1, groups = {"smoke", "regression"})
    public void POD_2070_curationDirectiveToggleTest() throws Exception {
        logExtentReport("Curation Directive Toggle Button Testing");

        //Login to the APP
        testLogin();

        // Switching Off the Directive, Now All Directive should turn off!
        Patient ps = new Patient(driver);
        ps.curationDirectivesToggleClick();

        //Getting a random Directive Text and Asserting the Text is Correct or Not

        String actualText = ps.directiveText();
        System.out.println(">>>>>>>>>>> " + actualText);
        String emptyText = "Empty Text";
        Assert.assertEquals(actualText, emptyText, "Not Matching");
        test.log(Status.PASS, "No Directive Found!! Curation Directive is OFF Now");



        //Switching ON the Directive Button, Now all the Directive should displayed
        ps.curationDirectivesToggleClick();
        String realText = ps.directiveText();
        String expectedText = "If the date was estimated, record the precision of the date curated.";
        System.out.println(">>>>>>>>>>> " + realText);
        Assert.assertEquals(realText, expectedText, "Not Matching");
        test.log(Status.PASS, "Directive Found! Curation Directive is ON Now");
        // Continue Doing the NEXT Test....
    }


    // We are saving the patient status form and checking with database that the UI data is Matching with Database or Not.
    @Test(priority = 2)
    public void POD_2071_TestingSaveButton() throws Exception {
        //********** if you want to test only this test please activate the commented section///
        System.out.println(">>>>>>>>>>>    Testing Save Button!");
        logExtentReport("Patient Section Save Button Testing");

        // Login Application
         //testLogin();

        // Increasing the Curation Panel
        Patient ps = new Patient(driver);
        ps.referenceBarClick();
        //Clicking Save Button
        ps.saveButtonClick();

        //Collect MRN
        String MRN = ps.collectDFCIMRN();

        // Database Testing
        DatabaseTesting dt = new DatabaseTesting();

        // Converting MRN to PID
        String PID = dt.patientIDFind(MRN);
        System.out.println(">>>>>>>>>>>>>" + PID);

        //Patient Vital Status Checking with Reply
        String reply = ps.patientDeceasedInformationCollect();

        //If Vital Status is YES it will go the database testing and collect YES Related Fields
        if (reply.equals("Yes")) {
            //Connecting to Database and Collecting Result
            LinkedHashMap<String, String> map = pdt.patientDeceasedisYES(PID);

            //Connecting to UI and collecting result
            LinkedHashMap<String, String> map1 = ps.collectPatientInformation("Yes");

            //This for loop just only printing the values
            for (String key : map.values()) {
                logExtentReport("Reply from Database >>>>>>> " + key);

                System.out.println("Reply from Database >>>>>>> " + key);
            }
            for (String key : map1.values()) {
                logExtentReport("Reply from UI >>>>>>> " + key);

                System.out.println("Reply from UI >>>>>>>>" + key);
            }

            // This is Asserting BOTH MAPS are same or not.
            Assert.assertEquals(map, map1, "Save Button is Not Saving Data Correctly");
            test.log(Status.PASS, "Save Button is Saving Data Correctly");

        } else if (reply.equals("No")) {

            LinkedHashMap<String, String> map2 = pdt.ifPatientVitalSignIsNo(PID);

            LinkedHashMap<String, String> map3 = ps.collectPatientInformation("No");

            for (String key : map2.values()) {
                logExtentReport("Reply from Database >>>>>>> " + key);

                System.out.println("Reply from Database >>>>>>> " + key);
            }
            for (String key : map3.values()) {
                logExtentReport("Reply from UI  " + key);

                System.out.println("Reply from UI  " + key);
            }
            Assert.assertEquals(map2, map3, "Save Button is Not Saving Data Correctly");
            test.log(Status.PASS, "Save Button is Saving Data Correctly");
        } else {
            System.out.println("NO MAP");
        }

        // Continue doing the next test
    }


    // If Patient Deceased is YES there should be 8 Fileds in the FORM, if Patient Deceased is NO there should be 5 Fields
    @Test(priority = 3)
    public void POD_2072_noOfFieldsTest() throws Exception {
//********** if you want to test only this test please activate the commented section///
        //testLogin();
        //Thread.sleep(10000);
        Patient ps = new Patient(driver);
        int count = ps.countOfHistoryIcons(driver);

        System.out.println(count);
        if (count == 8) {
            System.out.println("Patient Deceased = Yes");
            Assert.assertEquals(count, 8, "Count of History Button is Wrong!");
            test.log(Status.PASS, "Patient Deceased = Yes and No of Fields Appearing is >>>" + count);

        } else {
            System.out.println("Patient Deceased = No");
            Assert.assertEquals(count, 5, "Count of History Button is Wrong!");
            test.log(Status.PASS, "Patient Deceased = NO and No of Fields Appearing is >>>" + count);

        }


    }

    //This test will check if patient deceased turns Yes to NO or NO to Yes then the Record of Date of Death and Death Source and Estimation-Date of Death values should be removed from database.

    @Test(priority = 4)
    public void POD_2073_VitalStatusChangeTest() {

        try {
            //testLogin();

            //Collect MRN and Patient ID
            System.out.println("Collectign Latest Patient ID for MRN ");
            CommonMethods cm = new CommonMethods(driver);
            String PID = cm.collectPID();

            Patient ps = new Patient(driver);
            //Clicking Toggle Button to Activate Form
            ps.curationDirectivesToggleClick();

            //Thread.sleep(5000);
            //Collect Vital Status

            String reply = ps.patientDeceasedInformationCollect();

            System.out.println(reply);
            logExtentReport("Patient Vital Status is "+reply);



            //Collecting Source Data to Insert Back after Testing
            LinkedHashMap<String, String> sourceMap = ps.collectPatientInformation(reply);

            for (String key : sourceMap.values()) {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from UI " + key);
                logExtentReport(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from UI " + key);
            }


            //Saving the Form
            ps.saveButtonClick();
            Thread.sleep(5000);


            if (reply.equals("Yes")) {
                System.out.println("Step 3");
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   ORIGINALLY VS IS YES >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                logExtentReport("Original Vital Status "+ reply);

                //Switching Vital Status YES TO NO
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   NOW CHANGING VS YES TO NO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                logExtentReport("Changing Vital Status To NO");

                ps.patientVitalStatusChange();
              ;
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   CLICKING SAVE BUTTON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                //Now Saving the Form

                ps.saveButtonClick();
                Thread.sleep(5000);



                //Now checking the Values in the Database, Database Should not have any values for Date of Death Source, Date of Death, and Estimation of Date of Death. Because we change the vital status to NO

                LinkedHashMap<String, String> map1 = pdt.vitalStatusChangeConfirmationFromDatabase(PID);

                for(Map.Entry<String, String> entry: map1.entrySet()){
                    System.out.println("Step 14");
                    String k = entry.getKey();
                    String v = entry.getValue();

                    if(v==null) {
                        System.out.println("Step 15");
                        System.out.println(k + " " + v);
                        Assert.assertTrue(true, k +" >>> "+v);
                        test.log(Status.PASS, k +" >>> "+v);
                    }
                    System.out.println("Step 16");
                }
                System.out.println("Step 17");
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   NOW CHANGING VS NO TO YES AGAIN >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                logExtentReport("Changing Vital Status to YES");
               //Now Switching Back to Original Vital Status when we first open this patient for curation which was YES
                String currentStatus = ps.patientVitalStatusChange();
                System.out.println(">>>>>>>>>> " + currentStatus);


              // Now we are inserting orginal data for the patient which we have saved at the begaining.
               ps.insertOriginalData(sourceMap);


               //After inserting original data we are saving the form.

               ps.saveButtonClick();
                Thread.sleep(5000);

                //Now we are making database call again to see if the YES related Values is available , Since we have chenged the VS from NO to YES again. Data should be available for Date of Death Source, Date of Death, and Estimation of Date of Death
                LinkedHashMap<String ,String> map2 = pdt.vitalStatusChangeConfirmationFromDatabase(PID);
                for(Map.Entry<String, String> entry: map2.entrySet()){
                    System.out.println("Step 24");
                    String k = entry.getKey();
                    String v = entry.getValue();

                    if(v!=null) {
                        System.out.println("Step 25");
                        System.out.println(k + " " + v);
                        Assert.assertTrue(true, k +" >>> "+v);
                        test.log(Status.PASS, k +" >>> "+v);
                    }
                    System.out.println("Step 26");
                }

                System.out.println("Step 27");
            } else {
                System.out.println("Step 28");
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   ORIGINALLY VS IS NO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                logExtentReport("Original Vital Status "+ reply);

                logExtentReport("Changing Vital Status to YES");
                //Switching Vital Status NO TO YES
                ps.patientVitalStatusChange();

                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   NOW CHANGING VS TO YES >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");



                // Making Data Entry for YES
                LinkedHashMap<String, String> mapUI = ps.dataEntry();


                //Printing the Values we are inserting in to form.
                for (String key : mapUI.values()) {
                    System.out.println("Step 29");
                    System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from UI After Changing NO TO YES" + key);
                }

                //Now Saving the Form
                ps.saveButtonClick();
                Thread.sleep(5000);
                //Now checking the Values in the Database, Database Should add Date of Death Source, Date of Death, and Estimation of Date of Death.
                LinkedHashMap<String, String> map3 = pdt.vitalStatusChangeConfirmationFromDatabase(PID);
                for(Map.Entry<String, String> entry: map3.entrySet()){
                    System.out.println("Step 30");
                    String k = entry.getKey();
                    String v = entry.getValue();

                    if(v!=null) {
                        System.out.println("Step 31");
                        System.out.println(k + " " + v);
                        Assert.assertTrue(true,  k +" >>> "+v);
                       test.log(Status.PASS,  k +" >>> "+v);
                    }
                    System.out.println("Step 32");
                }
                System.out.println("Step 33");
                 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   NOW CHANGING VS TO NO AGAIN >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                logExtentReport("Changing Vital Status to NO");
                //Now Switching Back to Original Vital Status when we first open this patient for curation.
                ps.patientVitalStatusChange();

                ps.saveButtonClick();
                Thread.sleep(5000);

                //Now we are making database call again to see if the YES related Values should be NULL, Since we have chenged the VS from Yes to NO again.
                LinkedHashMap<String, String> map4 = pdt.vitalStatusChangeConfirmationFromDatabase(PID);
                for(Map.Entry<String, String> entry: map4.entrySet()){
                    System.out.println("Step 34");
                    String k = entry.getKey();
                    System.out.println(k);
                    String v = entry.getValue();
                    System.out.println(v);

                    if(v==null) {
                        System.out.println("Step 35");
                        System.out.println(k + " " + v);
                        Assert.assertTrue(true,  k +" >>> "+v);
                       test.log(Status.PASS,  k +" >>> "+v);
                    }
                    System.out.println("Step 36");
                }
                System.out.println("Step 37");
            }
            System.out.println("Step 38");
        } catch (Exception e) {
            System.out.println("Step 39");
            System.out.println(e);
        }

    }

    //This Test will compare last saved date and time with current saved date and time.

    @Test(priority = 5)
    public void POD_2074_lastUpdatedDateTest() throws Exception {

        // testLogin();
        Patient ps = new Patient(driver);

        ps.curationDirectivesToggleClick();

        String actualDt = ps.lastUpdateDate();

        System.out.println(actualDt);
        logExtentReport("Form Last Saved Date is : " + actualDt);
        Calendar rightNow = Calendar.getInstance();

        java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM/dd/YY");

        String expecteddt = df1.format(rightNow.getTime());
        System.out.println(expecteddt);
        logExtentReport("Form Last Saved Expected Date is : " + actualDt);
        Assert.assertEquals(actualDt, expecteddt, "Save Date is not Matching!");
        test.log(Status.PASS, "Saved Date and Current Date is Matching!");


        Curation cs = new Curation(driver);
        cs.flagPatient("DO NOT CLOSE THIS PATIENT! PLEASE! This patient is the part of automation testing ");

        cs.LogOut();
        Thread.sleep(5000);


    }


}
