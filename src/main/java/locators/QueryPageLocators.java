package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QueryPageLocators {
    @FindBy(id ="solr")
    public WebElement SolrLogo;

    @FindBy(xpath = "//textarea[@id='q']")
    public WebElement queryTextBox;

    @FindBy(css =  "#response > pre > code > span:nth-child(4) > span:nth-child(2) > span")
    public WebElement NumOfResults;

    @FindBy(css = "#response > pre > code > span:nth-child(4) > span:nth-child(2) > span")
    public WebElement statusCode;

    @FindBy(xpath = "//button[contains(text(),'Execute Query')]")
    public WebElement executeQueryButton;
}
