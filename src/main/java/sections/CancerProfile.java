package sections;

import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import locators.CancerLocators;
import locators.CancerProfileLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CancerProfile extends CancerProfileLocators {



    WaitHelper wh;
    JavaScriptHelper jh;
    WebDriver driver;


    public CancerProfile(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("Cancer Section");
    }

    public void CancerProfilingSummeryTable() {
        System.out.println("Im clicking Hospitalization Summery Link");
        wh.WaitForElementVisibleWithPollingTime(referencePanelBar, 30, 5);
        jh.hightligtandCLick(referencePanelBar);
        wh.WaitForElementVisibleWithPollingTime(nevBar, 30, 5);
        jh.hightligtandCLick(nevBar);
        wh.WaitForElementVisibleWithPollingTime(cancerProfilingSummeryLink, 30, 5);
        jh.hightligtandCLick(cancerProfilingSummeryLink);

    }


    public int cancerProfilingSummeryTableRowCount(WebDriver driver) {
        int rows = 0;
        try {

            System.out.println("Step 1");
            jh.highLightElement(cancerTebleRowselection);
            List<WebElement> count = driver.findElements(By.xpath(cancerTableRow));
            rows = count.size();
            return rows;

        } catch (Exception e) {
            System.out.println("Step 2");
            return rows;
        }

    }

    public void cancerProfileAccept(WebDriver driver) throws InterruptedException {

        String startPath = "//mat-table[@class='summary-table mat-table']//mat-row[";
        String endPath = "]//mat-cell[8]//button[1]";
        int row = 1;
        String acceptButtonPath = startPath+row+endPath;
        WebElement accept_view_form_button = driver.findElement(By.xpath(acceptButtonPath));
        jh.hightligtandCLick(accept_view_form_button);
        Thread.sleep(5000);


    }

    public String cancerSelection(String cancerType){
        //span[contains(text(),'00 Lung Cancer NSCLC')]
       // String cancerType = "Lung Cancer NSCLC";
        String firstPath = "//span[contains(text(),'";
        String secondPath ="')]";
        String fullpath = firstPath+cancerType+secondPath;
        System.out.println(">>>>>>> Full Path "+fullpath);
        return fullpath;
    }

    public void selectCancerType(WebDriver driver, String cancerType) throws Exception {
        jh.hightligtandCLick(reportDt);
        WebElement ele = driver.findElement(By.xpath(cancerType));
        clickCancerAssiciated();
        jh.hightligtandCLick(ele);
        saveForm();
    }

    public void saveForm() throws InterruptedException {
        Thread.sleep(10000);
        jh.hightligtandCLick(saveButtonXpath);
        Thread.sleep(10000);
    }

    public void clickCancerAssiciated() throws InterruptedException {
        jh.hightligtandCLick(CancersAssociated);


    }

}
