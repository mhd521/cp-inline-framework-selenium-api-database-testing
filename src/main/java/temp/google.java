package temp;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class google {

    WaitHelper wh;
    JavaScriptHelper jh;

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchBox;

    public google(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("Cancer Section");
    }
    public void search(){
        searchBox.sendKeys("hi ggole");
    }
}
