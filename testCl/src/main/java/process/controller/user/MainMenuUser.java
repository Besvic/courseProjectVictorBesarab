package process.controller.user;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import process.controller.EnterInAccount;
import process.controller.Main;
import process.controller.error.ErrorInputData;
import dbconnection.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import program.classes.Const;
import program.classes.Order;
import program.classes.User;

public class MainMenuUser {

    @FXML // fx:id="loginLabel"
    private Label loginLabel; // Value injected by FXMLLoader

    @FXML // fx:id="passwordLabel"
    private Label passwordLabel; // Value injected by FXMLLoader

    @FXML // fx:id="actionRequest"
    private Label actionRequest; // Value injected by FXMLLoader

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="emailLabel"
    private Label emailLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="answerOnRequest"
    private Label answerOnRequest; // Value injected by FXMLLoader

    @FXML // fx:id="confirmReceiptButton"
    private Button confirmReceiptButton; // Value injected by FXMLLoader

    @FXML // fx:id="sendRequestButton"
    private Button sendRequestButton; // Value injected by FXMLLoader

    @FXML
    private Button changeDetailsButton;



    @FXML // fx:id="cityField"
    private TextField idEmployeeForChoiceField; // Value injected by FXMLLoader

    @FXML // fx:id="commentForRequestField"
    private TextField commentForRequestField; // Value injected by FXMLLoader

    @FXML // fx:id="telephoneNumberField"
    private TextField telephoneNumberField; // Value injected by FXMLLoader

    @FXML // fx:id="commentForReceiptText"
    private TextField commentForReceiptText; // Value injected by FXMLLoader

    @FXML // fx:id="dateReceiptText"
    private TextField dateReceiptText; // Value injected by FXMLLoader

    @FXML // fx:id="idEmployeeText"
    private TextField idEmployeeText; // Value injected by FXMLLoader

    @FXML // fx:id="timeReceiptText"
    private TextField timeReceiptText; // Value injected by FXMLLoader

    @FXML // fx:id="idEmployeeForStatisticField"
    private TextField idEmployeeForStatisticField; // Value injected by FXMLLoader

    @FXML // fx:id="markForStatisticField"
    private TextField markForStatisticField; // Value injected by FXMLLoader

    @FXML // fx:id="orderViewTable"
    private TableView<OrderTable> orderViewTable; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumberView"
    private TableColumn<OrderTable, String> phoneNumberView; // Value injected by FXMLLoader

    @FXML // fx:id="actionView"
    private TableColumn<OrderTable, String> actionView; // Value injected by FXMLLoader

    @FXML // fx:id="startDateView"
    private TableColumn<OrderTable, String> startDateView; // Value injected by FXMLLoader

    @FXML // fx:id="emailEmployeeView"
    private TableColumn<OrderTable, String> emailEmployeeView; // Value injected by FXMLLoader

    @FXML // fx:id="nameOrderView"
    private TableColumn<OrderTable, String> nameOrderView; // Value injected by FXMLLoader

    @FXML // fx:id="idOrderView"
    private TableColumn<OrderTable, Integer> idOrderView; // Value injected by FXMLLoader

    @FXML // fx:id="nameEmployeeView"
    private TableColumn<OrderTable, String> nameEmployeeView; // Value injected by FXMLLoader

    ObservableList<OrderTable> orderTable = FXCollections.observableArrayList();

    @FXML
    void deleteDetails(ActionEvent event) {
        Main.getMethod().writeLine(Const.DELETE_CURRENT_USER);
        Main.getMethod().writeLine(String.valueOf(User.CURRENT_ID));
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");
    }


    @FXML
    void sendStatistic(ActionEvent event) {

    }
    @FXML
    void confirmReceipt(ActionEvent event) {
        //  if(idEmployeeText.getText().trim() != "" && )

    }


    public class OrderTable extends Order{
        private String phone;
        private String email;
        private String nameEmployee;
        private String name;
        OrderTable(String phone, String email, String nameEmployee, int id, String action
                , String date, String name){
            this.setId(id);
            this.setAction(action);
            this.setNameEmployee(nameEmployee);
            this.setStartDate(date);
            this.setEmail(email);
            this.setPhone(phone);
            this.setName(name);
        }

        OrderTable(){

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNameEmployee() {
            return nameEmployee;
        }

        public void setNameEmployee(String nameEmployee) {
            this.nameEmployee = nameEmployee;
        }
    }


    @FXML
    void comeBack(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");
    }

    @FXML
    void changeDetails(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/user/changeUserDetails.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Изменение данных");
        stage.setScene(new Scene(root));
        stage.setMaxHeight(300);
        stage.setMaxWidth(400);
        stage.setMinHeight(300);
        stage.setMinWidth(400);
        stage.showAndWait();

        /*Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");*/
    }



    @FXML
    void sendRequest(ActionEvent event) {
        String answer = null;
        try {
            if (!idEmployeeForChoiceField.getText().trim().isEmpty())
                Integer.parseInt(idEmployeeForChoiceField.getText().trim());
            if (!telephoneNumberField.getText().isEmpty() && !commentForRequestField.getText().trim().isEmpty()) {
                Main.getMethod().writeLine(Const.SEND_REQUEST_fROM_USER_MENU);
                Main.getMethod().writeLine(String.valueOf(User.CURRENT_ID));
                Main.getMethod().writeLine(idEmployeeForChoiceField.getText().trim());
                Main.getMethod().writeLine(telephoneNumberField.getText().trim());
                Main.getMethod().writeLine(commentForRequestField.getText().trim());
                if (Main.getMethod().readLine().equals(Const.FUNCTION_FAILED)) {
                    answer = "Предыдущая заявка еще не рассмотрена.";
                }
                else {
                    answer = "Заявка сформирована.";
                }
            }
            else{
                answer = "Заполните все поля со *.";
                ErrorInputData err = new ErrorInputData();
                err.show();
            }
        } catch (NumberFormatException e) {
            ErrorInputData err = new ErrorInputData();
            err.show();
        }
        answerOnRequest.setText(answer);
    }

    public void showUserDetails(User user, String request){
        nameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());
        actionRequest.setText(request);
        loginLabel.setText(user.getLogin());
        passwordLabel.setText(user.getPassword());
        idLabel.setText(String.valueOf(user.getId()));

    }

    @FXML
    void initialize() {
        Gson gson = new Gson();
        User user = gson.fromJson(EnterInAccount.userDetailsG, User.class);
        DBConnect db = new DBConnect();
        //initialize user details and request
        ResultSet result = db.getDataFromRequestOnIdUser(user.getId());
        String actionRequest = "Запрос отустствует.";
        int numberResult = 0;
        try {
            while (result.next()){
                numberResult = result.getInt("id");
            }
        } catch (SQLException throwables) {
            numberResult = 0;
        }
        if (numberResult != 0)
            actionRequest = "Запрос обрабатывается.";
        showUserDetails(user, actionRequest);
        //initialize user order
        orderTable = initializeMyOrderTable();
        idOrderView.setCellValueFactory(new PropertyValueFactory<OrderTable,Integer>("id"));
        actionView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("action"));
        nameEmployeeView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("nameEmployee"));
        phoneNumberView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("phone"));
        emailEmployeeView.setCellValueFactory(new PropertyValueFactory<OrderTable,String>("email"));
        startDateView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("startDate"));
        nameOrderView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("name" ));
        orderViewTable.setItems(orderTable);
        //initialize view for employee


    }

    private ObservableList<OrderTable> initializeMyOrderTable(){

        DBConnect db = new DBConnect();
        ResultSet resultSet = db.getDataFromOrderOnIdUser(User.CURRENT_ID);
        try {
            while (resultSet.next()){
                String phone = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                String nameEmployee = resultSet.getString("employee.name");
                int idOrder = resultSet.getInt("idOrder");
                String action = resultSet.getString("action");
                String startDate = resultSet.getString("startDate");
                String name = resultSet.getString("service.name");
                orderTable.add(new OrderTable(phone,email,
                        nameEmployee,idOrder,action,startDate, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (orderTable.isEmpty()){
            return null;
        }
        else {
            return orderTable;
        }
    }
}



