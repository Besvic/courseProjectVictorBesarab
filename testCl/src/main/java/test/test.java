package test;

import program.helperClasses.StatisticMark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/*import static server.ServerWork.getServerStream;*/

public class test {
    public static void main(String[] args) throws IOException {
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


       /* System.out.println("ok");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        FileWriter out = null;
        try {
            out = new FileWriter("Акт.txt");
            out.write("Дата начала работы над заказом: \n");
            out.write("Дата начала работы над заказом: \n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
