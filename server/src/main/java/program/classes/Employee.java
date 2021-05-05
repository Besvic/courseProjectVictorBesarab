package program.classes;

public class Employee extends User {
    protected String position;
    protected String phoneNumber;

    public Employee(){
        id = 0;
        name = null;
        email = null;
        phoneNumber = null;
        login = null;
        password = null;
        position = null;
    }
    public Employee(int id, String name, String email, String phoneNumber, String login
    , String password, String position){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
