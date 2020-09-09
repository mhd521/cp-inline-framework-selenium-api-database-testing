package utility;


import helper.ResourceHelper;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;



/**
 *
 * @author Shariar Ahmed
 *
 */

@Log4j
public class DBConn {

    public static Properties prop=null;
    private static FileInputStream propertiesFile;
    Connection con;



    public static Properties getProperties(){
//		BufferedReader reader;
        try {
            String filePath = ResourceHelper.getResourcePath("config.properties");
            propertiesFile = new FileInputStream(new File(filePath));
            prop = new Properties();
            prop.load(propertiesFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


/*
    public DBConn() throws ClassNotFoundException{

        try {
            prop = getProperties();

            // step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
           // System.out.println("Driver Loaded");

            // step2 create the connection object
            con = DriverManager.getConnection(prop.getProperty("connection_string"), prop.getProperty("db_username"),  prop.getProperty("db_password"));
            //System.out.println("Database Connected");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


 */


    //Contructor of the DB Connection;
    public DBConn() throws ClassNotFoundException{

        try {
            prop = getProperties();

            // step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
             System.out.println("Driver Loaded");
            //con = DriverManager.getConnection(prop.getProperty("connection_string"), prop.getProperty("db_username"), prop.getProperty("db_password"));

            String url = ObjectReader.reader.getEnvUrl();
            System.out.println(url);

           if(url!=null){
                    switch (url) {
                        case "https://qa-pod-as2.dfci.harvard.edu":
                            System.out.println("QA DATABASE");
                            con = DriverManager.getConnection(prop.getProperty("connection_string"), prop.getProperty("db_username"), prop.getProperty("db_password"));
                            break;
                        case "https://dv-pod-as1.dfci.harvard.edu":
                            System.out.println("DEV DATABASE");
                            con = DriverManager.getConnection(prop.getProperty("dv_connection_string"), prop.getProperty("dv_db_username"), prop.getProperty("dv_db_password"));
                            break;
                        case "https://st-pod-as1.dfci.harvard.edu":
                            System.out.println("STAGING DATABASE");
                            con = DriverManager.getConnection(prop.getProperty("st_connection_string"), prop.getProperty("st_db_username"), prop.getProperty("st_db_password"));
                            break;

                        case "https://patch-pod-as1.dfci.harvard.edu":
                            System.out.println("PATCH DATABASE");
                            con = DriverManager.getConnection(prop.getProperty("patch_connection_string"), prop.getProperty("patch_db_username"), prop.getProperty("patch_db_password"));
                            break;
                     }


                }else {
               System.out.println("Database Credential Not Available");
                }





            // step2 create the connection object
            //con = DriverManager.getConnection(prop.getProperty("connection_string"), prop.getProperty("db_username"),  prop.getProperty("db_password"));
            //System.out.println("Database Connected");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public ResultSet testdb(String sqlQuery) throws SQLException{

        ResultSet result = null;

        try {
            //DBConn dbconnect = new DBConn();
            Statement smt = con.createStatement();

            result = smt.executeQuery(sqlQuery);

        }catch (Exception e){

        }

        return result;

    }







}
