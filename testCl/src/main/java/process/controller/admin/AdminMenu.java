package process.controller.admin;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import process.controller.Main;
import program.classes.Const;
import program.helperClasses.EmployeeTableView;

public class AdminMenu {

    @FXML
    private Label nameCurrentLabel;

    @FXML
    private Label passwordCurrentLabel;

    @FXML
    private Label idCurrentLabel;

    @FXML
    private Label loginCurrentLabel;

    @FXML
    private Label idUserForDeleteLabel;

    @FXML
    private Label idEmployeeForDeleteLabel;

    @FXML
    private TableView<EmployeeTableView> employeeTableView;

    @FXML
    private TableColumn<EmployeeTableView, Double> ratingEmployeeColumnEmployeeTableView;

    @FXML
    private TableColumn<EmployeeTableView, Integer> idEmployeeColumnEmployeeTableView;

    @FXML
    private TableColumn<EmployeeTableView, String> positionEmployeeColumnEmployeeTableView;

    @FXML
    private TableColumn<EmployeeTableView, String> nameEmployeeColumnEmployeeTableView;

    ObservableList<EmployeeTableView> employeeTableViews = FXCollections.observableArrayList();

    @FXML
    void comeBack(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");
    }

    @FXML
    void changeDetails(ActionEvent event) {

    }

    @FXML
    void deleteDetails(ActionEvent event) {

    }

    @FXML
    void showGraphic(ActionEvent event) {

    }

    @FXML
    void showDiagram(ActionEvent event) {

    }

    @FXML
    void initialize() {
        initializeEmployeeViewTable();
    }

    private void showIdEmployeeTableView(EmployeeTableView employeeTableView){
        if (employeeTableView != null){
            idUserForDeleteLabel.setText(String.valueOf(employeeTableView.getId()));
            idEmployeeForDeleteLabel.setText(String.valueOf(employeeTableView.getId()));
        }else {
            idUserForDeleteLabel.setText("");
            idEmployeeForDeleteLabel.setText("");
        }
    }

    private void initializeEmployeeViewTable(){
        Main.getMethod().writeLine(Const.INITIALIZE_ALL_EMPLOYEE_TABLE_VIEW);
        Gson gson = new Gson();
        while (true){
            String strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                EmployeeTableView employee = gson.fromJson(strG, EmployeeTableView.class);
                employeeTableViews.add(new EmployeeTableView(employee.getId(), employee.getName(), employee.getPosition(), employee.getMark()));
                idEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, Integer>("id"));
                nameEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, String>("name"));
                positionEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, String>("position"));
                ratingEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, Double>("mark"));
                employeeTableView.setItems(employeeTableViews);
                showIdEmployeeTableView(null);
                //listener tab on row
                employeeTableView.getSelectionModel().selectedItemProperty().addListener(
                        ((observableValue, employeeTableView1, t1) -> showIdEmployeeTableView(t1))
                );
            }
        }
    }
}
