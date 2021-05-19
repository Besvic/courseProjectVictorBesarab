package program.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActsOfWork {
    protected int id;
    protected String emailUser;
    protected String endDate;
    protected String startDate;
    protected double cost;
    protected String emailEmployee;
    protected String city;
    protected String definition;
    protected String name;
    protected int idUser;
    protected int idEmployee;
    protected String nameUser;
    protected String nameEmployee;


    public ActsOfWork() {};

    public ActsOfWork(int id, String emailUser, String endDate, String startDate, double cost,
                      String emailEmployee, String city, String definition, String name, int idUser, int idEmployee,
                      String nameUser, String nameEmployee) {
        this.id = id;
        this.emailUser = emailUser;
        this.endDate = endDate;
        this.startDate = startDate;
        this.cost = cost;
        this.emailEmployee = emailEmployee;
        this.city = city;
        this.definition = definition;
        this.name = name;
        this.idUser = idUser;
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.nameUser = nameUser;
    }


    /*public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }*/
}
