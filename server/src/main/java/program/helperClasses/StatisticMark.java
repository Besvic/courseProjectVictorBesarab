package program.helperClasses;

import java.util.Comparator;

public class StatisticMark {
    protected double mark;
    protected String name;

    public StatisticMark(String name, double mark) {
        this.mark = mark;
        this.name = name;
    }


    public static Comparator<StatisticMark> COMPARE_BY_MARK = new Comparator<StatisticMark>() {
        public int compare(StatisticMark one, StatisticMark other) {
            if (one.mark < other.mark)
                return 0;
            else
                return -1;

        }
    };

    @Override
    public String toString() {
        return "StatisticMark{" +
                "mark=" + mark +
                ", name='" + name + '\'' +
                '}';
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
