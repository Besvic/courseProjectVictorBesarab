package program.helperClasses;

public class CurrentOrderViewTable {
    protected int id;
    protected String nameOrder;
    protected String nameUser;
    protected String emailUser;
    protected String definition;
    protected double cost;
    protected String startDate;

    public CurrentOrderViewTable(){};

    public CurrentOrderViewTable(int id, String nameOrder, String nameUser, String emailUser, String definition, double cost, String startDate){
        this.id = id;
        this.nameOrder = nameOrder;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.definition = definition;
        this.cost = cost;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String numberPhoneUser) {
        this.emailUser = numberPhoneUser;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
