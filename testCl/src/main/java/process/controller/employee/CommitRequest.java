package process.controller.employee;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import process.controller.Main;
import process.controller.Registration;
import process.controller.error.ErrorInput;
import program.classes.Const;
import program.classes.Employee;
import program.classes.Request;
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
    private Label costLabel;

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
        return Double.valueOf(costLabel.getText().trim());
    }

    @FXML
    void createOrder(ActionEvent event) {
        if (getCityTextField().isEmpty() || getCoastTextField() == 0 || getCommentTextField().isEmpty() || getNameTextField().isEmpty() ||
                !Registration.checkName(getCityTextField()) || !Registration.checkName(getNameTextField())){
            ErrorInput errorInput = new ErrorInput();
            errorInput.show();
        }else {
            Main.getMethod().writeLine(Const.COMMIT_REQUEST_IN_EMPLOYEE_ACCOUNT);
            Main.getMethod().writeLine(String.valueOf(Request.CURRENT_ID));
            Service service = new Service(0, getNameTextField(), getCityTextField(), getCommentTextField(), "", getCoastTextField() );
            Gson gson = new Gson();
            getMethod().writeLine(gson.toJson(service));
            if (getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
                nameTextField.getScene().getWindow().hide();
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
        initializeCostLabel();
    }

    private void initializeCostLabel(){
        Main.getMethod().writeLine(Const.INITIALIZE_COST_LABEL_FOR_CREATE_ORDER);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL))
            costLabel.setText(Main.getMethod().readLine());
        else {
            ErrorInput err = new ErrorInput();
            err.showErrorProcess();
            nameTextField.getScene().getWindow().hide();
            Main main = new Main();
            main.getWindow("/fxml/employee/mainMenuEmployee.fxml", "Меню сотрудника");

        }
    }
}
