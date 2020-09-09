package Temp;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import sections.QueryPage;
import utility.ObjectReader;

import java.io.IOException;

public class Epic_Notes_Add_UI extends TestBase {


//    @Test(priority = 0)
//    public void storeDefaultValue() throws IOException {
//        statisticsWriter.defaultValue();
//    }

    @Test(priority = 1)
    public void loginTest() {
        QueryPage page = new QueryPage(driver);

        getApplicationUrl("https://tm905:Gn4dE5aV@dv-emerse-as3.dfci.harvard.edu:8983/solr/#/epic_notes_add/query");
        Assert.assertTrue(page.verifyLogin(), "login has failed");
    }

    //    @Test(priority = 2)
//    public void matchNumFoundAndLastNumFound() throws IOException, InterruptedException {
//        Assert.assertEquals(page.GetNumFound_DefaultQuery(),page.getLastNumFound());
//        page.storeNumFoundToFile();
//    }
//    @Test(priority = 3)
//    public void DFCI_MRNTest() throws InterruptedException {
//        Assert.assertTrue(page.searchWithDFCI_MRN("627226") >= 1);
//    }


}
