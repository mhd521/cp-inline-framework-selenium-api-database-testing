package Temp;

import base.TestBase;
import org.testng.annotations.Test;
import sections.QueryPage;
import temp.google;
import utility.ObjectReader;

public class Temp extends TestBase {
    @Test
    public void test() throws Exception {

        setUpDriver(ObjectReader.reader.getBrowserType());
        driver.manage().window().maximize();
        // Calling the App and Login
        getApplicationUrl(ObjectReader.reader.getEnvUrl());
        google google = new google(driver);
        google.search();


    }


}
