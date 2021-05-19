package process.controller;

import com.google.common.base.CharMatcher;
import com.google.gson.Gson;
import javafx.scene.control.Alert;
import process.controller.error.ErrorInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import program.classes.Const;
import program.classes.User;

import java.net.URL;
import java.util.ResourceBundle;




public class Registration {
    public final String FUNCTION_COMPLETED_SUCCESSFUL = "function completed successful";
    public final String FUNCTION_FAILED = "function failed";


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldEmail;

    @FXML
    private Button buttonConfirmRegisration;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldLogin;



    @FXML
    public String getName() { return fieldName.getText().trim(); }

    @FXML
    public String getPassword(/*ActionEvent event*/) { return fieldPassword.getText().trim(); }

    @FXML
    public String getEmail(/*ActionEvent event*/) { return fieldEmail.getText().trim(); }

    @FXML
    public String getLogin(/*ActionEvent event*/) { return fieldLogin.getText().trim(); }

    @FXML
    void authoriseUser(ActionEvent event) {
//        Methods methods = new Methods();
        /*Main.getMethod().writeLine("12");

        String mess = Main.getMethod().readLine();
        System.out.println(mess);
        Main.methods.writeLine("12");*/
       /* try {
            write.write("12");
            write.newLine();
            write.flush();
            System.out.println(read.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }*/


       /* sout.println("12");
        try {
            System.out.println(sin.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        if (getName() != "" && getEmail() != "" && getLogin() != "" && getPassword() != "" && checkName(getName()) && validationEmail(getEmail())) {
            Main.getMethod().writeLine("registrationUser");
            User user = new User(getName(),getEmail(), getLogin(), getPassword());
            String codUser = new Gson().toJson(user);
            Main.getMethod().writeLine(codUser);//sent input data for registration user
            if ( Main.getMethod().readLine().equals(FUNCTION_FAILED)){
                new ErrorInput().show();
            }
            else {
                System.out.println(FUNCTION_COMPLETED_SUCCESSFUL);
                exitInMainWindow();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");
            alert.showAndWait();
        }



    }

    public static boolean checkName(String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ')
                return false;
        }
        return true;
    }

    public static boolean validationNumber(String str){
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i)) || str.charAt(i) != '.' || str.charAt(i) != ',' )
                return false;
        }
        return true;
    }

    public static boolean validationEmail(String str){
        if (CharMatcher.WHITESPACE.matchesAnyOf(str))
            return false;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == '@')
                return true;
        return true;
    }




    @FXML
    void exitInMainWindow() {
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");
    }

    @FXML
    void initialize() {
        /*buttonConfirmRegisration.setOnAction(actionEvent -> {
            Main.methods.writeLine("12");
        });*/

    }
}

