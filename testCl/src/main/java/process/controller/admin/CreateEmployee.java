package process.controller.admin;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import process.controller.Main;
import process.controller.error.ErrorInput;
import program.classes.Const;
import program.classes.Employee;

public class CreateEmployee {

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField positionField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField loginField;

    @FXML
    private Button closeButton;

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
    String getPosition() {
        return positionField.getText().trim();
    }

    @FXML
    void confirmChanges(ActionEvent event) {
        if (getEmail().isEmpty() || getLogin().isEmpty() || getPassword().isEmpty() || getPhoneNumber().isEmpty() ||
                getName().isEmpty() || getPosition().isEmpty()){
            ErrorInput err = new ErrorInput();
            err.show();
        }else {
            Main.getMethod().writeLine(Const.CREATE_EMPLOYEE);
            Employee employee = new Employee(0, getName(), getEmail(), getPhoneNumber(), getLogin(), getPassword(), getPosition());
            Gson gson = new Gson();
            Main.getMethod().writeLine(gson.toJson(employee));
            if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
                close();
                ErrorInput err = new ErrorInput();
                err.showCompleteSuccessful();
            } else {
                ErrorInput err = new ErrorInput();
                err.showErrorProcess();
            }
        }
    }

    @FXML
    void close() {
        closeButton.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {

    }



    /*private void initializeField(){
        Main.getMethod().writeLine(Const.INITIALIZE_TEXT_FIELD_FOR_CREATE_DETAILS);
        Main.getMethod().writeLine(String.valueOf(Admin.getCurrentId()));
        if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)){
            Gson gson = new Gson();
            Admin admin = gson.fromJson(Main.getMethod().readLine(), Admin.class);
            passwordTextField.setText(admin.getPassword());
            nameTextField.setText((admin.getName()));
            loginTextField.setText(admin.getLogin());
        }
        else {
            ErrorInput err = new ErrorInput();
            err.showErrorProcess();
        }
    }*/


}

