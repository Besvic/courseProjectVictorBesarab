package program.classes;


import lombok.Getter;
import lombok.Setter;
import program.classes.Request;
@Setter
@Getter
public class ViewRequest extends Request {
    private String nameUser;
    public ViewRequest(int id, String nameUser, String phoneNumber, String comment, String date){
        super();
        this.id = id;
        this.nameUser =  nameUser;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.choiceDate = date;
    }
    public ViewRequest(){}

   /* public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }*/
}
