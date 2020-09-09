package utility;


import browsers.BrowserType;
import helper.ResourceHelper;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 *
 * @author Shariar Ahmed
 *
 */

public class PropertyReader implements ConfigReader{
    private static FileInputStream file;
    public static Properties OR;

    public PropertyReader() {
        try {
            String filePath = ResourceHelper.getResourcePath("config.properties");
            file = new FileInputStream(new File(filePath));
            OR = new Properties();
            OR.load(file);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getImpliciteWait() {
        return Integer.parseInt(OR.getProperty("implicitwait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(OR.getProperty("explicitwait"));
    }

    public int getPageLoadTime() {
        return Integer.parseInt(OR.getProperty("pageloadtime"));
    }

    public int getPollingTime() {
        return Integer.parseInt(OR.getProperty("pollingtime"));
    }

    public BrowserType getBrowserType() {
        return BrowserType.valueOf(OR.getProperty("browserType"));
    }



    public String getUserName() {
        if(System.getProperty("userName")!=null){
            return System.getProperty("userName");
        }
        return OR.getProperty("userName");
    }

    public String getPassword() {
        if(System.getProperty("password")!=null){
            return System.getProperty("password");
        }
        return OR.getProperty("password");
    }

    public String getQAUserName(){
        if(System.getProperty("qaUserName")!=null){
            return System.getProperty("qaUserName");
        }
        return OR.getProperty("qaUserName");

    }

    public String getQAPassword(){
        if(System.getProperty("qaPassword")!=null){
            return System.getProperty("qaPassword");
        }
        return OR.getProperty("qaPassword");
    }

    public String get(String property){
        return OR.getProperty(property);
    }


    public int shortsleepTime() {return Integer.parseInt(OR.getProperty("shortsleeptime"));
    }

    public String env(){
        return OR.getProperty("env");
    }


    public String getEnvUrl(){

        String env = OR.getProperty("env");
        String url = null;

            switch (env) {
                case "qa":
                    System.out.println("<<<<<<<<< YOU ARE GOING TO RUN YOUR TEST IN QA ENVIRONMENT >>>>>>>");
                    url = OR.getProperty("qa");
                    return url;

                case "dev":
                    System.out.println("<<<<<<<<< YOU ARE GOING TO RUN YOUR TEST IN DEV ENVIRONMENT >>>>>>>");
                    url = OR.getProperty("dev");
                    return url;

                case "st":
                    System.out.println("<<<<<<<<< YOU ARE GOING TO RUN YOUR TEST IN STAGING ENVIRONMENT >>>>>>>");
                    url = OR.getProperty("st");
                    return url;

                case "patch":
                    System.out.println("<<<<<<<<< YOU ARE GOING TO RUN YOUR TEST IN PATCH ENVIRONMENT >>>>>>>");
                    url = OR.getProperty("patch");
                    return url;

                case "prod":
                    System.out.println("<<<<<<<<< YOU ARE GOING TO RUN YOUR TEST IN PRODUCTION ENVIRONMENT >>>>>>>");
                    url = OR.getProperty("prod");
                    return url;

            }
        return url;


    }



}
