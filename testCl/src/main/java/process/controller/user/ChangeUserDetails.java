package process.controller.user;

import com.google.gson.Gson;
import process.controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import process.controller.Registration;
import process.controller.error.ErrorInput;
import program.classes.Const;
import program.classes.User;


public class ChangeUserDetails {

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label processLabel;

    @FXML
    private Button closeButton;

    @FXML
    void confirmChanges(ActionEvent event) {
        if (Registration.checkName(nameTextField.getText().trim()) &&
                !loginTextField.getText().trim().isEmpty() && !passwordTextField.getText().trim().isEmpty() &&
                Registration.validationEmail( emailTextField.getText().trim())) {
            User user = new User(nameTextField.getText().trim(), emailTextField.getText().trim(), loginTextField.getText().trim()
                    , passwordTextField.getText().trim(), User.CURRENT_ID);
            Gson gson = new Gson();
            String stringG = gson.toJson(user);
            Main.getMethod().writeLine(Const.UPDATE_USER_DETAILS);
            Main.getMethod().writeLine(stringG);
            String answer;

            if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
                answer = "Операция выполнена успешно.";
                close();
                Main main = new Main();
                main.getWindow("/fxml/start/mainWindow.fxml", "Вход/Авторизация");

            } else {
                answer = "Операция не завршена.";
            }
            processLabel.setText(answer);
        } else {
            ErrorInput  err = new ErrorInput();
            err.show();
        }
    }

    @FXML
    void close() {
        closeButton.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        Main.getMethod().writeLine(Const.GET_USER_CURRENT_DETAILS);
        Main.getMethod().writeLine(String.valueOf(User.CURRENT_ID));
        Gson gson = new Gson();
        User user = gson.fromJson(Main.getMethod().readLine(), User.class);
        nameTextField.setText(user.getName());
        loginTextField.setText(user.getLogin());
        emailTextField.setText(user.getEmail());
        passwordTextField.setText(user.getPassword());
    }
}
