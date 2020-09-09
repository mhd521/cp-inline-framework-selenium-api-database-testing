package locators;


import lombok.extern.log4j.Log4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class CancerProfileLocators {

    @FindBy(css = "#cancersAssociated input")
    public WebElement CancersAssociated;

    @FindBy(xpath = "//div[contains(text(),'Cancer Profiling Summary')]")
    public WebElement cancerProfilingSummeryLink;

    @FindBy(id = "toggle-notes-panel")
    public WebElement referencePanelBar;

    @FindBy(xpath = "//*[contains(text(),'Last Saved')]")
    public WebElement LastSaved;

    @FindBy(xpath = "//mat-icon[contains(text(),'history')]")
    public WebElement curationHistoryIcon;

    @FindBy(xpath = "//mat-action-row[@class='mat-action-row']")
    public WebElement matActionRow;

    @FindBy(xpath = "//div[@class='navbar-container']")
    public WebElement nevBar;

    @FindBy (xpath = "//mat-sidenav-content[@class='mat-drawer-content mat-sidenav-content']//mat-row")
    public WebElement cancerTebleRowselection;

    public String cancerTableRow = "//mat-table[@class='summary-table mat-table']//mat-row";

    @FindBy(xpath = "//span[contains(text(),'Save Section')]")
    public WebElement saveButtonXpath;

    @FindBy(css = "#reportDate input")
    public WebElement reportDt;
}
