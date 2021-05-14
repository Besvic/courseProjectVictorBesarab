package process.controller.employee;

import com.google.gson.Gson;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import process.controller.error.ErrorInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import program.classes.Const;
import program.classes.Employee;

import java.io.IOException;

import static process.controller.Main.getMethod;

public class ChangeEmployeeDetails {

    @FXML
    private Button closeButton;

    @FXML
    private Label processLabel;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField loginField;

    @FXML
    String getLogin() {
        return loginField.getText().trim();
    }

    @FXML
    String getPassword() {
        return passwordField.getText().trim();
    }

    @FXML
    String getName() {
        return nameField.getText().trim();
    }

    @FXML
    String getEmail() {
        return emailField.getText().trim();
    }

    @FXML
    String getPhoneNumber() {
        return phoneNumberField.getText().trim();
    }

    @FXML
    void confirmChanges(ActionEvent event) {
        if(!getName().isEmpty() && !getEmail().isEmpty() && !getPhoneNumber().isEmpty() && !getPassword().isEmpty() && !getLogin().isEmpty()){
            Employee employee = new Employee(Employee.CURRENT_ID, getName(), getEmail(), getPhoneNumber(), getLogin(), getPassword(), null);
            Gson gson = new Gson();
            String stringG = gson.toJson(employee);
            getMethod().writeLine(Const.UPDATE_EMPLOYEE_DETAILS);
            getMethod().writeLine(stringG);
            //wait answer about function
            if (getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)){
                processLabel.setText("Выйдите из аккаунта для сохранения данных.");
                close();
                getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");

            }
            else{
                processLabel.setText("Логин уже используется.");
                ErrorInput err = new ErrorInput();
                err.show();
            }
        }else {
            processLabel.setText("Заполните все поля.");
            ErrorInput err = new ErrorInput();
            err.show();
        }
    }

    @FXML
    void close() {
        closeButton.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        getMethod().writeLine(Const.GET_EMPLOYEE_CURRENT_DETAILS);
        getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        Gson gson = new Gson();
        Employee employee = gson.fromJson(getMethod().readLine(), Employee.class);
        nameField.setText(employee.getName());
        loginField.setText(employee.getLogin());
        emailField.setText(employee.getEmail());
        passwordField.setText(employee.getPassword());
        phoneNumberField.setText(employee.getPhoneNumber());
    }


    public void getWindow(String FXMLString, String title){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(FXMLString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        process.controller.Main.primaryStage.setScene(new Scene(root));
        process.controller.Main.primaryStage.setTitle(title);
        process.controller.Main.primaryStage.show();
    }
}
