package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import helper.ResourceHelper;

/**
 * @author Shariar Ahmed
 */


public class ChromeBrowser {

    public ChromeOptions getChromeOptions() {

        ChromeOptions option = new ChromeOptions();
       // option.addArguments("--headless");
       // option.addArguments("--window-size=1920,1080");
        option.addArguments("--incognito");
        //option.addArguments("--auto-open-devtools-for-tabs");
        option.addArguments("--test-type");
        option.addArguments("--disable-popup-blocking");
        option.addArguments("--disable-application-cache");
        option.addArguments("--disk-cache-size=0");
        option.addArguments("--disable-extensions");

        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setJavascriptEnabled(true);

        option.setCapability(ChromeOptions.CAPABILITY, chrome);
        //Linux
        if (System.getProperty("os.name").contains("Linux")) {
            option.addArguments("--headless");
            option.addArguments("--window-size=1920,1080");
            option.addArguments("--no-sandbox");
            //option.addArguments("--disable-gpu");
           // option.addArguments("--proxy-server={None}");
           // option.addArguments("--disable-dev-shm-usage");

        }
        return option;
    }

    public WebDriver getChromeDriver(ChromeOptions cap) {

        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("src/main/resources/ui/drivers/mac-chromedriver"));
            return new ChromeDriver(cap);
        } else if (System.getProperty("os.name").contains("Window")) {
            System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("src/main/resources/ui/drivers/chromedriver.exe"));
            return new ChromeDriver(cap);
        } else if (System.getProperty("os.name").contains("Linux")) {
            System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("src/main/resources/ui/drivers/chromedriver"));
            return new ChromeDriver(cap);
        }
        return null;
    }


//	public static void main(String[] args) {
//		ChromeBrowser obj = new ChromeBrowser();
//		WebDriver driver = obj.getChromeDriver(obj.getChromeOptions());
//		driver.get("https://www.zomato.com/bangalore/punjabi-nawabi-hsr/order/LoqDdXx");
//	}

}
