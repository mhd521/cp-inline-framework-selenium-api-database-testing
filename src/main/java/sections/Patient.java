package sections;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.PatientLocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DateSpliters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */

@Log4j
public class Patient  extends PatientLocators {
    WaitHelper wh;
    JavaScriptHelper jh;
    WebDriverWait wait;








    //Constructor
    public Patient(WebDriver driver) {
        PageFactory.initElements(driver, this);
        System.out.println("We are ate Patient Section Constructor");
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("We are at patient section now!");
    }

    public void curationDirectivesToggleClick() throws InterruptedException {
        TestBase.logExtentReport("Trying to Toggle the Curation Button..");
        wh.WaitForElementVisibleWithPollingTime(CurationDirectivesToggle,30,5);
        jh.hightligtandCLick(CurationDirectivesToggle);

    }

    public String directiveText() {
        String text;
        try {
            wh.WaitForElementVisibleWithPollingTime(DirectiveText,30,5);
            jh.hightligtandCLick(DirectiveText);
            Thread.sleep(5000);
            text = DirectiveText.getText();
            System.out.println("Directive Displaying");
            return text;
        } catch (Exception e) {

            System.out.println("Text is Empty");
            text = "Empty Text";
            return text;
        }
    }

    public String dateOfFirstVisitToDFCIGetDate() {
        String date;

        try {
            jh.hightligtandCLick(dateofFirstVisitToDFCIcss);
            date = dateofFirstVisitToDFCIcss.getAttribute("value");
            System.out.println(date);
            return date;
        } catch (Exception e) {
            System.out.println("Date is Empty");
            System.out.println("Inserting New Dates");
            dateofFirstVisitToDFCIcss.sendKeys("01-01-2000");
            date = "01-01-2000";
            return date;
        }
    }

    public void saveButtonClick() throws InterruptedException {
        System.out.println("Step 0");
        TestBase.logExtentReport("Trying to Save the Form...");
        System.out.println("Trying to Save the Form...");

        if (saveButtonXpath.isEnabled()) {
            System.out.println("Step 1");
            System.out.println("Clicking Save Button!");
            //jh.scrollIntoViewAndClick(matActionRow);
            //jh.hightligtandCLick(matActionRow);
            jh.hightligtandCLick(saveButtonXpath);
            TestBase.logExtentReport("Clicking Save Button");

        } else {
            System.out.println("Step 2");
            TestBase.logExtentReport("Save Button Not Enabled, Going to Next Step...");
            System.out.println("Save Button is Not Enabled");
        }


    }

    public String collectDFCIMRN() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(dfciMRN,30,5);
        jh.hightligtandCLick(dfciMRN);
        Thread.sleep(5000);
        String MRN = dfciMRN.getText();

        return MRN;
    }

    public LinkedHashMap<String, String> collectPatientInformation(String patientDeceased) throws Exception {

        System.out.println(" ++++++++++++++ Checking Patient Section UI Testing Results!");

        //Creating a MAP to collect all the reply into one place.
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        //Clicking the reference bar to make the extra space
        wh.waitWith30SecondAndClickElement(referencePanelBar);
        //referencePanelBar.click();

        //Collecting MRN
        Thread.sleep(5000);
        String MRN = collectDFCIMRN();
        System.out.println(MRN);

             // All Date to be Reformatted becuase date sequeence is different.
        DateSpliters ds = new DateSpliters();

        //Changing Focus
        wh.waitWith30SecondAndClickElement(CurationDirectivesToggle);

        //Getting Date of First Visit
        wh.waitWith30SecondAndClickElement(dateofFirstVisitToDFCIcss);
       // jh.hightligtandCLick(dateofFirstVisitToDFCIcss);
        String dfv = dateofFirstVisitToDFCIcss.getAttribute("value");

        if (dfv.isEmpty()) {
            // System.out.println("+++++++++>>>> NULL");
            map.put("DFCIFirstVisitDt", "null");
        } else {
            String dofv = ds.dateSpliter(dfv);
            //  System.out.println("++++++++++++>>>>>" +dofv);
            map.put("DFCIFirstVisitDt", dofv);
        }


        //String dfvT = dfvF + " 01:00:00.0";
        wh.WaitForElementVisibleWithPollingTime(vitalStatus,30,5);
        //Getting the Vital Status
        jh.hightligtandCLick(vitalStatus);
        String vs = vitalStatus.getAttribute("value");

        if (vs.isEmpty()) {
            //  System.out.println("+++++++++ VS NULL");
            map.put("IsPatientDecease", "null");
        } else {
            //  System.out.println("+++++++++++++ "+vs);
            map.put("IsPatientDecease", vs);
        }



        //Getting Date of Last Visit
        wh.WaitForElementVisibleWithPollingTime(dateOfLastDfciContact,30,5);
        jh.hightligtandCLick(dateOfLastDfciContact);
        String doldc = dateOfLastDfciContact.getAttribute("value");

        if (doldc.isEmpty()) {
            // System.out.println("+++++++++++++ empty value" );
            // System.out.println("++++++++++++++++++ >> DOLV NULL");
            map.put("LastContactDt", "null");
        } else {
            String ldcd = ds.dateSpliter(doldc);
            //System.out.println("++++++++++++++++ >> DOLV "+ ldcd);
            map.put("LastContactDt", ldcd);
        }


        //String dolcdT = doldF + " 01:00:00.0";
        Thread.sleep(5000);

        if (patientDeceased.equals("Yes")) {
             System.out.println("+++++++++++++++++ Vital Status is YES");

             String dods = dateOfDeathSource.getAttribute("value");
             if(dods.isEmpty()){
                 map.put("DateOfDeathSource",null);
             }else {
                 map.put("DateOfDeathSource",dods);
             }

            wh.WaitForElementVisibleWithPollingTime(dateofDeath,30,5);
            jh.hightligtandCLick(dateofDeath);
            String dod = dateofDeath.getAttribute("value");
            Thread.sleep(5000);
            if (dod.isEmpty()) {
                map.put("DateOfDeath", "null");
            } else {
                String dodF = ds.dateSpliter(dod);
                map.put("DateOfDeath", dodF);
            }

            //String dodT= dodF + " 01:00:00.0";
            Thread.sleep(2000);


        } else {
            System.out.println("+++++++++++++++++ Vital Status is NO");
        }


        return map;

    }

    public int countOfHistoryIcons(WebDriver driver) throws Exception {
        wh.WaitForElementVisibleWithPollingTime(CurationDirectivesToggle,30,5);
        CurationDirectivesToggle.click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.xpath("//mat-icon[contains(text(),'history')]"));
        int count = elements.size();
        System.out.println("Total No of curation history " + count);
        return count;

    }

    public String patientDeceasedReply(WebDriver driver) throws Exception {
        String reply;
        Thread.sleep(5000);
        jh.hightligtandCLick(CurationDirectivesToggle);
        //CurationDirectivesToggle.click();

        List<WebElement> elements = driver.findElements(By.xpath("//mat-icon[contains(text(),'history')]"));
        int count = elements.size();
        System.out.println("Total No of curation history " + count);
        if (count == 8) {

            reply = "Yes";

        } else {
            reply = "No";
        }
        return reply;

    }

    public String replaceNull(String Value) {
        String reply = null;

        if (Value == null) {
            reply = "null";
        } else {
            DateSpliters ds = new DateSpliters();
            reply = ds.dateSpliter(Value);

        }

        return reply;
    }

    public String[] dataof682785() {
        System.out.println("Im Here");

        jh.hightligtandCLick(dateOfDeathSource);
        String dodsource = dateOfDeathSource.getAttribute("value");
        jh.hightligtandCLick(dateofDeath);
        String dod = dateofDeath.getAttribute("value");
        jh.hightligtandCLick(estimationDateofDeath);
        String estiamtiondod = estimationDateofDeath.getAttribute("value");

        String[] arr = new String[3];
        arr[0] = dodsource;
        arr[1] = dod;
        arr[2] = estiamtiondod;
        return arr;


    }

    public String patientVitalStatusChange() throws InterruptedException {
        System.out.println("Step 4");
        String currentStatus = null;
        wh.WaitForElementVisibleWithPollingTime(CurationDirectivesToggle,30,5);

        jh.hightligtandCLick(CurationDirectivesToggle);

        wh.WaitForElementVisibleWithPollingTime(vitalStatus,30,5);
        jh.highLightElement(vitalStatus);
        String vs = vitalStatus.getAttribute("value");
        if (vs.equals("Yes")) {
            System.out.println("Step 5");
            //  System.out.println("+++++++++ VS NULL");

            jh.hightligtandCLick(vitalStatus);
            jh.hightligtandCLick(SelectNo);
            Thread.sleep(5000);
            currentStatus = "No";
        } else {
            System.out.println("Step 6");
            //  System.out.println("+++++++++++++ "+vs);

            jh.hightligtandCLick(vitalStatus);
            jh.hightligtandCLick(SelectYes);
            Thread.sleep(5000);
            currentStatus = "Yes";

        }
        System.out.println("Step 7");
        return currentStatus;

    }

    public void dataEntryfor682785() throws Exception {
        jh.highLightElement(CurationDirectivesToggle);
        jh.hightligtandCLick(dateofDeath);
        dateofDeath.sendKeys("09-15-2017");
        jh.hightligtandCLick(estimationDateofDeath);
        DayEstimated.click();
        jh.hightligtandCLick(dateOfDeathSource);
        selectNDI.click();
    }

    public void dayEstimationSelect() {
        jh.hightligtandCLick(estimationDateofDeath);
        DayEstimated.click();

    }

    public String lastUpdateDate() throws InterruptedException {
        jh.hightligtandCLick(LastSaved);
        Thread.sleep(5000);
        String ls = LastSaved.getText();
        Thread.sleep(5000);
        String[] result;
        result = ls.split(" ");
        String text1 = result[0];
        String text2 = result[1];
        String date = result[2];
        String time = result[3];
        String ampm = result[4];

        return date;
    }

    public void referenceBarClick() {
        wh.WaitForElementVisibleWithPollingTime(referencePanelBar,30,5);
        jh.hightligtandCLick(referencePanelBar);
    }

    public String patientDeceasedInformationCollect() throws InterruptedException {
        TestBase.logExtentReport("Trying to collect patient Vital Status.....");
        wh.WaitForElementVisibleWithPollingTime(vitalStatus,30,5);
        jh.highLightElement(vitalStatus);
        String reply = vitalStatus.getAttribute("value");
        if(reply.isEmpty()){
            TestBase.logExtentReport("NO VITAL STATUS DEFINED FROM SOURCE!");
            jh.hightligtandCLick(vitalStatus);
            jh.hightligtandCLick(SelectNo);
            saveButtonClick();
            Thread.sleep(5000);
            reply = vitalStatus.getAttribute("value");
        }
        return reply;

    }

    public LinkedHashMap<String, String> dataEntry() throws InterruptedException {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        jh.highlightClickSelect(dateOfDeathSource,selectNDI);
        Thread.sleep(5000);
        jh.hightligtCLickFillData(dateofDeath, "01-01-2020");
        Thread.sleep(5000);
        jh.highlightClickSelect(estimationDateofDeath,DayEstimated);
        Thread.sleep(5000);

        map.put("DateOfDeathSource", "NDI");
        map.put("DateOfDeath", "01-01-2020");
        map.put("EstimationDateOfDeath", "Day Estimated");


        return map;

    }

    public void insertOriginalData(LinkedHashMap<String, String> map) throws InterruptedException {
        System.out.println("Step 18");
        System.out.println("Inserting Original Data!!");
        for (String key : map.values()) {
            System.out.println("Step 19");
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Source Data from UI " + key);
        }

        for(Map.Entry<String, String> entry: map.entrySet()){
            System.out.println("Step 20");
            String k = entry.getKey();
            String v = entry.getValue();

            if(k.equals("DateOfDeath")){
                System.out.println("Step 21");
                System.out.println(entry.getValue());
                jh.hightligtCLickFillData(dateofDeath,entry.getValue());

            }
            System.out.println("Step 22");
        }
        System.out.println("Step 23");
        wh.WaitForElementVisibleWithPollingTime(dateOfDeathSource,30,5);
        jh.highlightClickSelect(dateOfDeathSource,selectNDI);
        wh.WaitForElementVisibleWithPollingTime(estimationDateofDeath,30,5);
        jh.highlightClickSelect(estimationDateofDeath, DayEstimated);
    }

    public void selectYes(){
        jh.hightligtandCLick(vitalStatus);
        jh.hightligtandCLick(SelectYes);

    }


}
