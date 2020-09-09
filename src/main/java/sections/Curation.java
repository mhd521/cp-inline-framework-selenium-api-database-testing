package sections;


import base.TestBase;
import helper.JavaScriptHelper;
import helper.LoggerHelper;
import helper.WaitHelper;
import locators.CurationLocators;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ObjectReader;

import java.util.concurrent.TimeUnit;

/**
 * @author Shariar Ahmed
 * Updated 04/02/2019
 */

@Log4j
public class Curation extends CurationLocators {


    private WebDriver driver;


    //private final Logger log = LoggerHelper.getLogger(Curation.class);
    CuratorDashBoard wf;
    WaitHelper wh;
    JavaScriptHelper jsh;



    // Constructor for this Page ///
    public Curation(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(60, TimeUnit.SECONDS);
        TestBase.logExtentReport("Curator Page Object Created");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //new TestBase().getNavigationScreen(driver);
        //wh.waitForElement(PatientName, ObjectReader.reader.getExplicitWait());
        jsh = new JavaScriptHelper(driver);
        wf = new CuratorDashBoard(driver);


    }

    //Patient MRN Label Display//
    public String patientMRNLabelDisplay() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(patientMRNLabel,30,5);
        jsh.highLightElement(patientMRNLabel);
        Thread.sleep(10000);
        String dfciMrn = patientMRNLabel.getText();
        TestBase.logExtentReport("Patient MRN Label Displayed");
        //  log.info("Step 13");
        return dfciMrn;
    }


    public void flagPatient(String message) throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(dateandtime,30,5);
        jsh.highLightElement(dateandtime);
        String curationtime = dateandtime.getText();
      // wh.WaitForElementVisibleWithPollingTime(flagButton,30,5);
      //  jsh.hightligtandCLick(flagButton);
        wh.waitWith30SecondAndClickElement(flagButton);
        String username = ObjectReader.reader.getUserName();
        //flagButton.click();
        wh.WaitForElementVisibleWithPollingTime(flagCommentsArea,30,5);
        jsh.hightligtandCLick(flagCommentsArea);
        flagCommentsArea.sendKeys(message + curationtime + " by " + username);
        flagingPatient.click();
        log.info("<<<<<<<<<<<<<<< Testing done by Automation Script >>>>>>>>>>>>>>>>>>>>>>>>>>");

        //  wf.logOut.click();
    }

    public boolean flagButton() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(flagButton,30,5);
        jsh.highLightElement(flagButton);
        if (flagButton.isEnabled()) {
            System.out.println("We Are Inside Flag Button");
            wh.WaitForElementVisibleWithPollingTime(patientMRNLabel,30,5);
            Thread.sleep(5000);
            String MRN = patientMRNLabel.getText();
            System.out.println(MRN);
            flagPatient("Flag Button Testing");
            return true;
        } else {
            System.out.println("Flag Button Not Available");
            return false;
        }


    }

    //Return to Main Menu //
    public void returnToMainMenuBtnClick() {
        wh.WaitForElementVisibleWithPollingTime(returnToMainMenuBtn,30,5);
        jsh.hightligtandCLick(returnToMainMenuBtn);
        System.out.println("Return To Main Menu Clicked!");

    }


    public void completCurationClick() throws Exception {
        wh.WaitForElementVisibleWithPollingTime(completeCurationButton,30,5);
        jsh.hightligtandCLick(completeCurationButton);
        wh.WaitForElementVisibleWithPollingTime(qaAcceptBtn,30,5);
        wh.waitWith30SecondAndClickElement(qaAcceptBtn);
    }

    public boolean flagButtonAvailability() throws Exception {

        try{
            wh.WaitForElementVisibleWithPollingTime(flagButton,30,5);
            jsh.hightligtandCLick(flagButton);
            System.out.println("Flag Button Clicked!");
            return true;

        }catch (Exception e){
            System.out.println("Cannot Find Flag Button!");
            return false;
        }


    }

    public void LogOut(){
        jsh.hightligtandCLick(LogOut);
    }




}
