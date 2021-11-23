package process.controller.admin;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import process.controller.Main;
import process.controller.error.ErrorInput;
import program.classes.Admin;
import program.classes.Const;
import program.helperClasses.EmployeeTableView;
import program.helperClasses.RejectRequestViewTable;

import java.io.IOException;

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


    @FXML
    private TableView<RejectRequestViewTable> rejectRequestTableView;

    @FXML
    private TableColumn<RejectRequestViewTable, String> userNameColumnRejectRequestTableView;

    @FXML
    private TableColumn<RejectRequestViewTable, String> adminNameColumnRejectRequestTableView;

    @FXML
    private TableColumn<RejectRequestViewTable, String> phoneNumberUserColumnRejectRequestTableView;

    @FXML
    private TableColumn<RejectRequestViewTable, String> reasonColumnRejectRequestTableView;

    @FXML
    private TableColumn<RejectRequestViewTable, String> definitionColumnRejectRequestTableView;

    @FXML
    private TableColumn<RejectRequestViewTable, String> startDateColumnRejectRequestTableView;


    ObservableList<EmployeeTableView> employeeTableViews = FXCollections.observableArrayList();
    ObservableList<RejectRequestViewTable> requestViewTables = FXCollections.observableArrayList();

    @FXML
    void comeBack() {
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");
    }

    @FXML
    void createEmployee(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/admin/createEmployee.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Создание аккаунта");
        stage.setScene(new Scene(root));
        stage.setMaxHeight(600);
        stage.setMaxWidth(600);
        stage.setMinHeight(100);
        stage.setMinWidth(500);
        stage.showAndWait();

    }

    @FXML
    void deleteDetails(ActionEvent event) {
        Main.getMethod().writeLine(Const.DELETE_CURRENT_ADMIN);
        Main.getMethod().writeLine(String.valueOf(Admin.getCurrentId()));
        if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL))
            comeBack();
        else {
            ErrorInput err = new ErrorInput();
            err.showErrorProcess();
        }
    }

    @FXML
    void confirmDeleteEmployee(){
        if (idEmployeeForDeleteLabel.getText().isEmpty()){
            ErrorInput err = new ErrorInput();
            err.show();
        } else {
            Main.getMethod().writeLine(Const.DELETE_EMPLOYEE);
            Main.getMethod().writeLine(idEmployeeForDeleteLabel.getText().trim());
            if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)){
                /*Main main = new Main();
                main.getWindow("/fxml/admin/adminMenu.fxml", "Меню администратора");*/
            } else {
                ErrorInput err = new ErrorInput();
                err.showErrorProcess();
            }
        }
    }
    @FXML
    void showGraphic(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/graphic/graphicAreaChartByAllEmployee.fxml", "График");
    }

    @FXML
    void showDiagram(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/graphic/barChartAllEmployee.fxml", "Диаграмма");
    }

    @FXML
    void initialize() {
        initializeCurrentDetails();

        initializeEmployeeViewTable();
        initializeRejectRequestTableView();

    }
    public void initializeCurrentDetails(){
        Main.getMethod().writeLine(Const.INITIALIZE_CURRENT_DETAILS_ADMIN);
        Main.getMethod().writeLine(String.valueOf(Admin.getCurrentId()));
        Gson gson = new Gson();
        if (Main.getMethod().readLine().equals(Const.FUNCTION_FAILED)){
            ErrorInput err = new ErrorInput();
            err.showErrorProcess();
        } else {
            Admin admin = gson.fromJson(Main.getMethod().readLine(), Admin.class);
            idCurrentLabel.setText(String.valueOf(admin.getId()));
            nameCurrentLabel.setText(admin.getName());
            loginCurrentLabel.setText(admin.getLogin());
            passwordCurrentLabel.setText(admin.getPassword());
        }
    }

    private void showIdEmployeeTableView(EmployeeTableView employeeTableView){
        if (employeeTableView != null){
           // idUserForDeleteLabel.setText(String.valueOf(employeeTableView.getId()));
            idEmployeeForDeleteLabel.setText(String.valueOf(employeeTableView.getId()));
        }else {
            //idUserForDeleteLabel.setText("");
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
                employeeTableViews.add(new EmployeeTableView(employee.getId(), employee.getName(), employee.getPosition(), employee.getMark(), -1));
                idEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, Integer>("id"));
                nameEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, String>("name"));
                positionEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, String>("position"));
                ratingEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, Double>("mark"));
            }
        }
        employeeTableView.setItems(employeeTableViews);
        showIdEmployeeTableView(null);
        //listener tab on row
        employeeTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, employeeTableView1, t1) -> showIdEmployeeTableView(t1)));
    }

    private void initializeRejectRequestTableView(){
        Main.getMethod().writeLine(Const.GET_DATA_FOR_REJECT_REQUEST_TABLE_VIEW);
        Gson gson = new Gson();
        while (true) {
            String strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                RejectRequestViewTable requestViewTable = gson.fromJson(strG, RejectRequestViewTable.class);
                requestViewTables.add(new RejectRequestViewTable(requestViewTable.getNameUser(), requestViewTable.getNameAdmin(), requestViewTable.getPhoneNumber(),
                        requestViewTable.getStartDate(), requestViewTable.getDefinition(), requestViewTable.getReason()));
                userNameColumnRejectRequestTableView.setCellValueFactory(new PropertyValueFactory<RejectRequestViewTable, String>("nameUser"));
                adminNameColumnRejectRequestTableView.setCellValueFactory(new PropertyValueFactory<RejectRequestViewTable, String>("nameAdmin"));
                phoneNumberUserColumnRejectRequestTableView.setCellValueFactory(new PropertyValueFactory<RejectRequestViewTable, String>("phoneNumber"));
                startDateColumnRejectRequestTableView.setCellValueFactory(new PropertyValueFactory<RejectRequestViewTable, String>("startDate"));
                definitionColumnRejectRequestTableView.setCellValueFactory(new PropertyValueFactory<RejectRequestViewTable, String>("definition"));
                reasonColumnRejectRequestTableView.setCellValueFactory(new PropertyValueFactory<RejectRequestViewTable, String>("reason"));
            }
        }
        rejectRequestTableView.setItems(requestViewTables);
    }

}
