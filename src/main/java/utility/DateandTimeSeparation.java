package utility;


import lombok.extern.log4j.Log4j;

/**
 * @author Pooja Sha
 * @author Shariar Ahmed
 * Updated 04/04/2020
 */

@Log4j
public class DateandTimeSeparation {

    public String  separateDateandTime(String result){
        String date;
        if(result != null){
            date = result.substring(0,10);
        }else {
            date = "null";
        }

        return date;
    }


}


