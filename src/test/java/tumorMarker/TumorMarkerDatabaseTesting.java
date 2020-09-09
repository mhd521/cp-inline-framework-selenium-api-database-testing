package tumorMarker;

import lombok.extern.log4j.Log4j;
import utility.DBConn;
import utility.DateandTimeSeparation;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */

@Log4j
public class TumorMarkerDatabaseTesting {

    // Getting the Database Query Result for Save Fields of Tumor Marker

    public LinkedHashMap<String,String> tumorMarkerDatabaseTest(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Tumor Marker Database Testing for Form Saving");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");
            //Getting the Latest Tumor Marker Form which we have just created

//            String idQuery = "select max(ID) from TUMOR_MARKER where PATIENT_ID="+PID;
//            ResultSet reply = dbConn.testdb(idQuery);
//            int tumorMarkerID=0;
//            while (reply.next()){
//                tumorMarkerID = reply.getInt(1);
//            }
//
//            System.out.println(">>>>>>>>>>>>>>>>>> "+tumorMarkerID);

            //Using the Latest Tumor Marker ID to build the Query
            //String query = "SELECT TEST_NAME_DESC, LAB_INSTITUTION_DESC, SPECIMEN_DT, SPECIMEN_DT_PRECISION_DESC, RESULT_NUM, RESULT_UNIT_DESC, RESULT_TEXT, OUTSIDE_RANGE_IND_DESC from TUMOR_MARKER where ID="+tumorMarkerID;

            String query = "SELECT TEST_NAME_DESC, LAB_INSTITUTION_DESC, SPECIMEN_DT, SPECIMEN_DT_PRECISION_DESC, RESULT_NUM, RESULT_UNIT_DESC, RESULT_TEXT, OUTSIDE_RANGE_IND_DESC from TUMOR_MARKER where id=(select ID from TUMOR_MARKER where PATIENT_ID="+PID+"and SOURCE_ID is null and UPDATED_DT=((select Max(UPDATED_DT) from TUMOR_MARKER where PATIENT_ID="+PID+")))";


            ResultSet result = dbConn.testdb(query);
            while (result.next()){

                DateandTimeSeparation dt = new DateandTimeSeparation();
                //String testName= result.getString(1);
                //String institutionn = result.getString(2);
                String specimentdt = dt.separateDateandTime(result.getString(3));
               // String specimenEstimation = result.getString(4);
               // String resultNumber = result.getString(5);
               // String resultUnit = result.getString(6);
               // String resultText = result.getString(7);
               // String range = result.getString(8);

                map.put("TestName",result.getString(1));
                map.put("Institution",result.getString(2));
                map.put("SpecimenDT", specimentdt);
                map.put("Estimation",result.getString(4));
                map.put("ResultNumber", result.getString(5));
                map.put("ResultUnit", result.getString(6));
                map.put("ResultText", result.getString(7));
                map.put("Range", result.getString(8));

            }

//            for (String key : map.values()) {
//                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Printing the Testing Results!");
//               System.out.println(key);
//            }

        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

    //Getting the Total Count of Tumor Marker Report from Database
    public int tumorMarkerCountfromDatabase(String PID){
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Tumor Marker Report Count from Database ");
        int count = 0;

        try {
            DBConn dbConn = new DBConn();
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Running Query in the Database");

            String query = "select count(*) from TUMOR_MARKER where PATIENT_ID="+PID;

            ResultSet result = dbConn.testdb(query);

            while (result.next()){
                count = result.getInt(1);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return count;

    }

}
