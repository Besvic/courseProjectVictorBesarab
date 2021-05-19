package process.controller.user;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import process.controller.EnterInAccount;
import process.controller.Main;
import process.controller.error.ErrorInput;
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
import program.classes.*;
import program.helperClasses.EmployeeTableView;

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
    @FXML
    private Label idEmployeeForStatisticLabel;
    @FXML // fx:id="idEmployeeText"
    private Label idEmployeeForReceiptLabel; // Value injected by FXMLLoader
    @FXML // fx:id="confirmReceiptButton"
    private Button confirmReceiptButton; // Value injected by FXMLLoader
    @FXML // fx:id="sendRequestButton"
    private Button sendRequestButton; // Value injected by FXMLLoader
    @FXML
    private TextField serviceQualityMarkForStatisticField;
    @FXML
    private TextField serviceSpeedMarkForStatisticField;
    @FXML
    private TextField politenessMarkForStatisticField;
    @FXML // fx:id="cityField"
    private Label idEmployeeForChoiceLabel; // Value injected by FXMLLoader
    @FXML // fx:id="commentForRequestField"
    private TextField commentForRequestField; // Value injected by FXMLLoader
    @FXML // fx:id="telephoneNumberField"
    private TextField telephoneNumberField; // Value injected by FXMLLoader
    @FXML // fx:id="commentForReceiptText"
    private TextField commentForReceiptText; // Value injected by FXMLLoader
    @FXML // fx:id="timeReceiptText"
    private TextField phoneReceiptText; // Value injected by FXMLLoader
    @FXML
    private DatePicker dateReceiptText;
    @FXML // fx:id="orderViewTable"
    private TableView<OrderTable> orderViewTable; // Value injected by FXMLLoader
    @FXML // fx:id="phoneNumberView"
    private TableColumn<OrderTable, String> phoneNumberView; // Value injected by FXMLLoader
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
    @FXML
    private TableView<EmployeeTableView> employeeTableView;
    @FXML
    private TableColumn<EmployeeTableView, String> nameEmployeeColumnEmployeeTableView;
    @FXML
    private TableColumn<EmployeeTableView, Double> ratingEmployeeColumnEmployeeTableView;
    @FXML
    private TableColumn<EmployeeTableView, Integer> idEmployeeColumnEmployeeTableView;
    @FXML
    private TableColumn<EmployeeTableView, String> positionEmployeeColumnEmployeeTableView;


    ObservableList<OrderTable> orderTable = FXCollections.observableArrayList();

    ObservableList<EmployeeTableView> employeeTableViews = FXCollections.observableArrayList();

    @FXML
    String getServiceSpeedMarkForStatistic() {
        return serviceSpeedMarkForStatisticField.getText().trim();
    }

    @FXML
    String getServiceQualityMarkForStatistic() {
        return serviceQualityMarkForStatisticField.getText().trim();
    }

    @FXML
    String getPolitenessMarkForStatistic() {
        return politenessMarkForStatisticField.getText().trim();
    }

    @FXML
    void deleteDetails(ActionEvent event) {
        Main.getMethod().writeLine(Const.DELETE_CURRENT_USER);
        Main.getMethod().writeLine(String.valueOf(User.CURRENT_ID));
        Main main = new Main();
        main.getWindow("/fxml/start/mainWindow.fxml","Вход/Авторизация");
    }

    @FXML
    String getPhoneReceiptText() {
        return phoneReceiptText.getText().trim();
    }

    @FXML
    String getCommentForReceiptText() {
        return commentForReceiptText.getText().trim();
    }

    @FXML
    String getDateReceiptText() {
        return dateReceiptText.getValue().toString();
    }

    @FXML
    void sendStatistic(ActionEvent event) {
        try {
            if (idEmployeeForStatisticLabel.getText().trim().isEmpty() || getPolitenessMarkForStatistic().isEmpty() || getServiceSpeedMarkForStatistic().isEmpty() ||
                    getServiceQualityMarkForStatistic().isEmpty() ||
                   Double.valueOf(getPolitenessMarkForStatistic()) > 5 || Double.valueOf(getServiceSpeedMarkForStatistic()) > 5 ||
                    Double.valueOf(getServiceQualityMarkForStatistic()) > 5) {
                ErrorInput err = new ErrorInput();
                err.show();
            } else {
                Main.getMethod().writeLine(Const.ADD_STATISTIC_EMPLOYEE);
                Main.getMethod().writeLine(getServiceSpeedMarkForStatistic());
                Main.getMethod().writeLine(getServiceQualityMarkForStatistic());
                Main.getMethod().writeLine(getPolitenessMarkForStatistic());
                Main.getMethod().writeLine(idEmployeeForStatisticLabel.getText().trim());
                if (Main.getMethod().readLine().equals(Const.FUNCTION_FAILED)) {
                    ErrorInput err = new ErrorInput();
                    err.show();
                }else {
                    Main main = new Main();
                    main.getWindow("/fxml/user/MenuForUser.fxml", "Меню пользователя");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ErrorInput err = new ErrorInput();
            err.show();
        }
    }
    @FXML
    void confirmReceipt(ActionEvent event) {
        String stringCurrentDate = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat defaultDateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = null;
        Date userDate = null;
        try {
            currentDate = defaultDateFormat.parse(stringCurrentDate);
            userDate = defaultDateFormat1.parse(getDateReceiptText());
        } catch (ParseException e) {
            ErrorInput err = new ErrorInput();
            err.show();
            e.printStackTrace();
        }
        System.out.println(currentDate.before(userDate));
        if (currentDate.before(userDate) && !getPhoneReceiptText().isEmpty() && !getDateReceiptText().isEmpty() &&
                !idEmployeeForReceiptLabel.getText().trim().isEmpty() && !getCommentForReceiptText().isEmpty()){
            Main.getMethod().writeLine(Const.SEND_REQUEST_FOR_EMPLOYEE);
            Request request = new Request(User.CURRENT_ID, Integer.parseInt(idEmployeeForReceiptLabel.getText()), getPhoneReceiptText(), getCommentForReceiptText(), getDateReceiptText());
            Gson gson = new Gson();
            Main.getMethod().writeLine(gson.toJson(request));
            if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)){
                Main main = new Main();
                main.getWindow("/fxml/user/MenuForUser.fxml", "Меню пользователя");
            }else {
                ErrorInput err = new ErrorInput();
                err.showErrorProcess();
            }
        } else {
            ErrorInput err = new ErrorInput();
            err.show();
        }

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
            if (!telephoneNumberField.getText().trim().isEmpty() && !commentForRequestField.getText().trim().isEmpty()) {
                Main.getMethod().writeLine(Const.SEND_MANAGER_REQUEST_fROM_USER_MENU);
                Request request = new Request(User.CURRENT_ID, telephoneNumberField.getText().trim(), commentForRequestField.getText().trim());
                Gson gson = new Gson();
                Main.getMethod().writeLine(gson.toJson(request));
                if (Main.getMethod().readLine().equals(Const.FUNCTION_COMPLETED_SUCCESSFUL)) {
                    answer = "Заявка сформирована.";
                }
                else {
                   ErrorInput err = new ErrorInput();
                   err.showErrorProcess();
                }
            }
            else{
                ErrorInput err = new ErrorInput();
                err.show();
            }
        } catch (NumberFormatException e) {
            ErrorInput err = new ErrorInput();
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
        String actionRequest = "Заявка отустствует.";
        int numberResult = 0;
        try {
            while (result.next()){
                numberResult = result.getInt("id");
            }
        } catch (SQLException throwables) {
            numberResult = 0;
        }
        if (numberResult != 0)
            actionRequest = "Заявка обрабатывается.";
        showUserDetails(user, actionRequest);
        //initialize user order
        orderTable = initializeMyOrderTable();
       /* idOrderView.setCellValueFactory(new PropertyValueFactory<OrderTable,Integer>("id"));*/
        nameEmployeeView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("nameEmployee"));
        phoneNumberView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("phone"));
        emailEmployeeView.setCellValueFactory(new PropertyValueFactory<OrderTable,String>("email"));
        startDateView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("startDate"));
        nameOrderView.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("name" ));
        orderViewTable.setItems(orderTable);
        //initialize view for employee
        initializeEmployeeViewTable();


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
               /* String action = resultSet.getString("action");*/
                String startDate = resultSet.getString("dateStart");
                String name = resultSet.getString("service.name");
                orderTable.add(new OrderTable(phone,email,
                        nameEmployee,idOrder,/*action*/"",startDate, name));
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

    private void showIdEmployeeTableView(EmployeeTableView employeeTableView){
        if (employeeTableView != null){
            idEmployeeForReceiptLabel.setText(String.valueOf(employeeTableView.getId()));
            idEmployeeForStatisticLabel.setText(String.valueOf(employeeTableView.getId()));
           // idEmployeeForChoiceLabel.setText(String.valueOf(employeeTableView.getId()));
        }else {
            idEmployeeForReceiptLabel.setText("");
            idEmployeeForStatisticLabel.setText("");
            //idEmployeeForChoiceLabel.setText("");
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
/*
                idEmployeeColumnEmployeeTableView.setCellValueFactory(new PropertyValueFactory<EmployeeTableView, Integer>("id"));
*/
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



