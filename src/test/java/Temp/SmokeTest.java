package Temp;

import temp.LoginPage;
import base.TestBase;
import org.testng.annotations.Test;
import utility.ObjectReader;

public class SmokeTest extends TestBase {


    @Test
    public void LoginTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        setUpDriver(ObjectReader.reader.getBrowserType());
        getApplicationUrl(ObjectReader.reader.get("CTS_URL"));
        loginPage.login(driver);
        //Assert.assertTrue(homePage.verifyLogin());
    }
}
