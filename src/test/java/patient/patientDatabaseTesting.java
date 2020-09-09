package patient;

import lombok.extern.log4j.Log4j;
import utility.DBConn;
import utility.DateandTimeSeparation;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */

@Log4j
public class patientDatabaseTesting {

    //Select VITAL_STATUS_DESC, DFCI_FIRST_VISIT_DT, HYBRID_DEATH_DT, LAST_CONTACT_DT, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH from PATIENT where ID=21789;

    public LinkedHashMap<String,String> patientDeceasedisYES(String PID) {


        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Checking Patient Section Database Testing Results!");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String query = "Select  DFCI_FIRST_VISIT_DT, VITAL_STATUS_DESC, HYBRID_DEATH_DT_SOURCE_DESC, HYBRID_DEATH_DT, LAST_CONTACT_DT from PATIENT where ID=" + PID;


            ResultSet result = dbConn.testdb(query);

            while (result.next()) {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Creating Database Result Map!");
                DateandTimeSeparation dt = new DateandTimeSeparation();
                String dofv = dt.separateDateandTime(result.getString(1));
                String ipd = result.getString(2);
                String dods = result.getString(3);
                String dod = dt.separateDateandTime(result.getString(4));
                String lcd = dt.separateDateandTime(result.getString(5));
                map.put("DFCIFirstVisitDt",dofv);
                map.put("IsPatientDecease",ipd);
                map.put("DateOfDeathSource", dods);
                map.put("DateOfDeath",dod);
                map.put("LastContactDt",lcd);

            }

//            for (String key : map.values()) {
//                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing Yes Database the Testing Results!");
//                System.out.println(key);
//            }


        } catch (Exception e) {
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Catching Error at patientDatabaseTesting.Java line 52");
            System.out.println(e);
        }
        return map;

    }

    public LinkedHashMap<String,String> ifPatientVitalSignIsNo(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Checking Patient Section Database Testing Results!");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try{

            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String query = "Select VITAL_STATUS_DESC, DFCI_FIRST_VISIT_DT, LAST_CONTACT_DT from PATIENT where ID=" + PID;


            ResultSet result = dbConn.testdb(query);

            while (result.next()) {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Creating Database Result Map!");
                DateandTimeSeparation dt = new DateandTimeSeparation();
                String ipd = result.getString(1);
                String dofv = dt.separateDateandTime(result.getString(2));
                String lcd = dt.separateDateandTime(result.getString(3));
                map.put("IsPatientDecease",ipd);
                map.put("DFCIFirstVisitDt",dofv);
                map.put("LastContactDt",lcd);


            }
//
//            for (String key : map.values()) {
//                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing - No - Database Testing Results!");
//                System.out.println(key);
//            }

        }catch (Exception e){

        }


        return map;
    }

    public LinkedHashMap<String,String> vitalStatusChangeConfirmationFromDatabase(String PID){
       // System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Checking Patient Section Database Testing Results!");
        System.out.println("Step 8");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            System.out.println("Step 9");
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String query = "select HYBRID_DEATH_DT_SOURCE_DESC, HYBRID_DEATH_DT, HYBRID_DEATH_DT_PRECISION_DESC from PATIENT where ID="+PID;
            System.out.println(">>>>>>>>>>>>>" + query);

            ResultSet result = dbConn.testdb(query);

            while (result.next()) {
                System.out.println("Step 10");
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Creating Database Result Map!");
                DateandTimeSeparation dt = new DateandTimeSeparation();
                String deathSource = result.getString(1);
                //String doddate = dt.separateDateandTime(result.getString(2));
                String dod = result.getString(2);
                String estimationdod = result.getString(3);

                map.put("DateOfDeathSource",deathSource);
                map.put("DateOfDeath",dod);
                map.put("EstimationDateofDeath",estimationdod);


            }
            System.out.println("Step 11");
//            for (String key : map.values()) {
//                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing the Testing Results!");
//                System.out.println(key);
//            }


        } catch (Exception e) {
            System.out.println("Step 12");
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Catching Error at patientDatabaseTesting.Java line 52");
            System.out.println(e);
        }
        System.out.println("Step 13");
        return map;
    }

    public void collectPID(String MRN){

    }

    public LinkedHashMap<String,String> dataentryTest(String PID, String CurrentStatus) {


        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Checking Patient Section Database Testing Results!");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");


            if(CurrentStatus.equals("Yes")){
                String query = "Select DFCI_FIRST_VISIT_DT, VITAL_STATUS_DESC, HYBRID_DEATH_DT_SOURCE_DESC, , HYBRID_DEATH_DT, LAST_CONTACT_DT from PATIENT where ID=" + PID;
                ResultSet result = dbConn.testdb(query);

                while (result.next()) {
                    System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Creating Database Result Map!");
                    DateandTimeSeparation dt = new DateandTimeSeparation();
                    // String ipd = result.getString(1);
                    String dofv = dt.separateDateandTime(result.getString(1));
                    String vs = result.getString(2);
                    String dods = result.getString(3);
                    String dod = dt.separateDateandTime(result.getString(4));
                    String lcd = dt.separateDateandTime(result.getString(5));

                    map.put("DFCIFirstVisitDt",dofv);
                    map.put("IsPatientDecease",vs);
                    map.put("DateofDeathSource",dods);
                    map.put("DateOfDeath",dod);
                    map.put("LastContactDt",lcd);


                }

            }else {
                String query = "Select DFCI_FIRST_VISIT_DT, VITAL_STATUS_DESC,  LAST_CONTACT_DT from PATIENT where ID=" + PID;

                ResultSet result = dbConn.testdb(query);

                while (result.next()) {
                    System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Creating Database Result Map!");
                    DateandTimeSeparation dt = new DateandTimeSeparation();

                    String dofv = dt.separateDateandTime(result.getString(1));
                    String ipd = result.getString(2);
                    String lcd = dt.separateDateandTime(result.getString(3));


                    map.put("IsPatientDecease",ipd);
                    map.put("DFCIFirstVisitDt",dofv);
                    map.put("LastContactDt",lcd);


                }
            }





//            for (String key : map.values()) {
//                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing the Testing Results!");
//                System.out.println(key);
//            }


        } catch (Exception e) {
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Catching Error at patientDatabaseTesting.Java line 52");
            System.out.println(e);
        }
        return map;

    }

}
