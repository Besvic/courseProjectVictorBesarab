package program.helperClasses;

public class EmployeeTableView {
    protected int id;
    protected String name;
    protected String position;
    protected double mark;

    public EmployeeTableView(int id, String name, String position, double mark) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.mark = mark;
    }

    public EmployeeTableView(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
