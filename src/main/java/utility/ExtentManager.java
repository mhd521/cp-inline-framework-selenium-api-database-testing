package utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helper.ResourceHelper;

/**
 *
 * @author Shariar Ahmed
 *
 */

public class ExtentManager {
    private static ExtentReports extent;



    public static ExtentReports getInstance(){
        if(extent == null){
            DateFunctions df = new DateFunctions();
            String dateToday = df.currentDateWithTimeStamp();
            String location = ResourceHelper.getResourcePath("Reports/extent_"+dateToday+".html");
            return createInstance(location);
        }
        else{
            return extent;
        }
    }

    public static ExtentReports createInstance(String fileName){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }



}
