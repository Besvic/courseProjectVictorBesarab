package process.controller;


import animation.ErrorInputEnter;
import com.google.gson.Gson;
import javafx.scene.control.PasswordField;
import process.controller.error.ErrorInput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import program.classes.Admin;
import program.classes.Const;
import program.classes.Employee;
import program.classes.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnterInAccount {
    public final String AUTHORISATION = "authorisation";
    public final String USER_AUTHORISATION = "authorisationUser";
    public final String ADMIN_AUTHORISATION = "authorisationAdmin";
    public final String FUNCTION_COMPLETED_SUCCESSFUL = "function completed successful";
    public final String FUNCTION_FAILED = "function failed";
    public static String userDetailsG = null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Button buttonRegister;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private ChoiceBox<String> choiceLaw;

    ObservableList<String> statusList = FXCollections.observableArrayList("Пользователь", "Сотрудник", "Администратор");
   /* public EnterInAccount(){

    }*/

    @FXML
    void buttonEnterAction(ActionEvent event) {
        if(textFieldLogin.getText().trim() != "" && textFieldPassword.getText().trim() != ""){
            if (choiceLaw.getValue().equals("Администратор")){
                Main.getMethod().writeLine(Const.AUTHORISATION_ADMIN);
                Admin admin = new Admin("", 0, textFieldLogin.getText().trim(), textFieldPassword.getText().trim());
                Gson gson = new Gson();
                Main.getMethod().writeLine(gson.toJson(admin));
                if (Main.getMethod().readLine().equals(Const.FUNCTION_FAILED)){
                    ErrorInput err = new ErrorInput();
                    err.show();
                }else {
                    Admin.setCurrentId(Integer.parseInt(Main.getMethod().readLine()));
                    Main main = new Main();
                    main.getWindow("/fxml/admin/adminMenu.fxml", "Меню администратора");
                }
            }else {
                Main.getMethod().writeLine(AUTHORISATION);
                if (choiceLaw.getValue().trim().equals("Пользователь")) {
                    Main.getMethod().writeLine(USER_AUTHORISATION);
                    User user = new User();
                    user.setLogin(textFieldLogin.getText().trim());
                    user.setPassword(textFieldPassword.getText().trim());
                    Gson gson = new Gson();
                    Main.getMethod().writeLine(gson.toJson(user));

                    userDetailsG = Main.getMethod().readLine();////////////////////
                    if (userDetailsG.equals(FUNCTION_FAILED)) {
                        // ANIMATION TEST
                        ErrorInputEnter loginAnimation = new ErrorInputEnter(textFieldLogin);
                        ErrorInputEnter passwordAnimation = new ErrorInputEnter(textFieldPassword);
                        loginAnimation.playAnimation();
                        passwordAnimation.playAnimation();
                        ErrorInput err = new ErrorInput();
                        err.show();
                    } else {
                        User.CURRENT_ID = Integer.parseInt(Main.getMethod().readLine());
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/fxml/user/MenuForUser.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Main.primaryStage.setScene(new Scene(root));
                        Main.primaryStage.setTitle("Меню пользователя");
                        Main.primaryStage.show();
                    }
                } else {
                    Main.getMethod().writeLine(ADMIN_AUTHORISATION);
                    Employee employee = new Employee();
                    employee.setLogin(textFieldLogin.getText().trim());
                    employee.setPassword(textFieldPassword.getText().trim());
                    Gson gson = new Gson();
                    Main.getMethod().writeLine(gson.toJson(employee));
                    String answer = Main.getMethod().readLine();
                    if (answer.equals(FUNCTION_FAILED)) {
                        // ANIMATION TEST
                       /* ErrorInputEnter loginAnimation = new ErrorInputEnter(textFieldLogin);
                        ErrorInputEnter passwordAnimation = new ErrorInputEnter(textFieldPassword);
                        loginAnimation.playAnimation();
                        passwordAnimation.playAnimation();*/
                        ErrorInput err = new ErrorInput();
                        err.show();
                    } else {
                        Employee.CURRENT_ID = Integer.parseInt(answer);
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/fxml/employee/mainMenuEmployee.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Main.primaryStage.setScene(new Scene(root));
                        Main.primaryStage.setTitle("Меню сотрудника");
                        Main.primaryStage.show();
                    }
                }
            }

        }else {
            ErrorInput err = new ErrorInput();
            err.show();
        }
    }
    @FXML
    void initialize() {

        choiceLaw.setValue("Пользователь");
        choiceLaw.setItems(statusList);
        buttonRegister.setOnAction(actionEvent -> {/*
          buttonRegister.getScene().getWindow().hide();

          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/start/registration.fxml"));
          try {
              loader.load();
          } catch (IOException e) {
              e.printStackTrace();
          }
          Parent root = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.showAndWait();
*/
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/start/registration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.setTitle("Авторизация");
            Main.primaryStage.show();
        });
    }



}

