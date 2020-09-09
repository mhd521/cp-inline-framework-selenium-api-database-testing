package sections;

import base.TestBase;
import helper.LoggerHelper;
import helper.WaitHelper;
import locators.QueryPageLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class QueryPage extends QueryPageLocators {
    WebDriver driver;
    WaitHelper wh;
    Logger logger = LoggerHelper.getLogger(QueryPage.class);


    public QueryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(20, TimeUnit.SECONDS);
        TestBase.logExtentReport("Query page object is created");
    }

    public boolean verifyLogin() {
        logger.info("verifying Solr logo is displayed or not.");
        if (SolrLogo.isDisplayed()) {
            logger.info("Solr logo is displayed, login success.");
            return true;
        } else {
            logger.info("Solr logo is not displayed.");
            return false;
        }
    }

    public int GetNumFound_DefaultQuery() throws InterruptedException {
        wh.wait(2000);
        logger.info("searching for all results from Epic_Notes_Add and getting numFound");
        TestBase.logExtentReport("searching for all results from Epic_Notes_Add and getting numFound");
        executeQueryButton.click();
        wh.waitForElement(NumOfResults, 10);
        int numFound = Integer.parseInt(NumOfResults.getText());
        logger.info("numFound of all Data :" + numFound);
        TestBase.logExtentReport("numFound of all Data :+numFound");
        return numFound;
    }

//    public void storeNumFoundToFile() throws InterruptedException, FileNotFoundException {
//        String num = String.valueOf(GetNumFound_DefaultQuery());
//        statisticsWriter.setData("Epic_NTS_Add_UI_Last_numFound", num);
//        logger.info("stored numFound into following file :" + "statistics.properties");
//        TestBase.wait(3);
//    }

//    public int getLastNumFound() throws IOException {
//        logger.info("fetching the numFound from store file.");
//        return Integer.parseInt(statisticsWriter.getData("Epic_NTS_Add_UI_Last_numFound"));
//    }

    public int searchWithDFCI_MRN(String MRN_Number) throws InterruptedException {
        wh.wait(2000);
        logger.info("searching with the DFCI_MRN number : " + MRN_Number);
        TestBase.logExtentReport("searching with the DFCI_MRN number : " + MRN_Number);
        queryTextBox.clear();
        queryTextBox.sendKeys("DFCI_MRN:" + MRN_Number);
        executeQueryButton.click();
        wh.waitForElement(NumOfResults, 10);
        int number = Integer.parseInt(NumOfResults.getText());
        logger.info("numFound is : " + number);
        TestBase.logExtentReport("numFound is : " + number);
        return number;
    }

    public boolean negativeQuery(String NegativeQuery) throws InterruptedException {
        wh.wait(5000);
        logger.info("sending the following Negative Query : " + '"' + NegativeQuery + '"');
        TestBase.logExtentReport("sending the following Negative Query : " + NegativeQuery);
        queryTextBox.clear();
        queryTextBox.sendKeys(NegativeQuery);
        executeQueryButton.click();
        wh.waitForElement(NumOfResults, 10);
        boolean result = NumOfResults.getText().equals("0");
        logger.info("numFound is: " + NumOfResults.getText());
        logger.info("for negative query NumFound should be 0 and result is :" + result);
        return result;
    }
}
