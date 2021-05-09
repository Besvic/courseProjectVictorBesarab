package program.classes;

public class Service {
    protected static int CURRENT_ID;
    protected int id;
    protected String name;
    protected String city;
    protected String definition;
    protected String dateStart;
    protected double cost;

    public Service(){};

    public Service(int id, String name, String city, String definition, String dateStart, double cost){
        this.id = id;
        this.name = name;
        this.city = city;
        this.definition = definition;
        this.cost = cost;
    }

    public static int getCurrentId() {
        return CURRENT_ID;
    }

    public static void setCurrentId(int currentId) {
        CURRENT_ID = currentId;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
