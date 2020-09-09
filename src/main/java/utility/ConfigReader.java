package utility;

import browsers.BrowserType;

/**
 *
 * @author Shariar Ahmed
 *
 */

public interface ConfigReader {

    public int getImpliciteWait();
    public int getExplicitWait();
    public int getPageLoadTime();
    public int getPollingTime();
    public int shortsleepTime();
    public BrowserType getBrowserType();
    public String getUserName();
    public String getPassword();
    public String getEnvUrl();
    public String env();
    public String getQAUserName();
    public String getQAPassword();
    public String get(String property);

}
