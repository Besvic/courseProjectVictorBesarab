package program.classes;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;

@Data
public class User {
    public static int CURRENT_ID = 0;

    protected String name;
    protected String email;
    protected String login;
    protected String password;
    protected int id;

    public User(){
        this.name = null;
        this.email = null;
        this.login = null;
        this.password = null;
    }
    public User(String name, String email, String login, String password){
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }
    public User(String name, String email, String login, String password, int id){
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

