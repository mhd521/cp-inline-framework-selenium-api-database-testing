package sections;


import base.TestBase;
import helper.JavaScriptHelper;
import helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ObjectReader;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Projects extends TestBase {

    private WebDriver driver;
    WaitHelper wh;
    JavaScriptHelper jh;


    @FindBy(xpath = "//body//mat-card[1]")
    WebElement DefaultPJ;

    @FindBy(xpath = "//body//mat-card[2]")
    WebElement DefaultPJinPatch;

    @FindBy(xpath = "//body//mat-card[2]")
    WebElement DefaultPJDev;

    @FindBy(xpath = "//body//mat-card[2]")
    WebElement testPJ01;

    @FindBy(xpath = "//body//mat-card[3]")
    WebElement testPJ02;

    @FindBy(xpath = "//body//mat-card[4]")
    WebElement testPJ03;

    @FindBy(xpath = "//body//mat-card[5]")
    WebElement testPJ04;

    @FindBy(xpath = "//body//mat-card[6]")
    WebElement testPJ05;

    @FindBy(xpath = "//body//mat-card[7]")
    WebElement testPJ06;

    @FindBy(xpath = "//body//mat-card[8]")
    WebElement testPJ07;

    @FindBy(xpath = "//h3[contains(text(),'Please select a project below')]")
    WebElement projectPageDisplayMessage;

    @FindBy(xpath = "//h1[@class='project-title']")
    WebElement projectTitle;

    @FindBy(xpath = " //body//mat-card" )
    WebDriver allprojects;



    // Constructor for this Page ///
    public Projects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wh = new WaitHelper(driver);
        wh.pageLoadTime(30, TimeUnit.SECONDS);
        jh = new JavaScriptHelper(driver);
        TestBase.logExtentReport("We are at Project Selection Page!");
        //  new TestBase().getNavigationScreen(driver);

    }


    public boolean projectPageSuccessMessage(){

        wh.waitUntilTextAppear(projectPageDisplayMessage, "Please select a project below", 5);
        projectPageDisplayMessage.isDisplayed();
        jh.highLightElement(projectPageDisplayMessage);
        return true;


    }
    public void selectDefaultPjDev(){

        wh.WaitForElementVisibleWithPollingTime(DefaultPJDev,30,5);
        jh.hightligtandCLick(DefaultPJDev);
        wh.WaitForElementVisibleWithPollingTime(projectTitle,30,5);

    }

    public void selectDefaultProject(){

        wh.WaitForElementVisibleWithPollingTime(DefaultPJ,30,5);
        jh.hightligtandCLick(DefaultPJ);
        wh.WaitForElementVisibleWithPollingTime(projectTitle,30,5);

    }

    public void selectProject(){
        String env = ObjectReader.reader.env();
        switch (env) {
            case "st":
                System.out.println("<<<<<<<<<<<<<<< THIS IS STAGING >>>>>>>>>>>>>>>>>>>>>>>>>>");
                selectDefaultProject();
                break;
            case "qa":
                System.out.println("<<<<<<<<<<<<<<< THIS IS QA >>>>>>>>>>>>>>>>>>>>>>>>>>");
                selectDefaultProject();
                break;
            case "prod":
                System.out.println("<<<<<<<<<<<<<<< THIS IS PRODUCTION >>>>>>>>>>>>>>>>>>>>>>>>>>");
                selectDefaultProject();
                break;
            case "dev":
                System.out.println("<<<<<<<<<<<<<<< THIS IS DEVELOPMENT >>>>>>>>>>>>>>>>>>>>>>>>>>");
                selectDefaultPjDev();
                break;
            case "patch":
                System.out.println("<<<<<<<<<<<<<<< THIS IS PATCH >>>>>>>>>>>>>>>>>>>>>>>>>>");
                selectDefaultProject();
                break;
            default:
                System.out.println("<<<<<<<<<<<<<<< NO ENVIRONMENT SELECTED >>>>>>>>>>>>>>>>>>>>>>>>>>");
                selectDefaultProject();
                break;
        }
    }


    public String selectDefaultProjectInPatch(){

        wh.WaitForElementClickable(DefaultPJinPatch,5);
        jh.hightligtandCLick(DefaultPJinPatch);
        wh.WaitForElementVisibleWithPollingTime(projectTitle,5,5);
        String PJID="1000";
        return PJID;

    }


    public void selectPj(String cardNo, WebDriver driver){
        //xpath = "//body//mat-card[2]"

        driver.findElement(By.xpath("//body//mat-card["+cardNo+"]")).click();


    }


    public String selectTestPJ02(){

        wh.WaitForElementClickable(testPJ01,2);
        jh.hightligtandCLick(testPJ01);
        wh.WaitForElementVisibleWithPollingTime(projectTitle,5,5);
        String PJID="1001";
        return PJID;


    }

    public String selectTestPJ04(){

        wh.WaitForElementClickable(testPJ04,5);
        jh.hightligtandCLick(testPJ04);
        wh.WaitForElementVisibleWithPollingTime(projectTitle,5,5);
        String PJID="1383";
        return PJID;


    }

    public String projectId(){

        String project_id = null;

        String env = ObjectReader.reader.env();
        if(env.equals("qa")){
            project_id="1000";
        }else if(env.equals("dev")){
            project_id="1000";
        }else if(env.equals("st")){
            project_id="1000";
        }

        return project_id;

    }




    // To identify the Projects//

    public String identifyProject(String PROJECTID){

        int proID = Integer.parseInt(PROJECTID);

        HashMap<Integer, String > map = new HashMap<>();

        map.put(1000, "Default Project");
        map.put(1080, "Shariar_Project1");
        map.put(1001, "QA Test Project");
        map.put(1381, "Test Project 02");
        map.put(1382, "Test Project 03");
        map.put(1383, "Test Project 04");
        map.put(1384, "Test Project 05");
        map.put(1385, "Test Project 06");
        map.put(1385, "Test Project 07");

        String projectyName = "";


        if(map.containsKey(proID)){

            projectyName = map.get(proID);



            // System.out.println(map.get(UserID));
        }

        return projectyName;





    }

    public String selectProject(String PROJECTID)throws InterruptedException{

        int proID = Integer.parseInt(PROJECTID);

        System.out.println("Im Selecting the Project Please Wait .. "+proID);

        HashMap<Integer, String > map = new HashMap<>();

        map.put(1000, "Default Project");
        map.put(1080, "Shariar_Project1");
        map.put(1001, "QA Test Project");
        map.put(1381, "Test Project 02");
        map.put(1382, "Test Project 03");
        map.put(1383, "Test Project 04");
        map.put(1384, "Test Project 05");
        map.put(1385, "Test Project 06");
        map.put(1385, "Test Project 07");

        String projectName = "";


        if(map.containsKey(proID)){

            projectName = map.get(proID);

            // Thread.sleep(5000);

            // System.out.println(map.get(UserID));
        }

        System.out.println("Project Selected "+projectName+"");

        return projectName;



    }

    public int noOfProjects(WebDriver driver) {
        int projectsCount=0;
        projectPageDisplayMessage.click();
        List<WebElement> all = driver.findElements(By.xpath("//body//mat-card"));
        projectsCount = all.size();
        return projectsCount;
    }



}
