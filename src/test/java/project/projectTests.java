package project;

import base.TestBase;
import com.aventstack.extentreports.Status;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.LoginPage;
import sections.Projects;
import sections.CuratorDashBoard;
import utility.ObjectReader;

/**
 *
 * @author Shariar Ahmed
 * @author Pooja Shah
 */

@Log4j

public class projectTests extends TestBase {

    JavaScriptHelper jh = new JavaScriptHelper(driver);
    WaitHelper wh = new WaitHelper(driver);
    projectDBTest pdb = new projectDBTest();


    @Test (priority = 1, groups = {"smoke","regression"})
    public void POD2023NoOfProjects() {

        getApplicationUrl(ObjectReader.reader.getEnvUrl());
        LoginPage lp = new LoginPage(driver);
        lp.loginApp();

        Projects pj = new Projects(driver);
        int uiCount = pj.noOfProjects(driver);
        System.out.println("UI Count of Projects >>>>>>>>>" + uiCount);
        logExtentReport("UI Count of Projects >>>>>>>>>" + uiCount);
        int dbCount = pdb.countofProjects();
        System.out.println("Database Count of Projects >>>>>>>>>>" + dbCount);
        logExtentReport("Database Count of Projects >>>>>>>>>>" + dbCount);

        Assert.assertEquals(uiCount,dbCount,"Count of Project Is Not Matching!");
        test.log(Status.PASS,"Total Count of Project is Matching");


    }

    @Test(priority = 2, groups = {"smoke","regression"})
    public void POD2024_Project_Card_is_Clickable() throws Exception {
//        getApplicationUrl(ObjectReader.reader.getUrl());
//        LoginPage lp = new LoginPage(driver);
//        lp.loginApp();
//        Thread.sleep(5000);
        Projects pj = new Projects(driver);
        pj.selectPj("1", driver);
        Thread.sleep(5000);
        CuratorDashBoard wf = new CuratorDashBoard(driver);
        String acturalTitle = wf.collectProjectTitle();
        //System.out.println("Project Title is "+acturalTitle);
        logExtentReport(acturalTitle+ " Selected");
        String expectedTitle = "Project: Default Project";
        Assert.assertEquals(expectedTitle,acturalTitle,"Wrong Project Selected");
        test.log(Status.PASS, "Project Card is Clickable");

    }





}
