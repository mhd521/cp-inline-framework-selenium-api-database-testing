package base;
import QAMode.QALogin;
import browsers.BrowserType;
import browsers.ChromeBrowser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import helper.JavaScriptHelper;
import helper.LoggerHelper;
import helper.ResourceHelper;
import helper.WaitHelper;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import sections.LoginPage;
import sections.Projects;
import sections.CuratorDashBoard;
import utility.ExtentManager;
import utility.ObjectReader;
import utility.PropertyReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Shariar Ahmed
 */


public class TestBase {

    public static ExtentReports extent;
    public static ExtentTest test;
    public WebDriver driver;
    private Logger log = LoggerHelper.getLogger(TestBase.class);
    public static File reportDirectory;

    @BeforeSuite
    public void beforeSuite() throws Exception {
        extent = ExtentManager.getInstance();
    }


    @BeforeTest
    public void beforeTest() throws Exception {
        ObjectReader.reader = new PropertyReader();
        reportDirectory = new File("ScreenShots");

        // setUpDriver(ObjectReader.reader .getBrowserType());
        test = extent.createTest(getClass().getSimpleName());

    }

    @BeforeClass
    public void beforeClass() throws Exception {

    }

    @BeforeMethod
    public void beforeMethod(Method method) throws Exception {
        test.log(Status.INFO, method.getName() + "************** test started ***************");
        log.info("************** " + method.getName() + " Started *************** ");
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            //printLogs(driver);
            test.log(Status.FAIL, result.getThrowable());
            String imagePath =captureScreen(result.getName(), driver);
            System.out.println(">>>>>>>>>>>> "+ imagePath + "<<<<<<<<<<<<<<");
            test.addScreenCaptureFromPath("../"+imagePath);
            test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath("../"+imagePath));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName() + " is pass");
            String imagePath =captureScreen(result.getName(), driver);
            System.out.println(">>>>>>>>>>>> "+ imagePath + "<<<<<<<<<<<<<<");
            test.addScreenCaptureFromPath("../"+imagePath);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, result.getThrowable());
        }
        log.info("**************" + result.getName() + "Finished *************** ");
        extent.flush();

    }

    @AfterTest
    public void afterTest() throws Exception {
        if (driver != null) {
            extent.flush();
            driver.quit();
        }
    }
    @AfterClass
    public void afterClass()throws Exception{
        if (driver != null) {
            extent.flush();
            driver.quit();
        }
    }

    public WebDriver getBrowserObject(BrowserType btype) throws Exception {

        try {
            // get object of ChromeBrowser class
            ChromeBrowser chrome = ChromeBrowser.class.newInstance();
            ChromeOptions chromeOptions = chrome.getChromeOptions();
            return chrome.getChromeDriver(chromeOptions);

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    public void setUpDriver(BrowserType btype) throws Exception {
        driver = getBrowserObject(btype);
        log.info("Initialize Web driver: " + driver.hashCode());
        WaitHelper wait = new WaitHelper(driver);
        wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);
        wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public void closeDriverAfterTest() {
        if (driver != null) {
            extent.flush();
            driver.quit();
        }
    }

    public String captureScreen(String fileName, WebDriver driver) {
        if (driver == null) {
            log.info("driver is null..");
            return null;
        }
        if (fileName == "") {
            fileName = "blank";
        }
        Reporter.log("captureScreen method called");
        File destFile = null;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
        File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            destFile = new File(reportDirectory + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
            Files.copy(screFile.toPath(), destFile.toPath());
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/></a>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return destFile.toString();
    }

    public void getNavigationScreen(WebDriver driver) {
        log.info("capturing ui navigation screen...");
        new JavaScriptHelper(driver).zoomInBy60Percentage();
        String screen = captureScreen("", driver);
        new JavaScriptHelper(driver).zoomInBy100Percentage();
        try {
            test.addScreenCaptureFromPath("../"+screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logExtentReport(String s1) {
        test.log(Status.INFO, s1);
    }


    public void getApplicationUrl(String url) {
        driver.get(url);
        logExtentReport("navigating to ..." + url);
    }

    public void logBrowserConsoleLogs() {
        log.info("================== BROWSER LOGS =======================");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            log.info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        log.info("=======================================================");
    }


    public void testLogin() throws Exception {
        setUpDriver(ObjectReader.reader.getBrowserType());
        driver.manage().window().maximize();
        // Calling the App and Login
        getApplicationUrl(ObjectReader.reader.getEnvUrl());

        //OCTA Login Page
        try{
            LoginPage lp = new LoginPage(driver);
            lp.loginApp();

        }catch (Exception e){
            System.out.println("<<<<<<<<<<<<<<<< Error in Login Page >>>>>> " + e);
        }
        Thread.sleep(10000);
        // Project Selection Page
        try{
            Projects pj = new Projects(driver);
            pj.selectProject();
        }catch (Exception e){
            System.out.println("<<<<<<<<<<<<<<<< Error in Project Selection >>>>>> " + e);
        }

        Thread.sleep(10000);
        // Patient Selection Page

        try{
            CuratorDashBoard cdb = new CuratorDashBoard(driver);
            cdb.selectingPatientForCuration();
            //cdb.openSpecificPatient(driver,"653807");
        }catch (Exception e){
            System.out.println("<<<<<<<<<<<<<<<< Error in  Patient Selection >>>>>> " + e);
        }


    }

    public void printLogs(WebDriver driver){
        LogEntries logs = driver.manage().logs().get("browser");
        for (LogEntry entry : logs) {
            System.out.println(entry.getMessage());
        }

    }

    public void qaLogin() throws Exception {

        setUpDriver(ObjectReader.reader.getBrowserType());
        driver.manage().window().maximize();
        // Calling the App and Login
        getApplicationUrl(ObjectReader.reader.getEnvUrl());

        try{
            LoginPage lp = new LoginPage(driver);
            lp.qaModeLogin();

        }catch (Exception e){
            System.out.println("<<<<<<<<<<<<<<<< Error in Login Page >>>>>> " + e);
        }

        // Project Selection Page
        try{
            Projects pj = new Projects(driver);
            pj.selectProject();
        }catch (Exception e){
            System.out.println("<<<<<<<<<<<<<<<< Error in Project Selection >>>>>> " + e);
        }
    }

    public void takeScreenShotWhenEver(String methodname) throws IOException {
        String imagePath =captureScreen(methodname, driver);
        System.out.println(">>>>>>>>>>>> "+ imagePath + "<<<<<<<<<<<<<<");
        test.addScreenCaptureFromPath("../"+imagePath);
    }



//	public Object[][] getExcelData(String excelName, String sheetName){
//		String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configfile/")+excelName;
//		log.info("excel location "+excelLocation);
//		ExcelHelper excelHelper = new ExcelHelper();
//		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
//		return data;
//	}


}
