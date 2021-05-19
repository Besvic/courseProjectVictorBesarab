package process.controller.employee;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import process.controller.Main;
import program.classes.ActsOfWork;
import program.classes.Const;

public class Report {
    @FXML
    private Label emailUserLabel;
    @FXML
    private Label emailEmployeeLabel;
    @FXML
    private Label costLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label nameEmployeeLabel;
    @FXML
    private Label nameUserLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label definitionLabel;

    @FXML
    void close(){
        nameEmployeeLabel.getScene().getWindow().hide();
        new Main().getWindow("/fxml/employee/mainMenuEmployee.fxml", "Меню сотрудника");
    }

    @FXML
    void initialize() {
        initializeDetails();
    }

    private void initializeDetails(){
        String strG = Main.getMethod().readLine();
        if (strG.equals(Const.FUNCTION_FAILED))
            close();
        else {
            ActsOfWork acts = new Gson().fromJson(strG, ActsOfWork.class);
            emailEmployeeLabel.setText(acts.getEmailEmployee());
            nameEmployeeLabel.setText(acts.getNameEmployee());
            nameLabel.setText(acts.getName());
            nameUserLabel.setText(acts.getNameUser());
            emailUserLabel.setText(acts.getEmailUser());
            definitionLabel.setText(acts.getDefinition());
            costLabel.setText(String.valueOf(acts.getCost()));
            cityLabel.setText(acts.getCity());
            endDateLabel.setText(acts.getEndDate());
            startDateLabel.setText(acts.getStartDate());
        }
    }
}
