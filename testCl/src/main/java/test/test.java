package test;

import program.helperClasses.StatisticMark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static server.ServerWork.getServerStream;

public class test {
    public static void main(String[] args) {
        ArrayList<StatisticMark> statistic = new ArrayList<>();
        statistic.add(new StatisticMark("serviceSpeed", 1.1));
        statistic.add(new StatisticMark("serviceQuality", 1.2));
        statistic.add(new StatisticMark("politeness", 1.3));
        for (var i: statistic) {
            System.out.println(i.getMark());
        }
        Collections.sort(statistic, new Comparator<StatisticMark>() {
            @Override
            public int compare(StatisticMark o1, StatisticMark o2) {
                if (o1.getMark() < o2.getMark())
                    return 0;
                else
                    return -1;
            }
        });
        for (var i: statistic) {
            System.out.println(i.getMark());
        }

    }
}
