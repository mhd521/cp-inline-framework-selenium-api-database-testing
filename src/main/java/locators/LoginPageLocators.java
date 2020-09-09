package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPageLocators {
    @FindBy(xpath = "//a[contains(text(),'Login into the Curation Platform')]")
    public WebElement signin;

    @FindBy(xpath = "//input[@id='okta-signin-username']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='okta-signin-password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='okta-signin-submit']")
    public WebElement submit;

    @FindBy(xpath = "//div[@class='app-title']")
    public WebElement cpVersion;

    @FindBy(xpath = "//body//mat-card[1]")
    public WebElement defaultProject;

    @FindBy(xpath = "//img[@class='auth-org-logo']")
    public WebElement partnerLogo;
    
}
