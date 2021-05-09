package process.controller.employee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import dbconnection.DBConnect;
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
import process.controller.error.ErrorInputData;
import program.classes.*;

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

 /*   @FXML
    private MenuButton reasonForReject;*/

    @FXML
    private ChoiceBox<String> reasonForReject;

    @FXML
    private Button rejectRequest;

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

    ObservableList<ViewRequest> viewRequests = FXCollections.observableArrayList();

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
            ErrorInputData err = new ErrorInputData();
            err.show();
        }

    }

    public String getReasonForReject(){
        return reasonForReject.getValue().trim();
    }
    @FXML
    void getRejectRequest(ActionEvent event) {
        if (idRequestFromViewRequestLabel.getText().trim().isEmpty()){
            ErrorInputData err = new ErrorInputData();
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
                ErrorInputData err = new ErrorInputData();
                err.show();
            }
        }
    }

    /*@FXML
    String getReasonForReject() {
        if (reasonForReject.getText().trim().equals("Указать причину")) {
            return "";
        }
        return reasonForReject.getText().trim();
    }*/


    @FXML
    void graphicMenu(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/graphic/graphicMenu.fxml", "Статистика");
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
    void initialize() {
        // initialize current details in profile

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

        //initialize request view

        initializeObjectViewRequest();
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

        // initialize choice box for reject order
        reasonForReject.setValue("Не могу связаться с клиентом");
        ObservableList<String> rulesReject = FXCollections.observableArrayList("Не мой профиль", "Не могу связаться с клиентом", "Не договорились с клиентом", "Другое");
        reasonForReject.setItems(rulesReject);

    }

    public void showIdRequestFromViewRequest(ViewRequest request){
        if (request != null)
            idRequestFromViewRequestLabel.setText(String.valueOf(request.getId()));
        else
            idRequestFromViewRequestLabel.setText("");
    }

    public void initializeObjectViewRequest() {
       /* Gson gson = new Gson();
        Main.getMethod().writeLine(Const.GET_DATA_FOR_INITIALISE_VIEW_REQUEST);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        String str = Main.getMethod().readLine();
        *//*viewRequests = gson.fromJson(Main.getMethod().readLine(),  ViewRequest.class);*//*
        ResultSet result = gson.fromJson(str, ResultSet.class);
        try{
            while (result.next()){
                viewRequests.add(new ViewRequest(result.getInt("id"), result.getString("name")
                        , result.getString("phoneNumber"), result.getString("comment")
                        , result.getString("dateForMeeting")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        DBConnect db = new DBConnect();
        ResultSet result = db.getDataForViewRequest(Employee.CURRENT_ID);
        try{
            while (result.next()){
                viewRequests.add(new ViewRequest(result.getInt("id"), result.getString("name")
                        , result.getString("phoneNumber"), result.getString("comment")
                        , result.getString("dateForMeeting")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
