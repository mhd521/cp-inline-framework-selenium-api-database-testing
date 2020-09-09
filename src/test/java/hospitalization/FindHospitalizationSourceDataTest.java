package hospitalization;

import base.TestBase;
import com.aventstack.extentreports.Status;
import helper.JavaScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import sections.Curation;
import sections.CuratorDashBoard;
import sections.Hospitalization;
import sections.Patient;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindHospitalizationSourceDataTest extends TestBase {

    @Test
    public void hospitalizationSourceData() throws Exception {

        testLogin();
        Curation cs = new Curation(driver);
        cs.flagPatient("Flag");
        Thread.sleep(5000);

        String info = "Collected Informations";
        String idForTxtFile = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        File file = new File("src/test/resources/testdata/nohospitalizationdata.txt" + idForTxtFile);
        FileWriter fw = new FileWriter(file, true);
        String lineSeparator = System.getProperty("line.separator");
        String[] output = info.split("\n");

        String firstPath = "//*[@class='flagged-patients']//mat-table[@class='mat-table'][1]//mat-row[";
        String secondPath ="]//mat-cell[3]//button";


        CuratorDashBoard cds = new CuratorDashBoard(driver);
        int count = cds.countResumeCurationButtons(driver);
        int counter = 1;

        for (int i = 0; i <= count; i++){

            System.out.println("Looking for View Patient Button form Curation Completed to Date Section Row No " + counter);
            String completePath = firstPath + counter + secondPath;
            WebElement reviewPatientButton = driver.findElement(By.xpath(completePath));
            JavaScriptHelper jh = new JavaScriptHelper(driver);
            jh.scrollIntoViewAndClick(reviewPatientButton);
            Thread.sleep(5000);
            Hospitalization hp = new Hospitalization(driver);
            Patient ps = new Patient(driver);
            String MRN = ps.collectDFCIMRN();

            String admissionType = hp.countSource(driver);

            if(admissionType.equals("NS")){
                System.out.println(admissionType);
                fw.write(MRN);
                fw.write(lineSeparator);
            }else {
                System.out.println(admissionType);
                System.out.println("<<<<<<<<<<<<<<<<<<<< "+ MRN + "<<<<<<<<<<<<<<<<<<<<<<");

                cs.flagPatient("HOSPITALIZATION SOURCE DATA AVAILABLE");
                break;
            }

            cs.flagPatient("HOSPITALIZATION SOURCE DATA NOT AVAILABLE");

            counter ++;


        }

        fw.flush();
        fw.close();






    }
}
