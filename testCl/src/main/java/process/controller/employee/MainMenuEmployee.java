package process.controller.employee;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;



import javafx.scene.control.cell.PropertyValueFactory;
import process.controller.Main;
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
import javafx.stage.Stage;
import program.classes.Const;
import program.classes.Employee;

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
        nameUserColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("name"));
        phoneNumberUserColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("phoneNumber"));
        commentColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("comment"));
        dateColumnRequest.setCellValueFactory(new PropertyValueFactory<ViewRequest, String>("date"));
        requestTableView.setItems(viewRequests);
    }


    public void initializeObjectViewRequest() {
        Gson gson = new Gson();
        Main.getMethod().writeLine(Const.GET_DATA_FOR_INITIALISE_VIEW_REQUEST);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        viewRequests = gson.fromJson(Main.getMethod().readLine(), (Type) ViewRequest.class);
    }


}
