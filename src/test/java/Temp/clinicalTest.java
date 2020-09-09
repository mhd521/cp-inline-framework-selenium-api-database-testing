package Temp;

import base.TestBase;
import org.testng.annotations.Test;
import temp.clinical;
import utility.ObjectReader;

public class clinicalTest extends TestBase {

    @Test
    public void test01() throws Exception {
        setUpDriver(ObjectReader.reader.getBrowserType());
        driver.manage().window().maximize();
        getApplicationUrl(ObjectReader.reader.getEnvUrl());
        clinical cl = new clinical(driver);
        cl.login();
        Thread.sleep(10000);
    }


}
