package utility;

import base.TestBase;
import database.DatabaseTesting;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */



@Log4j
public class CommonMethods {

    WebDriver driver;
    JavaScriptHelper jh;
    WebDriverWait wait;
    WaitHelper wh;

    @FindBy(xpath = "//span[contains(text(),'Save Section')]")
    WebElement saveButtonXpath;

    @FindBy(className = "patient-mrn-label")
    WebElement dfciMRNLabel;

    @FindBy(xpath = "//span[@class='patient-mrn-info']")
    WebElement dfciMRN;

    public CommonMethods(WebDriver driver){
        PageFactory.initElements(driver, this);
        System.out.println("We are ate Patient Section Constructor");
        wait = new WebDriverWait(driver,30);
        wh = new WaitHelper(driver);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("We are at patient section now!");
    }

    public void saveButtonClick() {

        System.out.println("Clicking Save Button!");
        //jh.scrollIntoViewAndClick(matActionRow);
        //jh.hightligtandCLick(matActionRow);
        jh.hightligtandCLick(saveButtonXpath);

    }

    public String collectPID() throws InterruptedException {
        wh.WaitForElementVisibleWithPollingTime(dfciMRNLabel,30,5);
        jh.hightligtandCLick(dfciMRNLabel);
        jh.hightligtandCLick(dfciMRN);
        Thread.sleep(5000);
        String MRN = dfciMRN.getText();
        Thread.sleep(5000);
        System.out.println("DFCI MRN>>>>>>>>>>>>> "+MRN);
        DatabaseTesting dt = new DatabaseTesting();
        String PID = dt.patientIDFind(MRN);
        System.out.println(">>>>>>>>>>>>>" + PID);
        return PID;
    }

    public boolean stringTest(String regex, String Match){

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
       // String name = "EMMERGENCY";
        Matcher matcher = pattern.matcher(Match);
        boolean result = matcher.find();
        System.out.println(result);
        return result;
    }


    public String replyTest(String regex, String Match){

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        // String name = "EMMERGENCY";
        Matcher matcher = pattern.matcher(Match);
        String result = matcher.toString();
        System.out.println(result);
        return result;
    }





}
