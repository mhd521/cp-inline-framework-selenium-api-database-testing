package cancer;

import base.TestBase;
import org.testng.annotations.Test;
import utility.DBConn;
import utility.ReadFromHashMap;

import javax.sound.midi.Soundbank;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CancerDatabaseTest {

    public LinkedHashMap<String, String> cancerDataReadFromDatabase(String PID, String filePath) throws ClassNotFoundException, SQLException, InterruptedException {

        //tring PID="16080";
        ReadFromHashMap rmh = new ReadFromHashMap();
        String allFields = rmh.readHashMapTextFileWithComma(filePath);
        DBConn dbConn = new DBConn();
        //String allFields = "CANCER_TYPE_DESC, SEQUENCE_NUMBER_DESC, PRIMARY_SITE_DESC, HISTOLOGY_AND_BEHAVIOR_DESC, DIAGNOSIS_CONF_DESC, LATERALITY_DESC, GRADE_DESC, DIAGNOSIS_DATE_CURATED, DIAGNOSIS_DT_SOURCE_DESC, DIAGNOSING_INST_DESC, AJCC_GROUP_BEST_STAGE, DIAGNOSIS_STAGE_IV_IND_DESC, AJCC_TNM_VERSION_DESC, CLINICAL_STAGE_GROUP_DESC, PATHOLOGIC_STAGE_GROUP_DESC, SSF_1_DESC, SSF_2_DESC, SSF_3_DESC, SSF_4_DESC, SSF_5_DESC, SSF_6_DESC, SSF_7_DESC, SSF_8_DESC, SSF_9_DESC, SSF_10_DESC, SSF_11_DESC, SSF_12_DESC, SSF_13_DESC, SSF_14_DESC, SSF_15_DESC, SSF_16_DESC, SSF_21_DESC, SSF_22_DESC, SSF_23_DESC, ";
        String query = "Select " + allFields + "from Cancer where ID=(select ID from CANCER where PATIENT_ID=" + PID + " and UPDATED_DT=((select Max(UPDATED_DT) from CANCER where PATIENT_ID=" + PID + ")))";
        String finalQuery = query.replaceAll(", from", " from");
        System.out.println(finalQuery);

        String[] pairs = allFields.replaceAll(", ", " ").split(" ");
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        Thread.sleep(10000);
        ResultSet result = dbConn.testdb(finalQuery);
        while (result.next()) {
            for (int i = 0; i < pairs.length; i++) {
                String key = pairs[i];
                String value = result.getString(i + 1);
                System.out.println("........" + key + "........." + value);
                map.put(key, value);
            }

        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Step 7");
            String k = entry.getKey();
            System.out.println(">>>>>> Reply from Database, Key = " + k);
            String v = entry.getValue();
            System.out.println("<<<<<<< Reply from Database, Value= " + v);
            TestBase.logExtentReport("<<<<<<< Reply from Database, Key= " + k + " Value= " + v);

        }

        return map;
    }

    public ArrayList<String>  cancerTypeForCancerProfile(String PID) throws ClassNotFoundException, SQLException {

        DBConn dbConn = new DBConn();
        String query = "select cancer_type_desc from CANCER where PATIENT_ID ="+PID;
        System.out.println(query);
        ResultSet result = dbConn.testdb(query);
        ArrayList<String> resultList = new ArrayList<>();


        while (result.next()) {
            String cancerType = result.getString(1);
            resultList.add(cancerType);
        }
        return resultList;


    }

}
