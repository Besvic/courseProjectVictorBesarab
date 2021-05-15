package process.controller.employee;

import java.io.IOException;

import com.google.gson.Gson;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import process.controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import process.controller.error.ErrorInput;
import program.classes.*;
import program.helperClasses.CurrentOrderViewTable;

public class MainMenuEmployee {
    @FXML
    private Label currentLoginLabel;

    @FXML
    private Label currentPasswordLabel;

    @FXML
    private Label currentNameLabel;

    @FXML
    private Label currentPositionLabel;

    @FXML
    private Label currentPhoneNumberLabel;

    @FXML
    private Label currentIdLabel;

    @FXML
    private Label currentEmailLabel;

    @FXML
    private Label idRequestFromViewRequestLabel;

    @FXML
    private Label idOrderFromCurrentOrderViewTableLabel;

    @FXML
    private ChoiceBox<String> reasonForReject;

    @FXML
    private TableView<ViewRequest> requestTableView;

    @FXML
    private TableColumn<ViewRequest, String> phoneNumberUserColumnRequest;

    @FXML
    private TableColumn<ViewRequest, String> nameUserColumnRequest;

    @FXML
    private TableColumn<ViewRequest, Integer> idRequestColumnRequest;

    @FXML
    private TableColumn<ViewRequest, String> dateColumnRequest;

    @FXML
    private TableColumn<ViewRequest, String> commentColumnRequest;

    @FXML
    private TableView<CurrentOrderViewTable> currentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, String> nameUserColumnCurrentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, Double> costColumnCurrentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, Integer> idOrderCurrentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, String> startDateColumnCurrentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, String> definitionColumnCurrentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, String> nameColumnCurrentOrderViewTable;

    @FXML
    private TableColumn<CurrentOrderViewTable, String> phoneNumberColumnCurrentOrderViewTable;

    @FXML
    private TableView<ActsOfWork> completedViewTable;

    @FXML
    private TableColumn<ActsOfWork, String> startDateColumnCompletedViewTable;

    @FXML
    private TableColumn<ActsOfWork, String> cityColumnCompletedViewTable;

    @FXML
    private TableColumn<ActsOfWork, String> definitionColumnCompletedViewTable;

    @FXML
    private TableColumn<ActsOfWork, String> endDateColumnCompletedViewTable;

    @FXML
    private TableColumn<ActsOfWork, String> nameOrderColumnCompletedViewTable;

    @FXML
    private TableColumn<ActsOfWork, Double> costColumnCompletedViewTable;

    @FXML
    private TableColumn<ActsOfWork, String> emailUserColumnCompletedViewTable;

    ObservableList<ViewRequest> viewRequests = FXCollections.observableArrayList();

    ObservableList<CurrentOrderViewTable> currentOrderViewTables = FXCollections.observableArrayList();

    ObservableList<ActsOfWork> completedOrder = FXCollections.observableArrayList();

    //commit request in account employee
    @FXML
    void commitRequest(ActionEvent event) {
        if (!idRequestFromViewRequestLabel.getText().isEmpty()) {
            Main.getMethod().writeLine(Const.COMMIT_REQUEST_IN_EMPLOYEE_ACCOUNT);
            Main.getMethod().writeLine(idRequestFromViewRequestLabel.getText());
            // sent id for delete request
            // Service.setCurrentId(Integer.parseInt(idRequestFromViewRequestLabel.getText()));
            Main m = new Main();
            m.createWindow("/fxml/employee/commitRequest.fxml", 400, 400);
            //Main.getMethod().readLine();
        }
        else {
            ErrorInput err = new ErrorInput();
            err.show();
        }

    }

    public String getReasonForReject(){
        return reasonForReject.getValue().trim();
    }

    @FXML
    void rejectRequest(ActionEvent event) {
        if (idRequestFromViewRequestLabel.getText().trim().isEmpty()){
            ErrorInput err = new ErrorInput();
            err.show();
        }
        else {
            Main.getMethod().writeLine(Const.REJECT_REQUEST);
            Main.getMethod().writeLine(idRequestFromViewRequestLabel.getText().trim());
            Main.getMethod().writeLine(getReasonForReject());
            if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
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
    void graphicMenu(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/graphic/graphicEmployeeCostCity.fxml", "График");
    }

    @FXML
    void barChartIdEmployeeIncomeInMonth(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/graphic/BarChartCurrentEmployeeIncom.fxml", "Диаграмма");
    }

    @FXML
    void comeBack() {
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml", "Вход/Авторизация");
    }

    @FXML
    void deleteCurrentAccount(){
        Main.getMethod().writeLine(Const.DELETE_CURRENT_EMPLOYEE);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        comeBack();
    }

    @FXML
    void changeCurrentDetails(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/employee/changeEmployeeDetails.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Изменение данных");
        stage.setScene(new Scene(root));
        stage.setMaxHeight(350);
        stage.setMaxWidth(350);
        stage.setMinHeight(350);
        stage.setMinWidth(350);
        stage.showAndWait();
    }

    @FXML
    void confirmCompleteOrder(ActionEvent event) {
        if (idOrderFromCurrentOrderViewTableLabel.getText().trim().equals("")){
            ErrorInput err = new ErrorInput();
            err.show();
        }else {
            Main.getMethod().writeLine(Const.ADD_ACTS_OF_WORK);
            Main.getMethod().writeLine(idOrderFromCurrentOrderViewTableLabel.getText().trim());
            if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
                Main main = new Main();
                main.getWindow("/fxml/employee/mainMenuEmployee.fxml", "Меню сотрудника");
            }else{
                ErrorInput err = new ErrorInput();
                err.show();
            }
        }
    }

    @FXML
    void initialize() {
        // initialize profile
        initializeCurrentDetailsInProfile();

        //initialize table view
        initializeObjectViewRequest();
        initializeCurrentOrderViewTable();
        initializeCompletedOrder();

        // initialize choice box for reject order
        reasonForReject.setValue("Не могу связаться с клиентом");
        ObservableList<String> rulesReject = FXCollections.observableArrayList("Не мой профиль", "Не могу связаться с клиентом", "Не договорились с клиентом", "Другое");
        reasonForReject.setItems(rulesReject);
    }

    public void initializeCurrentDetailsInProfile(){
        Main.getMethod().writeLine(Const.GET_EMPLOYEE_CURRENT_DETAILS);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        Gson gson = new Gson();
        Employee employee = gson.fromJson(Main.getMethod().readLine(), Employee.class);
        currentNameLabel.setText(employee.getName());
        currentEmailLabel.setText(employee.getEmail());
        currentIdLabel.setText(String.valueOf(employee.getId()));
        currentLoginLabel.setText(employee.getLogin());
        currentPasswordLabel.setText(employee.getPassword());
        currentPositionLabel.setText(employee.getPosition());
        currentPhoneNumberLabel.setText(employee.getPhoneNumber());
    }

    public void showIdRequestFromViewRequest(ViewRequest request ){
        if (request != null)
            idRequestFromViewRequestLabel.setText(String.valueOf(request.getId()));
        else
            idRequestFromViewRequestLabel.setText("");
    }

    public void initializeObjectViewRequest() {
        Main.getMethod().writeLine(Const.INITIALIZE_VIEW_REQUEST);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        Gson gson = new Gson();
        String strG;
        while (true){
            strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                ViewRequest v = gson.fromJson(strG, ViewRequest.class);
                viewRequests.add(new ViewRequest(v.getId(), v.getNameUser()
                        , v.getPhoneNumber(), v.getComment()
                        , v.getChoiceDate()));
            }
        }
        idRequestColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, Integer>("id"));
        nameUserColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("nameUser"));
        phoneNumberUserColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("phoneNumber"));
        commentColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("comment"));
        dateColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("choiceDate"));
        requestTableView.setItems(viewRequests);
        // listener request view for commit or reject
        showIdRequestFromViewRequest(null);
        requestTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, viewRequest, newValue) ->showIdRequestFromViewRequest(newValue) )
        );
    }

    public void showIdOrderFromCurrentOrderViewTable(CurrentOrderViewTable currentOrderViewTable){
        if (currentOrderViewTable != null)
            idOrderFromCurrentOrderViewTableLabel.setText(String.valueOf(currentOrderViewTable.getId()));
        else
            idOrderFromCurrentOrderViewTableLabel.setText("");
    }

    public void initializeCurrentOrderViewTable(){
        Main.getMethod().writeLine(Const.INITIALIZE_CURRENT_ORDER_VIEW_TABLE);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        Gson gson = new Gson();
        String strG;
        while (true){
            strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                CurrentOrderViewTable localOrder = gson.fromJson(strG, CurrentOrderViewTable.class);
                currentOrderViewTables.add(new CurrentOrderViewTable(localOrder.getId(), localOrder.getNameOrder(), localOrder.getNameUser(),
                        localOrder.getEmailUser(), localOrder.getDefinition(), localOrder.getCost(), localOrder.getStartDate()));
            }
        }
        idOrderCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, Integer>("id"));
        nameColumnCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, String>("nameOrder"));
        nameUserColumnCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, String>("nameUser"));
        phoneNumberColumnCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, String>("emailUser"));
        definitionColumnCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, String>("definition"));
        costColumnCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, Double>("cost"));
        startDateColumnCurrentOrderViewTable.setCellValueFactory(new PropertyValueFactory<CurrentOrderViewTable, String>("startDate"));
        currentOrderViewTable.setItems(currentOrderViewTables);
        //listener tab on row
        showIdOrderFromCurrentOrderViewTable(null);
        currentOrderViewTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, completeOrder, newValue) -> showIdOrderFromCurrentOrderViewTable(newValue)));
    }

    public void initializeCompletedOrder(){
        Main.getMethod().writeLine(Const.INITIALIZE_COMPLETED_VIEW_TABLE);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        Gson gson = new Gson();
        String strG;
        while (true){
            strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                ActsOfWork acts = gson.fromJson(strG, ActsOfWork.class);
                completedOrder.add(new ActsOfWork(0, acts.getEmailUser(), acts.getEndDate(), acts.getStartDate(),
                        acts.getCost(), acts.getEmailEmployee(), acts.getCity(), acts.getDefinition(), acts.getName(),
                        acts.getIdUser(), acts.getIdEmployee()));
            }
            emailUserColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, String>("emailUser"));
            endDateColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, String>("endDate"));
            startDateColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, String>("startDate"));
            costColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, Double>("cost"));
            cityColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, String>("city"));
            definitionColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, String>("definition"));
            nameOrderColumnCompletedViewTable.setCellValueFactory(new PropertyValueFactory<ActsOfWork, String>("name"));
            completedViewTable.setItems(completedOrder);
        }
    }
}
