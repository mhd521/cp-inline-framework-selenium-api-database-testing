package qa;

import utility.DBConn;

import java.sql.ResultSet;

public class QADatabaseTesting {

    public String patientForAssignCurator()  {


        String MRN = null;

        try {
            DBConn dbConn = new DBConn();
            System.out.println("2");

            String query = "select DFCI_MRN from PATIENT where ID in (select PATIENT_ID from CURATION_QUEUE where PROJECT_ID=1000)";

            ResultSet result = dbConn.testdb(query);

            while (result.next()) {
                System.out.println("3");
                MRN = result.getString(1);
                System.out.println(MRN);
            }

            System.out.println("4");
        } catch (Exception e) {
            System.out.println("5");
            System.out.println("Error in the Query");

        }
        System.out.println("6");
        return MRN;
    }


}
