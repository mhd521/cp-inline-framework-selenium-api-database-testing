package database;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;
import sections.Patient;
import utility.DBConn;

import java.sql.ResultSet;


/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */


@Log4j
public class DatabaseTesting {


    public String patientIDFind(String MRN){

        int PID=0;
        try {
            System.out.println(">>>>>>>Finding Patient ID >>>>>>>>>");
            DBConn dbConn = new DBConn();
            String query = "select max(ID) from PATIENT where DFCI_MRN = "+MRN+"\n" +
                    "order by ID desc";

            ResultSet result = dbConn.testdb(query);
            while (result.next()) {
                //System.out.println(">>>>>>>>>>>> Checking Database Resultset!");
                PID = result.getInt(1);
                System.out.println("Patient PID >>>>" +PID);

            }
        }catch (Exception e){
            System.out.println(">>>>>>>>>>>> Catching Database Testing Error!");
            System.out.println(e);
        }
        String pid = Integer.toString(PID);

        return pid;
    }

}
