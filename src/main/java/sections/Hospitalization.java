package sections;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.HospitalizationLocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.CommonMethods;
import utility.DateSpliters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Pooja Sah
 * Updated 04/14/2020
 */

@Log4j
public class Hospitalization extends HospitalizationLocators {

    WaitHelper wh;
    JavaScriptHelper jh;


    //Constructor
    public Hospitalization(WebDriver driver) {
        PageFactory.initElements(driver, this);
        System.out.println("We are at Hospitalization Constructor");
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("We are at Hospitalization section now!");
    }

//    public void hospitalizationSummaryClick() throws Exception {
//        System.out.println("I m inside Summary CLick");
//        Thread.sleep(5000);
//        jh.hightligtandCLick(nevBar);
//        Thread.sleep(5000);
//        jh.hightligtandCLick(hospitalizationSection);
//    }

    public void hideNavBar() throws Exception {
        jh.hightligtandCLick(referencePanelBar);
    }

    public void curationDirectivesToggleClick() {
        wh.WaitForElementVisibleWithPollingTime(CurationDirectivesToggle, 30, 5);
        jh.hightligtandCLick(CurationDirectivesToggle);

    }

    public boolean addHospitalizationFormClick() throws Exception {
        // hospitalizationSummeryTableClick();
        jh.hightligtandCLick(addHospitalization);
        Thread.sleep(5000);
        wh.waitForElement(curationDirectiveText, 5);
        return true;

    }

    public String directiveText() {
        String text;
        try {
            wh.WaitForElementVisibleWithPollingTime(DirectiveText, 30, 5);
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

    public void saveButtonClick() {
        System.out.println("Clicking Save Button!");
        wh.WaitForElementVisibleWithPollingTime(saveButtonXpath, 30, 5);
        //jh.hightligtandCLick(matActionRow);
        jh.hightligtandCLick(saveButtonXpath);
        TestBase.logExtentReport("Save Button Clicked!");

    }

    public boolean addHospitalizationFormWithSourceValues() {
        System.out.println("Adding Hospitalization form from source");
        wh.WaitForElementVisibleWithPollingTime(addHospitalizationSourceForm, 30, 5);
        jh.hightligtandCLick(addHospitalizationSourceForm);
        TestBase.logExtentReport("Added Hospitalization Form");
        return true;
    }

    public int countHospitalizationFormWithSourceValues(WebDriver driver) throws Exception {

        List<WebElement> elements = driver.findElements(By.xpath("//mat-icon[contains(text(),'Accept')]"));
        int count = elements.size();
        TestBase.logExtentReport("Counted Hospitalization Form with source values");
        return count;
    }

    public String
    collectDFCIMRN() {
        jh.hightligtandCLick(dfciMRN);
        String MRN = dfciMRN.getText();
        return MRN;
    }

    public String itemDirectiveButtonClick() {
        wh.WaitForElementVisibleWithPollingTime(itemDirective, 30, 5);
        jh.hightligtandCLick(itemDirective);
        wh.WaitForElementVisibleWithPollingTime(directiveTitle, 30, 5);
        jh.highLightElement(directiveTitle);
        String item;

        if (itemDirectiveText1.isDisplayed()) {
            jh.highLightElement(itemDirectiveText1);
            item = " Day Estimated ";

        } else {
            item = "Directive Not Loaded";
        }
        jh.highLightElement(itemContainer);
        itemContainer.sendKeys(Keys.ESCAPE);

        return item;


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

    public LinkedHashMap<String, String> dataEntry() throws Exception {
        jh.highLightElement(CurationDirectivesToggle);
        jh.hightligtandCLick(hospitalAdmissionDate);
        hospitalAdmissionDate.sendKeys("04-18-2014");
        jh.hightligtandCLick(hospitalAdmissionDateSource);
        selectDayEstimatedAdmissionEstimated.click();
        jh.highLightElement(dischargeDate);
        dischargeDate.sendKeys("09-15-2017");
        jh.hightligtandCLick(dischargeDateSource);
        selectDischargeDateSource.click();
        jh.hightligtandCLick(institution);
        selectInstitution.click();

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("ADMISSION_DT", "2014-04-18");
        map.put("AdmissionDateEstimation", "Day Estimated");
        map.put("DischargeDT", "2017-09-15");
        map.put("DischargeDateEstimation", "Day Estimated");
        map.put("Institution", "External Institution");

        return map;

    }

    public String lastUpdateDate() throws InterruptedException {

        wh.WaitForElementVisibleWithPollingTime(LastSaved, 30, 5);
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

    public int hospitalizationViewFormCount(WebDriver driver) throws Exception {
        hospitalizationSummeryTableClick();
        //  jh.highLightElement(hospitalizationSummaryTable);
        int total = 0;

        try {
            System.out.println("Step 1");
            jh.highLightElement(hospitalizationSummaryRow);
            List<WebElement> count = driver.findElements(By.xpath("//span[contains(text(),' View Form ')]"));

            total = count.size();
            System.out.println("VIEW FORM COUNT "+total);
            return total;
        } catch (Exception e) {
            System.out.println("Step 2");
            return total;
        }

    }

    public void deleteButtonClick() throws InterruptedException {
        jh.hightligtandCLick(deleteButton);
        wh.WaitForElementVisibleWithPollingTime(deletewindowbutton, 30, 5);
        jh.hightligtandCLick(deletewindowbutton);

    }

    public void newForm() throws Exception {
        hospitalizationSummeryTableClick();
        addHospitalizationFormClick();
        Thread.sleep(5000);

    }

    public String countSource(WebDriver driver) {
        hospitalizationSummeryTableClick();
        wh.WaitForElementVisibleWithPollingTime(hospitalizationSummaryTableHeadline, 30, 5);
        jh.highLightElement(hospitalizationSummaryTableHeadline);
        wh.WaitForElementVisibleWithPollingTime(hospitalizationSummaryTable, 30, 5);
        jh.highLightElement(hospitalizationSummaryTable);
        //List<WebElement> acceptButton = driver.findElements(By.xpath("//span[contains(text(),'Accept')]"));
        List<WebElement> electiveCount = driver.findElements(By.xpath("//mat-cell[contains(text(),'ELECTIVE')]"));
        List<WebElement> urgentCount = driver.findElements(By.xpath("//mat-cell[contains(text(),'URGENT')]"));
        List<WebElement> emmergency = driver.findElements(By.xpath("//mat-cell[contains(text(),'EMERGENCY')]"));

        //String getAdmissionType = admimissionType.getText();
        String status = null;
        int count = electiveCount.size();
        int uc = urgentCount.size();
        int em = emmergency.size();
        if (count == 0) {
            if (uc == 0) {
                if (em == 0) {
                    System.out.println("NO SOURCE DATA");
                    status = "NS";
                }

            } else {
                status = "SOURCE";
                System.out.println("SOURCE DATA FOUND");
            }
        } else {
            status = "SOURCE";
            System.out.println("SOURCE DATA FOUND");
        }
        return status;
    }

    public void hospitalizationSummeryTableClick() {
        System.out.println("Im clicking Hospitalization Summery Link");
        wh.WaitForElementVisibleWithPollingTime(referencePanelBar, 30, 5);
        jh.hightligtandCLick(referencePanelBar);
        wh.WaitForElementVisibleWithPollingTime(nevBar, 30, 5);
        jh.hightligtandCLick(nevBar);
        wh.WaitForElementVisibleWithPollingTime(hospitalizationSummeryLink, 30, 5);
        jh.hightligtandCLick(hospitalizationSummeryLink);
        wh.WaitForElementVisibleWithPollingTime(hospitalizationSection, 30, 5);
        jh.hightligtandCLick(hospitalizationSection);

    }


    public void findSourceReport(WebDriver driver) {

        hospitalizationSummeryTableClick();
        wh.WaitForElementVisibleWithPollingTime(hospitalizationSummaryTableHeadline, 30, 5);
        jh.highLightElement(hospitalizationSummaryTableHeadline);
        wh.WaitForElementVisibleWithPollingTime(hospitalizationSummaryTable, 30, 5);
        jh.highLightElement(hospitalizationSummaryTable);
        countSource(driver);

    }

    public void test(WebDriver driver) {
        List<WebElement> electiveCount = driver.findElements(By.xpath("//mat-cell[contains(text(),'ELECTIVE')]"));
        List<WebElement> urgentCount = driver.findElements(By.xpath("//mat-cell[contains(text(),'URGENT')]"));
        List<WebElement> emmergency = driver.findElements(By.xpath("//mat-cell[contains(text(),'EMERGENCY')]"));
        CommonMethods cm = new CommonMethods(driver);
        String regex = "ELECTIVE|URGENT|EMERGENCY";
        // cm.stringTest();
    }

    public String findingNewSourceForm(WebDriver driver, int totalRowCount) throws InterruptedException {

        System.out.println("Step 4");
        String reply = null;
        //Thread.sleep(5000);
        int row = 1;
        //Building Unique Path for Admission Type
        String firstPath = "//app-hospitalization-form[1]/div[1]/mat-expansion-panel[1]/div[1]/div[1]/app-hospitalization-table[1]/div[1]/mat-table[1]/mat-row[";
        String secondPath = "]/mat-cell[3]";

        //Building Unique Path for Accept/ViewForm From Source
        String startPath = "//app-hospitalization-form/div[1]/mat-expansion-panel/div/div/app-hospitalization-table/div/mat-table/mat-row[";
        String endPath = "]/mat-cell[7]//button[1]";

        for (int i = 1; i <= totalRowCount; i++) {
            System.out.println("Step 5");
            String completePath = firstPath + row + secondPath;
            //Thread.sleep(5000);

            //First Getting the Record from Admission Type, if its a source data, it should have ELECTIVE|URGENT|EMERGENCY"
            Thread.sleep(5000);
            String admissionType = driver.findElement(By.xpath(completePath)).getText();
            System.out.println(admissionType);

            //if admission type is empty it will skip the source data , it will got to the next report if available
            // Thread.sleep(5000);
            if (admissionType.isEmpty()) {
                System.out.println("Step 6");
                System.out.println("Its Not the Source Data , Skipping to the Next Report if any");
                TestBase.logExtentReport("Its Not the Source Data , Skipping to the Next Report if any! Row No " + row);

                reply = "NOT SUCCESS";
                // Thread.sleep(5000);

              // If admission type is not empty, it will check if VIEW PATIENT OR ACCEPT BUTTON IS AVAILABLE
            } else {

                // IT WILL CHECK WHAT IS THE VALUE OF ADMISSION TYPE
                CommonMethods cm = new CommonMethods(driver);
                System.out.println("Step 7");

                // Declearing on Regex to Match the Reply from Admission Type;
                String regex = "ELECTIVE|URGENT|EMERGENCY";
                boolean status = cm.stringTest(regex, admissionType);
                //Thread.sleep(5000);

                //If the reply have "ELECTIVE|URGENT|EMERGENCY" than we will check if the Accept Button is Enabled or Not.
                if (status) {
                    System.out.println("Step 8");

                    //We are creating the Path for Accept Button
                    String acceptButtonPath = startPath + row + endPath;

                    //Let's Make it as a WebElement -  Accept or View Form Button
                      WebElement accept_view_form_button = driver.findElement(By.xpath(acceptButtonPath));
                      Thread.sleep(5000);

                    try {

                        System.out.println("Step 9");

                        //Now Let's Click the Accept or View Form Button
                        jh.hightligtandCLick(accept_view_form_button);
                        Thread.sleep(5000);

                        //We are changing the focus to the form and activate it.
                        wh.WaitForElementVisibleWithPollingTime(curationDirectiveText, 30, 5);
                        System.out.println("Step 10");
                        jh.hightligtandCLick(curationDirectiveText);

                       // We want to change the Value of Admission Date Estimation
                            System.out.println("Step 11");
                            jh.highLightElement(hospitalAdmissionDateSource);
                            Thread.sleep(5000);

                            //First  we are saving the existing Value
                            String hospitalAdmissionDateEstimated = hospitalAdmissionDateSource.getAttribute("value");

                            // If there is no value
                            if (hospitalAdmissionDateEstimated.isEmpty()) {

                                System.out.println("Step 12");
                                jh.hightligtandCLick(hospitalAdmissionDateSource);
                                Thread.sleep(5000);
                                jh.hightligtandCLick(selectDayEstimatedAdmissionEstimated);
                                Thread.sleep(5000);

                            } else if (hospitalAdmissionDateEstimated.equals("Day Estimated")) {

                                System.out.println("Step 13");
                                jh.hightligtandCLick(hospitalAdmissionDateSource);
                                Thread.sleep(5000);
                                jh.hightligtandCLick(selectDayAndMonthAdmissionEstimated);
                                Thread.sleep(5000);

                            } else if (hospitalAdmissionDateEstimated.equals("Day and Month Estimated")) {

                                System.out.println("Step 14");
                                jh.hightligtandCLick(hospitalAdmissionDateSource);
                                Thread.sleep(5000);
                                jh.hightligtandCLick(selectDayEstimatedAdmissionEstimated);
                                Thread.sleep(5000);

                            } else if (hospitalAdmissionDateEstimated.equals("Day, Month, Year Estimated")) {

                                System.out.println("Step 15");
                                jh.hightligtandCLick(hospitalAdmissionDateSource);
                                Thread.sleep(5000);
                                jh.hightligtandCLick(selectDayEstimatedAdmissionEstimated);
                                Thread.sleep(5000);

                            }

                            System.out.println("Step 16");
                            jh.hightligtandCLick(saveButtonXpath);
                            Thread.sleep(5000);

                        // We need the reply to Success so that we can verify the data with database;
                        reply = "SUCCESS";
                        System.out.println("Step 17");
                        System.out.println("Button Clicked on Row No " + row);
                        TestBase.logExtentReport("Button Clicked on Row No " + row);
                        //We are breaking the loop since we got the Source File which never accepted
                        break;

                    } catch (Exception e) {

                        System.out.println("Step 18");
                        System.out.println("Step 10");
                        System.out.println("Accept Button is Not Enabled But This is from Source! Row No " + row);
                        TestBase.logExtentReport("Accept Button is Not Enabled But This is from Source! Row No " + row);
                        reply = "NOT SUCCESS";

                    }
                }

            }

            row++;
        }

        return reply;

    }

    public LinkedHashMap<String, String> collectInformationFromUI() {
        System.out.println(" ++++++++++++++ Checking Hospitalization Section UI Testing Results!");
        DateSpliters ds = new DateSpliters();
        //Creating a MAP to collect all the reply into one place.
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        wh.waitWith30SecondAndClickElement(referencePanelBar);
        wh.waitWith30SecondAndClickElement(CurationDirectivesToggle);

        wh.waitWith30SecondAndClickElement(hospitalAdmissionDate);
        String admissiondate = hospitalAdmissionDate.getAttribute("value");


        jh.highLightElement(hospitalAdmissionDateSource);
        String hospitalAdmissionDateEstimation = hospitalAdmissionDateSource.getAttribute("value");
        map.put("Estimation", hospitalAdmissionDateEstimation);

        wh.waitWith30SecondAndClickElement(dischargeDate);
        String dischargedate = dischargeDate.getAttribute("value");

        wh.WaitForElementVisibleWithPollingTime(institution, 30, 5);
        jh.highLightElement(institution);
        String institutions = institution.getAttribute("value");

        //Collecting and Saving Admission Date into Map
        if (admissiondate.isEmpty()) {
            map.put("AdmissionDate", "null");
        } else {
            String ad = ds.dateSpliter(admissiondate);
            //  System.out.println("++++++++++++>>>>>" +dofv);
            map.put("AdmissionDate", ad);
        }

        //Collecting and Saving Discharge Date into Map
        if (dischargedate.isEmpty()) {
            map.put("DischargeDate", "null");
        } else {
            String dd = ds.dateSpliter(dischargedate);
            //  System.out.println("++++++++++++>>>>>" +dofv);
            map.put("DischargeDate", dd);
        }

        //Collecting and Saving Institutions  into Map
        if (institutions.isEmpty()) {
            map.put("Institution", "null");
        } else {
            map.put("Institution", institutions);
        }

        return map;
    }


}
