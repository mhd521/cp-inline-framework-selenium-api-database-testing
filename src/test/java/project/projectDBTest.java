package project;



import utility.DBConn;

import java.sql.ResultSet;

public class projectDBTest {
    //Project Count
    public int countofProjects() {

        int count = 0;

        try {

            DBConn dbConn = new DBConn();
            ResultSet result = dbConn.testdb("select count(*) from project");
            while (result.next()) {
                System.out.println("Im Running");
                count = result.getInt(1);


            }

        } catch (Exception e) {

            System.out.println("Error in the Query");


        }
        //System.out.println("<<<<<<"+count);
        return count;

    }

    // New Patient for Assigning in to the Project

    public String patientMRNforPorjectAssignment() {

        int MRN = 0;


        try {

            DBConn dbConn = new DBConn();
            ResultSet result = dbConn.testdb("select q.PATIENT_ID, P.DFCI_MRN from CURATION_QUEUE Q\n" +
                    "Left JOIN CURATION C\n" +
                    "on Q.PATIENT_ID = C.PATIENT_ID\n" +
                    "JOIN PATIENT P\n" +
                    "on Q.PATIENT_ID=P.ID\n" +
                    "Where C.USER_ID is Null\n" +
                    "AND ROWNUM=1;");

            while (result.next()) {

                MRN = result.getInt(1);
                System.out.println("MRN");



            }



        } catch (Exception e) {

            System.out.println("Error in the Query");


        }
        String DFCIMRN = Integer.toString(MRN);
        return DFCIMRN;
    }


}
