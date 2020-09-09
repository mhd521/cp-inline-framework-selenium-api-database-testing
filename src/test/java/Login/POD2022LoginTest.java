package Login;

import base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.LoginPage;
import utility.ObjectReader;


public class POD2022LoginTest extends TestBase {

   //Pooja Run This Class

    @Test (groups = {"smoke","regression"})
    public void loginTesting() throws Exception {
        //setUpDriver(ObjectReader.reader.getBrowserType());
        getApplicationUrl(ObjectReader.reader.getEnvUrl());
        LoginPage lp = new LoginPage(driver);
        boolean status = lp.loginApp();
        Assert.assertTrue(status,"Test Successful");
        test.log(Status.PASS,"Login Test Successfully Completed");


    }


}
