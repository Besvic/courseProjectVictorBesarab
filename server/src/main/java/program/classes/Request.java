package program.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    public static int CURRENT_ID = 0;
    protected  String name;
    protected int idUser;
    protected int id;
    protected int idEmployee;
    protected String phoneNumber;
    protected String comment;
    protected String choiceDate;

    public Request(int idUser, int idEmployee, String phoneNumber, String comment, String choiceDate) {
        this.idUser = idUser;
        this.idEmployee = idEmployee;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.choiceDate = choiceDate;
    }

    public Request(int idUser, String phoneNumber, String comment) {
        this.idUser = idUser;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
    }

    public Request() {

    }

 /*   public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getChoiceDate() {
        return choiceDate;
    }

    public void setChoiceDate(String choiceDate) {
        this.choiceDate = choiceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
