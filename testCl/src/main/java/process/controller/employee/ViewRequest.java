package process.controller.employee;


import program.classes.Request;

public class ViewRequest extends Request {
    private String nameUser;
    public ViewRequest(){

    }
    public ViewRequest(int id, String nameUser, String phoneNumber, String comment, String date){
        this.idUser = id;
        this.nameUser =  nameUser;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.choiceDate = date;
    }



}
