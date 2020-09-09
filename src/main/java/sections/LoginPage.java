package sections;
import helper.JavaScriptHelper;
import locators.LoginPageLocators;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;
import helper.WaitHelper;
import org.testng.Assert;
import utility.ObjectReader;

import java.util.concurrent.TimeUnit;

/**
 * @author Shariar Ahmed
 */

@Log4j
public class LoginPage extends LoginPageLocators {

    private WebDriver driver;

    //private final Logger log = LoggerHelper.getLogger(LoginPage.class);

    WaitHelper wh;
    JavaScriptHelper jh;




    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        // wh.waitForElement(signin, ObjectReader.reader.getExplicitWait());
        TestBase.logExtentReport("Login Page Object Created");
       // driver.manage().window().maximize();

    }

    //---- This Will Log In To CP ---//

    public boolean loginApp() {

        try {

            wh.WaitForElementVisibleWithPollingTime(submit,30,5);

                username.sendKeys(ObjectReader.reader.getUserName());
                password.sendKeys(ObjectReader.reader.getPassword());

                  login();

                return true;

        } catch (Exception e) {
            log.warn(e);
            return false;
        }

    }

    public boolean qaModeLogin(){
        try {

            wh.WaitForElementVisibleWithPollingTime(submit,30,5);

            username.sendKeys(ObjectReader.reader.getQAUserName());
            password.sendKeys(ObjectReader.reader.getQAPassword());

            login();

            return true;

        } catch (Exception e) {
            log.warn(e);
            return false;
        }
    }

    public void login() throws InterruptedException {
        log.info("Clicking Submit");
        jh.highLightElement(submit);
        submit.click();
        Thread.sleep(5000);
        //defaultProject.click();

        Projects pj = new Projects(driver);
        wh.WaitForElementVisibleWithPollingTime(pj.projectPageDisplayMessage, 30, 5);
        if (pj.projectPageDisplayMessage.isDisplayed()) {
            Thread.sleep(5000);
            //Verifying Login is Successfuly with collecting a project text
            String Success = pj.projectPageDisplayMessage.getText();
            log.info("Login to Application Successful >> " + Success);
            log.info("Login Successfull");
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<< OCKTA Login Successful in Curation Platform >>>>>>>>>>>>>>>>>>>>>>>>>>");


        } else {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<< OCTA Login FAILED in Curation Platform >>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.warn("OCTA Login Failed");
            //logExtentReport("Login Failed");
            Assert.fail("OCTA Login Failed");
        }

        String version = cpVersion.getText();
        TestBase.logExtentReport("Test On CP Version " + version);
    }



}
