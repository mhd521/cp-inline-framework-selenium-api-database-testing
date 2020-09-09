package utility;

import org.testng.annotations.Test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateFunctions {

    public String currentDateWithTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_yyyy_hh_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String dateToday= dtf.format(now);
        System.out.println(dateToday);
        //return datetoFileName(dateToday);
        return dateToday;

    }

    public String datetoFileName(String dateToday){
        // String date = "04-13-2015";
        String[] result = new String[3];
        result = dateToday.split("/");
        String mm = result[0];
        String dt = result[1];
        String yyyy = result[2];

        String finalresult = mm+"_"+dt+"_"+yyyy;

        System.out.println(finalresult);

        return finalresult;
    }

    @Test
    public void dateTest(){
        String f = currentDateWithTimeStamp();
        System.out.println(f);
    }



    public List<String> genereateTwoDates(String yourDate) {
        List<String> list = new ArrayList<String>();

        // LocalDate localDate = DateWithoutTime.getLocalDate();
        SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");
        String acceptDated = yourDate;

        int count = 2;
        for (int i = 0; i <= count - 1; i++) {
            try {

                Date date = format.parse(acceptDated);
                Calendar cal = Calendar.getInstance();

                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                cal.setTime(date);
                cal.add(Calendar.DATE, +2);

                Date beforeDate = cal.getTime();

                //System.out.println(beforeDate);

                acceptDated = format.format(beforeDate);

                list.add(acceptDated);


            } catch (Exception e) {

            }

        }

        //System.out.println(list);

        return list;
    }


    public List<String> randomDates(int start, int end) {
        List<String> list = new ArrayList<String>();
        String acceptedDate = null;

        SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");


        for (int i = 0; i < 2; i++) {
            LocalDate randomDate = createRandomDate(start, end);


            acceptedDate = randomDate.toString();


            list.add(acceptedDate);

            System.out.println(acceptedDate);

        }

        return list;


    }


    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

     @Test
    public String currentDateNow() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        Date newdate = dateFormat.parse(currentDate);

        String newdt = dateFormat.format(newdate);

        System.out.println(newdt);

        return newdt;

    }


    public String[] oneYearsBefore(String yourDate) {

        String result[] = new String[4];

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        String orgDate = yourDate;


        try {
            Date date = format.parse(orgDate);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);

            cal.add(Calendar.YEAR, -1);

            Date beforeDate = cal.getTime();

            String newdate = format.format(beforeDate);
            result = newdate.split("-");
            String mm = result[0];
            String dd = result[1];
            String yyyy = result[2];


        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(result);
        return result;


    }

    public String[] fiveYearsBefore() {

        String result[] = new String[4];

        SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");

        String orgDate = null;


        try {
            Date date = format.parse(orgDate);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);

            cal.add(Calendar.YEAR, -5);

            Date beforeDate = cal.getTime();

            String newdate = format.format(beforeDate);
            result = newdate.split("-");
            String mm = result[0];
            String dd = result[1];
            String yyyy = result[2];


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;


    }


    public String[] dateofBirth(String dob) {

        String result[] = new String[4];

        SimpleDateFormat format = new SimpleDateFormat("mm-dd-yyyy");


        String orgDate = dob;

        try {
            Date date = format.parse(orgDate);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);

            cal.add(Calendar.YEAR, -5);

            Date beforeDate = cal.getTime();

            String newdate = format.format(beforeDate);
            result = newdate.split("-");
            String mm = result[0];
            String dd = result[1];
            String yyyy = result[2];


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;


    }

    public String currentDate() {

        //  DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        // Date date = new Date();
        // String currentDate = dateFormat.format(date);

        Calendar rightNow = Calendar.getInstance();

        java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM-dd-YYYY");

        String dt = df1.format(rightNow.getTime());

        return dt;

    }

    @Test
    public void datetest() throws ParseException {
        String dt = currentDateNow();
        System.out.println(dt);
    }

    public void fiveDates(){

        LocalDate startDate = LocalDate.of(1990, 1, 1); //start date
        long start = startDate.toEpochDay();
        System.out.println(",,,,"+start);

        LocalDate endDate = LocalDate.now(); //end date
        long end = endDate.toEpochDay();
        System.out.println(start);

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        System.out.println(LocalDate.ofEpochDay(randomEpochDay)); // random date between the range
    }




    @Test
    public String[] oneYearFutureDt() {


        String DATE_FORMAT = "MM-dd-yyyy";
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);


        // Get current date
        Date currentDate = new Date();
        System.out.println("date : " + dateFormat.format(currentDate));




        // convert date to localdatetime
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


        System.out.println("localDateTime : " + dateFormat8.format(localDateTime));

        // plus one
        localDateTime = localDateTime.plusYears(1).plusMonths(1).plusDays(1);
        //localDateTime = localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);


        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println("\nOutput : " + dateFormat.format(currentDatePlusOneDay));

        String datenow = dateFormat.format(currentDatePlusOneDay);

        System.out.println("\nOutput : " + datenow);

        String dat[] = new String[4];

        dat = datenow.split("/");
        String yyyy = dat[0];
        String mm = dat[1];
        String dd = dat[2];

        System.out.println(yyyy+mm+dd);

        return dat;

    }


    @Test
    public String oneYearsFuture(String yourDate) {

        //String yourDate = "01-01-2002";

        String result[] = new String[4];

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        String orgDate = yourDate;
        String newdate = null;


        try {
            Date date = format.parse(orgDate);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);

            cal.add(Calendar.YEAR, +1);

            Date beforeDate = cal.getTime();

            newdate = format.format(beforeDate);
            System.out.println(newdate);
//            result = newdate.split("-");
//            String mm = result[0];
//            String dd = result[1];
//            String yyyy = result[2];


        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(result);
        return newdate;


    }

}
