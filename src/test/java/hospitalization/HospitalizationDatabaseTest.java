package hospitalization;

import base.TestBase;
import lombok.extern.log4j.Log4j;
import utility.DBConn;
import utility.DateandTimeSeparation;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

/**
 * @author Pooja Sah
 * Updated 04/14/2020
 */

@Log4j
public class HospitalizationDatabaseTest {

    String id = null;
    // Getting the Database Query Result for Save Fields of Hospitalization

    public LinkedHashMap<String,String> hospitalizationDatabaseTest(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Hospitalization Database Testing for Form Saving");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String query = "SELECT ID, ADMISSION_DT, ADMISSION_DT_PRECISION_DESC, DISCHARGE_DT, DISCHARGE_DT_PRECISION_DESC, INSTITUTION_DESC from HOSPITALIZATION where id=(select ID from HOSPITALIZATION where PATIENT_ID="+PID+" and UPDATED_DT=((select Max(UPDATED_DT) from HOSPITALIZATION where PATIENT_ID="+PID+")))";


            ResultSet result = dbConn.testdb(query);
            while (result.next()){

                id = result.getString(1);
                TestBase.logExtentReport("THE HOSPITALIZATION ID is "+ result.getString(1));
                DateandTimeSeparation dt = new DateandTimeSeparation();

                String admissiondt = dt.separateDateandTime(result.getString(2));
                String dischargedt = dt.separateDateandTime(result.getString(4));

                map.put("ADMISSION_DT", admissiondt);
                map.put("AdmissionDateEstimation",result.getString(3));
                map.put("DischargeDT", dischargedt);
                map.put("DischargeDateEstimation", result.getString(5));
                map.put("Institution", result.getString(6));

            }

        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }


    public LinkedHashMap<String,String> sourceDataSaveChecking(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Hospitalization Database Testing for Form Saving");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String query = "SELECT ADMISSION_DT, DISCHARGE_DT, INSTITUTION_DESC, ADMISSION_DT_PRECISION_DESC, ID from HOSPITALIZATION where id=(select ID from HOSPITALIZATION where PATIENT_ID="+PID+" and UPDATED_DT=((select Max(UPDATED_DT) from HOSPITALIZATION where PATIENT_ID="+PID+")))";


            ResultSet result = dbConn.testdb(query);
            while (result.next()){
                TestBase.logExtentReport("THE HOSPITALIZATION ID is "+ result.getString(5));
                DateandTimeSeparation dt = new DateandTimeSeparation();

                String admissiondt = dt.separateDateandTime(result.getString(1));
                String dischargedt = dt.separateDateandTime(result.getString(2));

                map.put("Estimation",result.getString(4));
                map.put("AdmissionDate", admissiondt);
                map.put("DischargeDate",dischargedt);
                map.put("Institution", result.getString(3));


            }

        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

    public String deleteQuery(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Hospitalization Database Testing for Form Saving");
        String reply = null;

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String deleteQuery = "Select count(*) from HOSPITALIZATION where id="+id;

            System.out.println("Deleted Hospitalization Form ID >>>>>>>"+ id);
            TestBase.logExtentReport("Deleted Hospitalization Form ID >>>>>>>"+ id);

            ResultSet result = dbConn.testdb(deleteQuery);
            while (result.next()){

                int count = result.getInt(1);
                System.out.println(count);
                if (count == 0) {
                    TestBase.logExtentReport("DELETED RECORD FOR HOSPITALIZATION FORM ID "+id);
                    reply = "RECORD DELETED";
                }else {
                    reply = "DELETE NOT SUCCESSFUL";
                }

            }

        }catch (Exception e){
            System.out.println(e);
        }
        return reply;
    }


    public int countOfReports(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Hospitalization Report Count From Database >>>>>>>>>>>>>>>");
        int reportcount = 0;

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            String deleteQuery = "Select count(*) from HOSPITALIZATION where PATIENT_ID="+PID;

            ResultSet result = dbConn.testdb(deleteQuery);
            while (result.next()){

                reportcount = result.getInt(1);
                System.out.println(reportcount);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return reportcount;
    }

}
