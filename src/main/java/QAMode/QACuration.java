package QAMode;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.QALocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.DateFunctions;
import utility.ObjectReader;

@Log4j
public class QACuration extends QALocators {

    private WebDriver driver;
    WaitHelper wh;
    JavaScriptHelper jh;

    public QACuration(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        jh = new JavaScriptHelper(driver);
        // wh.waitForElement(signin, ObjectReader.reader.getExplicitWait());
        TestBase.logExtentReport("We are at QA Curation Page");
        // driver.manage().window().maximize();
    }


    public String MRNInfo() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(MRN,30,5);
        jh.hightligtandCLick(MRN);
        Thread.sleep(5000);
        String DfciMrn= MRN.getText();
        return DfciMrn;

    }

    public void correctionNeededButton(String message) throws InterruptedException {
        DateFunctions df = new DateFunctions();
        String dateandtime = df.currentDateWithTimeStamp();
        String username = ObjectReader.reader.getUserName();
        jh.hightligtandCLick(correctionNeeded);
        correctionNeededTextArea.sendKeys(message + dateandtime + " by " + username);
        jh.hightligtandCLick(flagPatient);
        Thread.sleep(5000);
        QADashboard qad = new QADashboard(driver);
        qad.qaInProgressText();
        log.info("<<<<<<<<<<<<<<< Testing done by Automation Script >>>>>>>>>>>>>>>>>>>>>>>>>>");

    }


    public void flagPatient(String message) throws InterruptedException {
        DateFunctions df = new DateFunctions();
        String dateandtime = df.currentDateWithTimeStamp();
        jh.hightligtandCLick(flagButton);
        String username = ObjectReader.reader.getUserName();
        wh.WaitForElementVisibleWithPollingTime(correctionNeededTextArea,30,5);
        jh.hightligtandCLick(correctionNeededTextArea);
        correctionNeededTextArea.sendKeys(message + dateandtime + " by " + username);
        jh.hightligtandCLick(flagPatient);
        Thread.sleep(5000);
        QADashboard qad = new QADashboard(driver);
        qad.qaInProgressText();
        log.info("<<<<<<<<<<<<<<< Testing done by Automation Script >>>>>>>>>>>>>>>>>>>>>>>>>>");

        //  wf.logOut.click();
    }





    public void flag_correction_needed() throws InterruptedException {

        try{

            flagPatient("AUTOMATION TESTING DONE AT  ");

        }catch (Exception e){

            correctionNeededButton("Automation Testing Done at  ");

        }

    }

    public String acceptButtonClick(){
        try{
            jh.hightligtandCLick(acceptButton);
            wh.WaitForElementVisibleWithPollingTime(qaInProgressText,30,5);
            String qaInP = qaInProgressText.getText();
            System.out.println(">>>>>>>>>>>"+ qaInP);
            return  qaInP;
        }catch (Exception e){
            System.out.println(e);
            String accepButton = "Not Accepted the Episode";
            return accepButton;
        }
    }

}

