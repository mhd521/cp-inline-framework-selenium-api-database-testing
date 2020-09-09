package temp;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ObjectReader;

@Log4j
public class LoginPage  {

    WaitHelper wh;
    JavaScriptHelper jh;


    @FindBy(xpath = "//input[@id='mat-input-0']")
    public WebElement user;
    @FindBy(xpath = "//input[@id='mat-input-1']")
    public WebElement pass;
    @FindBy(xpath = "//button[@class='mat-button']")
    public WebElement submit;



    public LoginPage(WebDriver driver){

     PageFactory.initElements(driver,this);
       // wh = new WaitHelper(driver);
       // wh.pageLoadTime(30, TimeUnit.SECONDS,driver);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("Cancer Section");
    }
    public void login(WebDriver driver){
       // logger.info("logging in with valid user credential.");
        driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys("mohamed");
        driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("MohammedPassW0Rd");
        jh.hightligtandCLick(submit);
       // return new HomePage();
    }
}
