package program.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    protected String name;
    protected int id;
    protected static int CURRENT_ID;
    protected String login;
    protected String password;

    public Admin(String name, int id, String login, String password) {
        this.name = name;
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public static int getCurrentId() {
        return CURRENT_ID;
    }

    public static void setCurrentId(int currentId) {
        CURRENT_ID = currentId;
    }
  /*  public Admin(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    }*/
}
