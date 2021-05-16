package test;

import program.helperClasses.StatisticMark;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/*import static server.ServerWork.getServerStream;*/

public class test {
    public static void main(String[] args) {
       /* String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());



        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date myDate = null;
        Date testDate = null;

        try {
            testDate = defaultDateFormat.parse("14.05.2021");
            myDate = defaultDateFormat.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (myDate.before(testDate)){
            System.out.println("good");
        }*/
        System.out.println("ok");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
