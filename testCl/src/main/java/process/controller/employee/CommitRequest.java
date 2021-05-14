package process.controller.employee;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import process.controller.Main;
import process.controller.error.ErrorInput;
import program.classes.Const;
import program.classes.Service;

import static process.controller.Main.getMethod;

public class CommitRequest {

    @FXML
    private TextField commentTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField coastTextField;

    @FXML
    String getNameTextField() {
        return nameTextField.getText().trim();
    }

    @FXML
    String getCommentTextField() {
        return commentTextField.getText().trim();
    }

    @FXML
    String getCityTextField() {
        return cityTextField.getText().trim();
    }

    @FXML
    double getCoastTextField() {
        return Double.valueOf(coastTextField.getText().trim());
    }

    @FXML
    void createOrder(ActionEvent event) {
        if (getCityTextField().isEmpty() || getCoastTextField() == 0 || getCommentTextField().isEmpty() || getNameTextField().isEmpty()){
            ErrorInput errorInput = new ErrorInput();
            errorInput.show();
        }else {
            Service service = new Service(0, getNameTextField(), getCityTextField(), getCommentTextField(), "", getCoastTextField() );
            Gson gson = new Gson();
            getMethod().writeLine(gson.toJson(service));
            if (getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
                coastTextField.getScene().getWindow().hide();
                Main main = new Main();
                main.getWindow("/fxml/employee/mainMenuEmployee.fxml", "Меню сотрудника");
            }
            else {
                ErrorInput err = new ErrorInput();
                err.show();
            }
        }
    }

    @FXML
    void initialize() {

    }
}
