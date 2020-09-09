package cancer;

import base.TestBase;
import com.aventstack.extentreports.Status;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.Cancer;
import sections.CancerProfile;
import sections.Curation;
import utility.CommonMethods;
import utility.WriteHashmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shariar Ahmed
 * Updated 04/29/2020
 */


@Log4j
public class CancerTest extends TestBase {

    @Test(priority = 1)
    public void acceptSourceCancerEpisodeTest() throws Exception {

        testLogin();

        //Thread.sleep(30000);

        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();

        Cancer cn = new Cancer(driver);
        cn.cancerSummeryTableClick();
        Thread.sleep(1000);

        int count = cn.cancerSummeryTableRowCount(driver);
        takeScreenShotWhenEver(PID);
        System.out.println(">>>>>>>>>>>>>> " + count);
        System.out.println("TOTAL COUNT OF REPORT >>>>>>>>>" + count);
        logExtentReport("TOTAL COUNT OF REPORT >>>>>>>>>" + count);

        if (count == 0) {

            System.out.println(">>>>>>>>>>>>>  NO SOURCE CANCER OR NEW CANCER OR PREVIOUSLY CREATED CANCER EPISODES AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
            test.log(Status.SKIP, ">>>>>>>>>>>>>  NO SOURCE CANCER OR NEW CANCER OR PREVIOUSLY CREATED CANCER EPISODES AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
            logExtentReport(">>>>>>>>>>>>>  NO SOURCE DATA OR NEW DATA OR PREVIOUS DATA AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
        } else {
            logExtentReport(">>>>>>>>>>>>>  CANCER EPISODES FOUND SOURCE DATA OR NEW DATA OR PREVIOUS DATA AVAILABLE TO TEST >>>>>>>>>>>>>>>>>>");
            String reply = cn.findingCancerEpisodesFromSource(driver, count);
            System.out.println(reply);

            if (reply.equals("SUCCESS")) {
                logExtentReport(">>>>>>>>>>>>>  CANCER EPISODES FOUND FROM SOURCE  >>>>>>>>>>>>>>>>>>");

                //Fill Up the Cancer Form and Return a HashMap with the Values of Saved Field
                LinkedHashMap<String, String> map = cn.cancerFormFillUp(driver);
                takeScreenShotWhenEver(reply);

                String filePath = "src/test/resources/testdata/SoureCancerFiledsCheck.txt";
                // Send that Hash Map to in a TXT File to Build the SQL Query for Database Result Serch
                WriteHashmap wrh = new WriteHashmap();
                wrh.writeToAHashMap(map, filePath);

                // Now send the TXT file to Create a Database SQL Query
                CancerDatabaseTest cdt = new CancerDatabaseTest();
                LinkedHashMap<String, String> map1 = cdt.cancerDataReadFromDatabase(PID, filePath);

                logExtentReport(">>>>>>>>>>>>>  MATCHING UI DATA WITH DATABASE  >>>>>>>>>>>>>>>>>>");
                Assert.assertEquals(map, map1, "Map is not same");
                test.log(Status.PASS, "UI and Database Data is Matching! ");

            } else if (reply.equals("NOT SUCCESS")) {
                System.out.println("NO CANCER FOUND FROM SOURCE!");
                test.log(Status.SKIP, ">>>>>>>>>>>>>  NO SOURCE CANCER OR NEW CANCER OR PREVIOUSLY CREATED CANCER EPISODES AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
            }
        }

        logExtentReport(">>>>>>>>>>>>>  DELETING THE CANCER EPISODE  >>>>>>>>>>>>>>>>>>");
        // cn.deleteCancerEpisode();
        takeScreenShotWhenEver("deleteCancerEpisode");
        Curation cs = new Curation(driver);
        cs.flagPatient("Automation Testing at ");
        Thread.sleep(5000);
        cs.LogOut();
        Thread.sleep(5000);

    }


    @Test(priority = 2)
    public void newCancerEpisodeTest() throws Exception {

        testLogin();

        CommonMethods cm = new CommonMethods(driver);
        String PID = cm.collectPID();

        Cancer cn = new Cancer(driver);
        cn.cancerSummeryTableClick();
        logExtentReport(">>>>>>>>>>>>>  CREATING NEW CANCER EPISODE  >>>>>>>>>>>>>>>>>>");
        cn.addCancerEpisode();

        LinkedHashMap<String, String> map = cn.newCancerFormFillUp(driver);
        takeScreenShotWhenEver(PID);

        String filePath = "src/test/resources/testdata/CreateHashMapKeySet.txt";

        WriteHashmap wrh = new WriteHashmap();
        wrh.writeToAHashMap(map, filePath);


        CancerDatabaseTest cdt = new CancerDatabaseTest();
        LinkedHashMap<String, String> map1 = cdt.cancerDataReadFromDatabase(PID, filePath);

        Assert.assertEquals(map, map1, "Map is not same");
        test.log(Status.PASS, "MAP Same");

        cn.onlyDeleteCuratorCancerEpisode();
        takeScreenShotWhenEver("onlyDeleteCuratorCancerEpisode");

        Curation cs = new Curation(driver);
        cs.flagPatient("Automation Testing at ");
        Thread.sleep(5000);
        cs.LogOut();
        Thread.sleep(5000);


    }

//    @Test
////    public void test() throws InterruptedException, SQLException, ClassNotFoundException {
////        CancerDatabaseTest cdt = new CancerDatabaseTest();
////        String PID = "16080";
////        LinkedHashMap<String, String> map = cdt.cancerDataReadFromDatabase(PID,filePath);
////        for(Map.Entry<String, String> entry: map.entrySet()){
////            System.out.println("Step 7");
////            String k = entry.getKey();
////            System.out.println(">>>>>> Reply from Database, Key = "+ k);
////            String v = entry.getValue();
////            System.out.println("<<<<<<< Reply from Database, Value= "+v);
////
////        }
////    }
//
//    @Test(priority = 3)
//    public void CancerProfilingTest() throws Exception {
//        //testLogin();
//        CancerProfile cp = new CancerProfile(driver);
//        cp.CancerProfilingSummeryTable();
//        int rowCount = cp.cancerProfilingSummeryTableRowCount(driver);
//
//        CommonMethods cm = new CommonMethods(driver);
//        String PID = cm.collectPID();
//        System.out.println(PID);
//
//        System.out.println(">>>>>>>>>>>>>> " + rowCount);
//        System.out.println("TOTAL COUNT OF REPORT >>>>>>>>>" + rowCount);
//        logExtentReport("TOTAL COUNT OF REPORT >>>>>>>>>" + rowCount);
//
//            if (rowCount == 0) {
//
//                System.out.println(">>>>>>>>>>>>>  NO SOURCE CANCER OR NEW CANCER OR PREVIOUSLY CREATED CANCER EPISODES AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
//                test.log(Status.SKIP, ">>>>>>>>>>>>>  NO SOURCE CANCER OR NEW CANCER OR PREVIOUSLY CREATED CANCER EPISODES AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
//                logExtentReport(">>>>>>>>>>>>>  NO SOURCE DATA OR NEW DATA OR PREVIOUS DATA AVAILABLE TO TEST, SKIPPING TEST >>>>>>>>>>>>>>>>>>");
//            } else {
//
//                cp.cancerProfileAccept(driver);
//
//                CancerDatabaseTest cdt = new CancerDatabaseTest();
//
//                ArrayList<String> list = cdt.cancerTypeForCancerProfile(PID);
//                     for (String temp : list) {
//                            cp.clickCancerAssiciated();
//                            System.out.println("................ "+temp);
//                            String cancerType = cp.cancerSelection(temp);
//                            System.out.println("Return Full Path >>>>>> "+cancerType);
//                            cp.selectCancerType(driver,cancerType);
//                         Thread.sleep(10000);
//
//                     }
//
//                     //cp.saveForm();
//
//            }
//
//
//    }


}
