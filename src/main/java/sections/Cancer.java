package sections;

import base.TestBase;
import com.aventstack.extentreports.Status;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.CancerLocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.DateFunctions;
import utility.DateSpliters;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Shariar Ahmed
 * Updated 04/29/2020
 */


@Log4j
public class Cancer extends CancerLocators {

    WaitHelper wh;
    JavaScriptHelper jh;


    public Cancer(WebDriver driver) {
        PageFactory.initElements(driver, this);

        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("Cancer Section");
    }


    public void cancerSummeryTableClick() {
        System.out.println("Im clicking Hospitalization Summery Link");
        wh.WaitForElementVisibleWithPollingTime(referencePanelBar, 30, 5);
        jh.hightligtandCLick(referencePanelBar);
        wh.WaitForElementVisibleWithPollingTime(nevBar, 30, 5);
        jh.hightligtandCLick(nevBar);
        wh.WaitForElementVisibleWithPollingTime(cancerSummeryLink, 30, 5);
        jh.hightligtandCLick(cancerSummeryLink);

    }

    public int cancerSummeryTableRowCount(WebDriver driver) {

        int rows = 0;

        try {

            System.out.println("Step 1");
            jh.highLightElement(cancerTebleRowselection);
            List<WebElement> count = driver.findElements(By.xpath(cancerTableRow));
            rows = count.size();
            return rows;

        } catch (Exception e) {
            System.out.println("Step 2");
            return rows;
        }

    }

    public String findingCancerEpisodesFromSource(WebDriver driver, int count) throws InterruptedException {
        System.out.println("Step 4");
        String reply = null;
        //Thread.sleep(5000);
        int row = 1;
        //Building Unique Path for Admission Type
        String firstPath = "//mat-table[@class='summary-table mat-table']//mat-row[";
        String secondPath = "]//mat-cell[8]";

        //*[@id="cdk-accordion-child-4"]/div/app-cancer-table/div/mat-table/mat-row[2]/mat-cell[8]

        //Building Unique Path for Accept/ViewForm From Source
        String startPath = "//mat-table[@class='summary-table mat-table']//mat-row[";
        String endPath = "]//mat-cell[9]//button[1]";

        for (int i = 1; i <= count; i++) {
            System.out.println("Step 5");
            String completePath = firstPath + row + secondPath;
            Thread.sleep(5000);
            String source = driver.findElement(By.xpath(completePath)).getText();
            System.out.println("Source Type >>>>>>>>" + source);


            if (source.isEmpty()) {
                System.out.println("Step 6");
                System.out.println("Its Not the Source Data , Skipping to the Next Report if any");
                TestBase.logExtentReport("Its Not the Source Data , Skipping to the Next Report if any! Row No " + row);

                reply = "NOT SUCCESS";
                // Thread.sleep(5000);

            }
            else if (source.equals("CORIS")) {
                String acceptButtonPath = startPath + row + endPath;
                WebElement accept_view_form_button = driver.findElement(By.xpath(acceptButtonPath));
                Thread.sleep(5000);

                try {
                    System.out.println("Step 9");
                    //Now Let's Click the Accept or View Form Button
                    jh.hightligtandCLick(accept_view_form_button);
                    Thread.sleep(5000);
                    //We are changing the focus to the form and activate it.
                    wh.WaitForElementVisibleWithPollingTime(curationDirectiveText, 30, 5);
                    jh.hightligtandCLick(curationDirectiveText);
                    System.out.println("Button Clicked on Row No " + row);
                    TestBase.logExtentReport("Button Clicked on Row No " + row);
                    reply = "SUCCESS";
                    break;

                } catch (Exception e) {
                    System.out.println();
                }
            } else if (source.equals("CURATOR")) {
                System.out.println("NOT FROM SOURCE! CURATOR CREATED FORM!");
                reply = "NOT SUCCESS";
            }

            row ++;

        }
        return reply;
    }



    public LinkedHashMap<String,String> cancerFormFillUp(WebDriver driver) throws Exception {

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        jh.hightligtandCLick(cancerType);
        String ct = cancerType.getAttribute("value");


        if (ct.isEmpty()) {
            jh.hightligtandCLick(cancerTypeOption);
        } else {
                System.out.println(">>>>>>>>>>>>> Cancer Type Selected " + ct);

        }
        Thread.sleep(1000);
        map.put("CANCER_TYPE_DESC", cancerType.getAttribute("value"));

        jh.highLightElement(dateofDiagnosisLocked);
        Thread.sleep(1000);
        String dateOfDiagnosis = dateofDiagnosisLocked.getAttribute("value");


        DateFunctions df = new DateFunctions();
        String newCuratedDateOfDiagnosis = df.oneYearsFuture(dateOfDiagnosis);
        System.out.println(".................... "+newCuratedDateOfDiagnosis);
        Thread.sleep(1000);
        String cdod = curatedDateOfDiagnosis.getAttribute("value");

        if(cdod.isEmpty()){
            jh.hightligtandCLick(curatedDateOfDiagnosis);
            curatedDateOfDiagnosis.sendKeys(newCuratedDateOfDiagnosis);
        }

        Thread.sleep(1000);
        DateSpliters ds = new DateSpliters();
        String modifyDate = ds.dateSpliter(curatedDateOfDiagnosis.getAttribute("value"))+ " 00:00:00";

        System.out.println("MODIFY DATE " + modifyDate);
        map.put("DIAGNOSIS_DATE_CURATED", modifyDate);

        //UPDATE ESTIMATION
        updateEstimation();

        jh.hightligtandCLick(sourceOfCuratedDateofDiagnosis);
        Thread.sleep(1000);
        jh.hightligtandCLick(selectImaging);
        Thread.sleep(1000);
        map.put("DIAGNOSIS_DT_SOURCE_DESC", sourceOfCuratedDateofDiagnosis.getAttribute("value"));

        jh.hightligtandCLick(diagnosisInstitution);
        Thread.sleep(1000);
        jh.hightligtandCLick(selectDfci);
        Thread.sleep(1000);
        map.put("DIAGNOSING_INST_DESC", diagnosisInstitution.getAttribute("value"));


        jh.highLightElement(ajccStage);
        Thread.sleep(1000);

        String ajcc = ajccStage.getAttribute("value");

        if (ajcc.equals("Stage IV")) {
           // map.put("AJCC_GROUP_BEST_STAGE", ajcc);
            jh.hightligtandCLick(add1DistantMetastasis);
            Thread.sleep(1000);
            jh.hightligtandCLick(siteOfDistantMetastasisAtDiagnosis);
            Thread.sleep(1000);
            jh.hightligtandCLick(selectUpperLip);
//            map.put("DistantMetastasis",siteOfDistantMetastasisAtDiagnosis.getAttribute("value"));
        }else if (ajcc.equals("Unknown")) {
            System.out.println("AJCC Is UNKNOWN");
        } else {

            System.out.println(ajcc);

        }

        Thread.sleep(5000);
        try {
            System.out.println("INSIDE TRY FOR SSF COMMENTS");
            String message = ssfComments.getText();
            System.out.println(message);
            TestBase.logExtentReport(message);
        } catch (Exception e) {

            int totalSSF = 25;
            System.out.println(">>>>>>>>> Total SSF Available >>>>>>>" + totalSSF);
            String firstPath = "//app-ssf-dictionary[@id='ssf";
            String secondPath = "']//input";
            int ssfCounter = 1;

            for (int i = 0; i <= totalSSF - 1; i++) {
                String buildSSFInput = firstPath + ssfCounter + secondPath;
                System.out.println(">>>>>>>>>>> SSF Path " + buildSSFInput);


                try {
                    WebElement ssf = driver.findElement(By.xpath(buildSSFInput));
                    jh.hightligtandCLick(ssf);
                    jh.hightligtandCLick(selectOption1);
                    String option = ssf.getAttribute("value");
                    String ssf_Desc= "SSF_"+ssfCounter+"_DESC";
                    System.out.println(">>>>>> " + ssf_Desc);

                    System.out.println(option);
                    map.put(ssf_Desc,option);

                } catch (Exception f) {
                    System.out.println("SSF Field is Not Available");
                }
                ssfCounter++;
            }

            jh.hightligtandCLick(saveButtonXpath);
            Thread.sleep(10000);


        }


        for(Map.Entry<String, String> entry: map.entrySet()){
            String k = entry.getKey();
            System.out.println(">>>>>> Key "+ k);

            String v = entry.getValue();
            System.out.println("<<<<<<< Value "+v);
            TestBase.logExtentReport(">>>>>> Reply from UI >>>>> Key = "+ k + ">>>>>> Value = "+v);

        }
        return map;

    }

    public void collectCancerFormUIInformation() {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("CancerType", cancerType.getAttribute("value"));
        map.put("CuratedDateOfDiagnosis", curatedDateOfDiagnosis.getAttribute("value"));
        map.put("SourceofCuratedDateofDiagnosis", sourceOfCuratedDateofDiagnosis.getAttribute("value"));
        map.put("Institution", institution.getAttribute("value"));
        String ajcc = ajccStage.getAttribute("value");
        if (ajcc.isEmpty()) {
            map.put("AJCC", "null");
        } else {
            map.put("AJCC", ajcc);
        }


    }

    public void addCancerEpisode(){
        jh.hightligtandCLick(addCancerEpisode);
        wh.WaitForElementVisibleWithPollingTime(curationDirectiveText, 30, 5);
        jh.hightligtandCLick(curationDirectiveText);

    }

    public LinkedHashMap<String,String> newCancerFormFillUp(WebDriver driver) throws Exception {
        TestBase.logExtentReport(">>>>>>>>>>>>>  FILLING THE NEW CANCER EPISODE  >>>>>>>>>>>>>>>>>>");
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        clickAndSelect(cancerType,breastCancer);
        Thread.sleep(1000);
        map.put("CANCER_TYPE_DESC", cancerType.getAttribute("value"));
        Thread.sleep(1000);


        clickAndSelect(sequenceNo, selectOption1);
        map.put("SEQUENCE_NUMBER_DESC",sequenceNo.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(primarySite, selectOption1);
        map.put("PRIMARY_SITE_DESC",primarySite.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(histologicType, selectOption1);
        map.put("HISTOLOGY_AND_BEHAVIOR_DESC",histologicType.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(diagnosticConfirmation, selectOption1);
        map.put("DIAGNOSIS_CONF_DESC",diagnosticConfirmation.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(laterality,selectOption1);
        map.put("LATERALITY_DESC",laterality.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(grade,selectOption1);
        map.put("GRADE_DESC",grade.getAttribute("value"));

        String cdod = curatedDateOfDiagnosis.getAttribute("value");

        if(cdod.isEmpty()){
            Thread.sleep(1000);
            DateFunctions df = new DateFunctions();
            String currentDt = df.currentDateNow();
            jh.highLightElement(curatedDateOfDiagnosis);
            curatedDateOfDiagnosis.sendKeys(currentDt);
        }

        DateSpliters dt = new DateSpliters();
        String modifyDate = dt.dateSpliter(curatedDateOfDiagnosis.getAttribute("value"))+ " 00:00:00";
        System.out.println("MODIFY DATE " +modifyDate);
        map.put("DIAGNOSIS_DATE_CURATED", modifyDate);
        Thread.sleep(1000);

        updateEstimation();

        Thread.sleep(1000);
        jh.hightligtandCLick(sourceOfCuratedDateofDiagnosis);
        jh.hightligtandCLick(selectImaging);
        map.put("DIAGNOSIS_DT_SOURCE_DESC", sourceOfCuratedDateofDiagnosis.getAttribute("value"));
        Thread.sleep(1000);
        jh.hightligtandCLick(diagnosisInstitution);
        jh.hightligtandCLick(selectDfci);
        map.put("DIAGNOSING_INST_DESC", diagnosisInstitution.getAttribute("value"));
        Thread.sleep(1000);
        jh.highLightElement(ajccStage);
        String ajcc = ajccStage.getAttribute("value");
        Thread.sleep(1000);
       // map.put("AJCC_GROUP_BEST_STAGE", "null");
        //Thread.sleep(1000);
        clickAndSelect(diagnosisStageIV,selectNo);
        map.put("DIAGNOSIS_STAGE_IV_IND_DESC",diagnosisStageIV.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(AjccTnmEdition,selectOption1);
        map.put("AJCC_TNM_VERSION_DESC",AjccTnmEdition.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(clinStageGroup,selectOption1);
        map.put("CLINICAL_STAGE_GROUP_DESC",clinStageGroup.getAttribute("value"));
        Thread.sleep(1000);
        clickAndSelect(pathStageGroup,selectOption1);
        map.put("PATHOLOGIC_STAGE_GROUP_DESC",pathStageGroup.getAttribute("value"));

        Thread.sleep(5000);

        try {
            System.out.println("INSIDE TRY FOR SSF COMMENTS");

            int totalSSF = 25;
            System.out.println(">>>>>>>>> Total SSF Available >>>>>>>" + totalSSF);
            String firstPath = "//app-ssf-dictionary[@id='ssf";
            String secondPath = "']//input";
            int ssfCounter = 1;

            for (int i = 0; i <= totalSSF - 1; i++) {
                String buildSSFInput = firstPath + ssfCounter + secondPath;
                System.out.println(">>>>>>>>>>> SSF Path " + buildSSFInput);


                try {
                    WebElement ssf = driver.findElement(By.xpath(buildSSFInput));
                    jh.hightligtandCLick(ssf);
                    jh.hightligtandCLick(selectOption1);
                    //Thread.sleep(5000);
                    String option = ssf.getAttribute("value");
                    String ssf_Desc= "SSF_"+ssfCounter+"_DESC";
                    System.out.println(">>>>>>  = " + ssf_Desc);
                    System.out.println(">>>>>>  = "+ option);
                    //Thread.sleep(5000);
                    map.put(ssf_Desc,option);


                } catch (Exception f) {
                    System.out.println("SSF Field is Not Available");
                }
                ssfCounter++;
            }

            jh.hightligtandCLick(saveButtonXpath);
            Thread.sleep(10000);
        } catch (Exception e) {

        }


        for(Map.Entry<String, String> entry: map.entrySet()){
            String k = entry.getKey();
            System.out.println(">>>>>> Key "+ k);
            String v = entry.getValue();
            System.out.println("<<<<<<< Value "+v);
            TestBase.logExtentReport(">>>>>> Reply from UI >>>>> Key = "+ k + ">>>>>> Value = "+v);
        }

        return map;

    }

    public void clickAndSelect(WebElement ele1, WebElement ele2){

        jh.hightligtandCLick(ele1);
        jh.hightligtandCLick(ele2);

    }

    public void updateEstimation() throws InterruptedException {
        //First  we are saving the existing Value
        String hospitalAdmissionDateEstimated = dateOfDiagnosisEstimation.getAttribute("value");

        // If there is no value
        if (hospitalAdmissionDateEstimated.isEmpty()) {

            System.out.println("Step 12");
            jh.hightligtandCLick(dateOfDiagnosisEstimation);
            jh.hightligtandCLick(selectDayEstimatedAdmissionEstimated);

        } else if (hospitalAdmissionDateEstimated.equals("Day Estimated")) {

            System.out.println("Step 13");
            jh.hightligtandCLick(dateOfDiagnosisEstimation);
            jh.hightligtandCLick(selectDayAndMonthAdmissionEstimated);


        } else if (hospitalAdmissionDateEstimated.equals("Day and Month Estimated")) {

            System.out.println("Step 14");
            jh.hightligtandCLick(dateOfDiagnosisEstimation);
            jh.hightligtandCLick(selectDayEstimatedAdmissionEstimated);


        } else if (hospitalAdmissionDateEstimated.equals("Day, Month, Year Estimated")) {

            System.out.println("Step 15");
            jh.hightligtandCLick(dateOfDiagnosisEstimation);
            jh.hightligtandCLick(selectDayEstimatedAdmissionEstimated);


        }
    }

    public void deleteCancerEpisode(){
        jh.hightligtandCLick(deleteAndReset);
        jh.hightligtandCLick(deleteWindowDeleteButton);
    }

    public void onlyDeleteCuratorCancerEpisode(){
        jh.hightligtandCLick(onlyDelete);
        jh.hightligtandCLick(deleteWindowDeleteButton);
    }

    public void logOut(){

        jh.hightligtandCLick(LogOut);


    }


}
