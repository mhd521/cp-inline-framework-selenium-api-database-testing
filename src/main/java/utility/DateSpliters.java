package utility;

public class DateSpliters {

    public String dateSpliter(String Date){
       // String date = "04-13-2015";
        String[] result = new String[3];
        result = Date.split("-");
        String mm = result[0];
        String dt = result[1];
        String yyyy = result[2];

        String finalresult = yyyy+"-"+mm+"-"+dt;

        System.out.println(finalresult);

        return finalresult;
    }

    public String dateSpliterOppsoit(String Date){
        // String date = "04-13-2015";
        String[] result = Date.split("-");
        String mm = result[0];
        String dt = result[1];
        String yyyy = result[2];

        String finaldate= mm+"-"+dt+"-"+yyyy;

        System.out.println(finaldate);

        return finaldate;
    }




}
