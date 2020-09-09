package temp;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class clinical extends ClinicalLocator{


    WaitHelper wh;
    JavaScriptHelper jh;


    public clinical(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);

    }

    public void login(){
        user.sendKeys("mohamed");
        pass.sendKeys("MohammedPassW0Rd");
        jh.hightligtandCLick(submit);
    }
}
