package QAMode;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.QALocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import sections.LoginPage;
import utility.ObjectReader;

@Log4j
public class QALogin extends QALocators {


    private WebDriver driver;
    WaitHelper wh;
    JavaScriptHelper jh;

    public QALogin(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        jh = new JavaScriptHelper(driver);
        // wh.waitForElement(signin, ObjectReader.reader.getExplicitWait());
        TestBase.logExtentReport("Login Page Object Created");
        // driver.manage().window().maximize();
    }


    public boolean clickQAMode() throws InterruptedException {
        boolean reply = true;
        String username = ObjectReader.reader.getQAUserName();
        if(username.equals("sq637")) {
            jh.hightligtandCLick(selectQAMode);
            Thread.sleep(5000);
            wh.WaitForElementVisibleWithPollingTime(qaInProgressText, 30, 5);
            reply = qaInProgressText.isDisplayed();
            return reply;
        }else {
            wh.WaitForElementVisibleWithPollingTime(qaInProgressText, 30, 5);
            reply = qaInProgressText.isDisplayed();
            return reply;
        }

    }







}
