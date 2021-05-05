package process.controller;

import com.google.gson.Gson;
import process.controller.error.ErrorInputData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        if (getName() != "" && getEmail() != "" && getLogin() != "" && getPassword() != "") {
            Main.getMethod().writeLine("registrationUser");
            User user = new User(getName(),getEmail(), getLogin(), getPassword());
            Gson gson = new Gson();
            String codUser = gson.toJson(user);
            Main.getMethod().writeLine(codUser);//sent input data for registration user
            if ( Main.getMethod().readLine().equals(FUNCTION_FAILED)){
                /*Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/error/errorInputData.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.setTitle("Ошибка ввода двнных.");
                Main.primaryStage.show();*/
                //exitInMainWindow();
                /*Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/error/errorInputData.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.setTitle("Вход/Авторизация");
                Main.primaryStage.show();*/
                ErrorInputData err = new ErrorInputData();
                err.show();

            }
            else {
                System.out.println(FUNCTION_COMPLETED_SUCCESSFUL);
            }
        }else {
            ErrorInputData err = new ErrorInputData();
            err.show();

        }



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

