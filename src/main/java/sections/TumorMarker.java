package sections;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.TumorMarkerLocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.DateSpliters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */


@Log4j
public class TumorMarker extends TumorMarkerLocators {

    WaitHelper wh;
    JavaScriptHelper jh;

   //Constructor
    public TumorMarker(WebDriver driver) {
        PageFactory.initElements(driver, this);
        System.out.println("We are at Tumor Marker Constructor");
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("We are at Tumor Marker section now!");
    }

    public void tumorMarkerSummeryClick() throws Exception{
        System.out.println("Im inside Summery CLick");
        wh.WaitForElementVisibleWithPollingTime(referencePanelBar,30,5);
        jh.hightligtandCLick(referencePanelBar);
       wh.WaitForElementVisibleWithPollingTime(nevBar,30,5);
        jh.hightligtandCLick(nevBar);
       wh.WaitForElementVisibleWithPollingTime(tumorMerkerSummerLink,30,5);
        jh.hightligtandCLick(tumorMerkerSummerLink);
    }

    public void curationDirectivesToggleClick() {
        wh.WaitForElementVisibleWithPollingTime(CurationDirectivesToggle,30,5);
        jh.hightligtandCLick(CurationDirectivesToggle);

    }

    public boolean addTumorMarkerFormClick() throws Exception {
        tumorMarkerSummeryClick();
        jh.highLightElement(tumorMarkerSummeryTableHeadline);
        jh.highLightElement(tumorMarkerSummeryTable);
        jh.hightligtandCLick(addTumorMarkerForm);
        wh.waitForElement(tumorMarkerTestResultBar,5);
        return true;

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

    public void saveButtonClick() {
        wh.WaitForElementVisibleWithPollingTime(saveButtonXpath,30,5);
        System.out.println("Clicking Save Button!");
        //jh.hightligtandCLick(matActionRow);
        jh.hightligtandCLick(saveButtonXpath);

    }

    public void addTumorMarkerForm() {
        System.out.println("Adding Tumor Marker");
        jh.hightligtandCLick(addTumorMarker);
        TestBase.logExtentReport("Added Tumor Marker Form");
    }

    public String collectDFCIMRN() {
        jh.hightligtandCLick(dfciMRN);
        String MRN = dfciMRN.getText();
        return MRN;
    }

    public String itemDirectiveButtonClick(){
        wh.WaitForElementVisibleWithPollingTime(itemDirective,30,5);
        jh.hightligtandCLick(itemDirective);
        wh.WaitForElementVisibleWithPollingTime(directiveTitle,30,5);
        jh.highLightElement(directiveTitle);
        String item;

        wh.WaitForElementVisibleWithPollingTime(itemDirective,30,5);
        if(itemDirectiveTextAFPl.isDisplayed()){
            wh.WaitForElementVisibleWithPollingTime(itemDirectiveTextAFPl,30,5);
            jh.highLightElement(itemDirectiveTextAFPl);
            item = "AFP";

        }else {
            item = "Directive Not Loaded";
        }
        wh.WaitForElementVisibleWithPollingTime(itemContainer,30,5);
        jh.highLightElement(itemContainer);
        itemContainer.sendKeys(Keys.ESCAPE);

        return item;



    }

    public int countOfHistoryIcons(WebDriver driver) throws Exception {

        CurationDirectivesToggle.click();
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.xpath("//mat-icon[contains(text(),'history')]"));
        int count = elements.size();
        System.out.println("Total No of curation history " + count);
        return count;

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

    public LinkedHashMap<String,String> dataEntry() throws Exception{
        wh.WaitForElementVisibleWithPollingTime(CurationDirectivesToggle,30,5);
        jh.highLightElement(CurationDirectivesToggle);
        wh.WaitForElementVisibleWithPollingTime(testName,30,5);
        jh.hightligtandCLick(testName);
        wh.WaitForElementVisibleWithPollingTime(selectTestName,30,5);
        selectTestName.click();
        wh.WaitForElementVisibleWithPollingTime(labInstitution,30,5);
        jh.hightligtandCLick(labInstitution);
        selectLabInstitution.click();
        wh.WaitForElementVisibleWithPollingTime(specimenCollectionDate,30,5);
        jh.highLightElement(specimenCollectionDate);
        specimenCollectionDate.sendKeys("09-15-2017");
        wh.WaitForElementVisibleWithPollingTime(specimenCollectionDateSource,30,5);
        jh.hightligtandCLick(specimenCollectionDateSource);
        selectCollectionDateSource.click();
        jh.hightligtandCLick(numericResult);
        numericResult.sendKeys("3.5");
        //Thread.sleep(5000);
        jh.hightligtandCLick(resultUnit);
        selectResultUnit.click();
        jh.hightligtandCLick(textResult);
        textResult.sendKeys("Tested");
        //Thread.sleep(5000);
        jh.hightligtandCLick(normalRange);
        //normalRange.sendKeys("0.1");
        //Thread.sleep(5000);
        jh.hightligtandCLick(resultOutOfRange);
        selectResultOutOfRange.click();

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("TestName","CA 125");
        map.put("Institution","External Institution");
        map.put("SpecimenDT", "2017-09-15");
        map.put("Estimation","Day Estimated");
        map.put("ResultNumber", "3.5");
        map.put("ResultUnit", "U/L");
        map.put("ResultText", "Tested");
        map.put("Range", "High");

        return map;

    }

    public String lastUpdateDate() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(LastSaved,30,5);
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

    public int tumorMarkerTableRowCount(WebDriver driver){
        int total = 0;

        try{
            wh.WaitForElementVisibleWithPollingTime(tumorMarkerNotesTab,30,5);
            jh.hightligtandCLick(tumorMarkerNotesTab);
            wh.WaitForElementVisibleWithPollingTime(tumorNotesTableRows,30,5);

            jh.hightligtandCLick(tumorNotesTableRows);
            List<WebElement> count = driver.findElements(By.xpath("/html/body/app-root/app-app-container/div/app-curation-container/div/mat-sidenav-container/mat-sidenav/div/app-notes/div[2]/app-tumor-marker-table/div/mat-table/mat-row"));
            total = count.size();
            return total;
        }catch (Exception e){
            return total;
        }

    }

    public void deleteButtonClick() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(deleteButton,30,5);
        jh.hightligtandCLick(deleteButton);
        wh.WaitForElementVisibleWithPollingTime(deletewindowbutton,30,5);
        jh.hightligtandCLick(deletewindowbutton);

    }

    public int tumorMarkerSummeryTableRowCount(WebDriver driver) throws Exception{
        //jh.hightligtandCLick(referencePanelBar);
        tumorMarkerSummeryClick();
        jh.highLightElement(tumorMarkerSummeryTableHeadline);
        jh.highLightElement(tumorMarkerSummeryTable);
        // jh.hightligtandCLick(addTumorMarkerForm);
        Thread.sleep(1000);

        int total = 0;

        try{
            jh.highLightElement(tumorMarkerSummeryRow);
            List<WebElement> count = driver.findElements(By.cssSelector("#view-path-form-button"));
            total = count.size();
            return total;
        }catch (Exception e){
            return total;
        }


    }

    public void viewFormOrNewForm() throws Exception {
        wh.WaitForElementVisibleWithPollingTime(tumorMarkerSummeryTableHeadline,30,5);
        jh.highLightElement(tumorMarkerSummeryTableHeadline);
        wh.WaitForElementVisibleWithPollingTime(tumorMarkerSummeryTable,30,5);
        jh.highLightElement(tumorMarkerSummeryTable);

        if(firstViewForm.isEnabled()){
            wh.WaitForElementVisibleWithPollingTime(firstViewForm,30,5);
            jh.hightligtandCLick(firstViewForm);

        }else {
            addTumorMarkerFormClick();
        }

    }

}
