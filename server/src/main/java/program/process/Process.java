package program.process;

import com.google.gson.Gson;
/*import controller.employee.MainMenuEmployee;
import controller.employee.ViewRequest;*/
import com.sun.javafx.collections.MappingChange;
import dbconnection.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import program.classes.*;
import program.helperClasses.*;
import program.classes.Statistic;
import server.ServerWork;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*import static server.ServerWork.ServerWork;*/


public class Process {

    public String setRegistrationUser(String stringGson){
        int i = 0;
        Gson gson = new Gson();
        User userAdd = new User();
        DBConnect db = new DBConnect();
        userAdd = gson.fromJson(stringGson, User.class);
        ResultSet result = db.getUserOnLogin(userAdd);
        while (true){
            try {
                if (!result.next())
                    break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            i++;
        }
        if (i == 0) {
           db.insertUser(userAdd);
           return Const.FUNCTION_COMPLETED_SUCCESSFUL;
        }else {
            return Const.FUNCTION_FAILED;
        }
    }

    public void checkUserAuthorisation(String stringGson){
        int i = 0;
        Gson gson = new Gson();
        User userAdd = gson.fromJson(stringGson, User.class);
        DBConnect db = new DBConnect();
        ResultSet result = db.checkUserAuthorisation(userAdd);
        try {
            while (result.next()) {
                userAdd.setId(result.getInt("idUser"));
                userAdd.setName(result.getString("name"));
                userAdd.setLogin(result.getString("login"));
                userAdd.setPassword(db.deCoding(result.getString("password")));
                userAdd.setEmail(result.getString("email"));
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i == 1) {
            ServerWork.sout.println(gson.toJson(userAdd));
            ServerWork.sout.println(Integer.toString(userAdd.getId()));
        }
        else{
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        }
    }

    public void getUserDetailsOnId(int id){
        DBConnect db = new DBConnect();
        ResultSet result = db.getUserDetails(id);
        User user = new User();
        int i = 0;
        try{
            while (result.next()){
                user.setLogin(result.getString("login"));
                user.setId(result.getInt("idUser"));
                user.setPassword(db.deCoding(result.getString("password")));
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Gson gson = new Gson();
        if (i != 0)
            ServerWork.sout.println(gson.toJson(user));
        else
            ServerWork.sout.println("0");
    }

    public void updateUserDetails(String stringG) {
        DBConnect db = new DBConnect();
        int answer = db.updateUserDetails(stringG);
        if ( answer == 0){
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        }
        else{
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        }
    }
    public void deleteUserAccountOnCurrentId(String id){
        int idInt = Integer.parseInt(id);
        DBConnect db = new DBConnect();
        db.userDeleteOnCurrentID(idInt);
    }

    public void setUserRequest() throws IOException {
       DBConnect db = new DBConnect();
       Gson gson = new Gson();
       Request request = gson.fromJson(ServerWork.sin.readLine(), Request.class);
        if (db.setUserRequestForManager(request) == 0)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void addStatisticMark() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ArrayList<StatisticMark> statistic = new ArrayList<>();
        statistic.add(new StatisticMark("serviceSpeed", Double.valueOf(ServerWork.sin.readLine())));
        statistic.add(new StatisticMark("serviceQuality", Double.valueOf(ServerWork.sin.readLine())));
        statistic.add(new StatisticMark("politeness", Double.valueOf(ServerWork.sin.readLine())));
        Collections.sort(statistic, StatisticMark.COMPARE_BY_MARK);
        while (true){
            if (statistic.get(0).getMark() > statistic.get(1).getMark() + statistic.get(2).getMark())
                break;
            else
                statistic.get(0).setMark(statistic.get(0).getMark() * 2);
        }
        double sum = 0;
        for (var s : statistic )
            sum += s.getMark();


        if (db.addUserStatisticMark(statistic.get(0).getMark() / sum,
                statistic.get(0).getName(), Integer.parseInt(ServerWork.sin.readLine())) != 0)
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            ServerWork.sout.println(Const.FUNCTION_FAILED);
    }

    public void addRequest() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        Request request = gson.fromJson(ServerWork.sin.readLine(), Request.class);
        if (db.addRequest(request.getIdUser(), request.getIdEmployee(), request.getPhoneNumber(), request.getComment(),
                request.getChoiceDate()) != 0)
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            ServerWork.sout.println(Const.FUNCTION_FAILED);

    }


    //Employee function
    public String checkEmployeeAuthorisation(String stringGson){
        int i = 0;
        Gson gson = new Gson();
        Employee employeeAdd = gson.fromJson(stringGson, Employee.class);
        DBConnect db = new DBConnect();
        ResultSet result = db.checkEmployeeAuthorisation(employeeAdd);
        try{
        while (result.next()) {
           Employee.CURRENT_ID = result.getInt("id");
            i++;
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        if (i == 1)
            return String.valueOf(Employee.CURRENT_ID);
        else
            return Const.FUNCTION_FAILED;
    }

    public void getEmployeeDetailsOnId(int id){
        DBConnect db = new DBConnect();
        ResultSet result = db.getEmployeeDetails(id);
        Employee employee = new Employee();
        int i = 0;
        try{
            while (result.next()){
                employee.setLogin(result.getString("login"));
                employee.setId(result.getInt("id"));
                employee.setPassword(result.getString("password"));
                employee.setEmail(result.getString("email"));
                employee.setName(result.getString("name"));
                employee.setPosition(result.getString("position"));
                employee.setPhoneNumber(result.getString("phoneNumber"));
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Gson gson = new Gson();
        if (i != 0)
            ServerWork.sout.println(gson.toJson(employee));
        else
            ServerWork.sout.println("0");
    }

    public void updateEmployeeDetails(String stringG){
        Gson gson = new Gson();
        Employee employee = gson.fromJson(stringG, Employee.class);
        DBConnect db = new DBConnect();
        int result = db.updateEmployeeDetails(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhoneNumber()
                , employee.getLogin(), employee.getPassword());
        if (result == 0)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void deleteEmployeeOnId(int id){
        DBConnect db = new DBConnect();
        if (db.deleteEmployeeOnId(id) != 0 )
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            ServerWork.sout.println(Const.FUNCTION_FAILED);
    }

    public void insertOrder() throws IOException{
        String idRequest = ServerWork.sin.readLine();
        String stringG = ServerWork.sin.readLine();
        Gson gson = new Gson();
        Service service = gson.fromJson(stringG, Service.class);
        DBConnect db = new DBConnect();
        if (db.insertOrder(service, Integer.parseInt(idRequest)) != 0)
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            ServerWork.sout.println(Const.FUNCTION_FAILED);
    }

    public void rejectRequest() throws IOException{
        DBConnect db = new DBConnect();
        if (db.deleteRowFromRequest(Integer.parseInt(ServerWork.sin.readLine()), ServerWork.sin.readLine()) == 0)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void addActsOfWork()  throws IOException {
        DBConnect db = new DBConnect();
        if (db.insertRowIntoActsOfWork(Integer.parseInt(ServerWork.sin.readLine())) == 1)
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            ServerWork.sout.println(Const.FUNCTION_FAILED);
    }


    // admin function

    public void checkAdmin() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.checkAdminAuthorisation(gson.fromJson(ServerWork.sin.readLine(), Admin.class));
        if (result == null)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else {
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
            try {
                result.next();
                ServerWork.sout.println(result.getString("id"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                ServerWork.sout.println(Const.FUNCTION_FAILED);
            }

        }
    }

    public void getDetailsAdmin() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = null;
        if ((result = db.getDetailsOnId(Integer.parseInt(ServerWork.sin.readLine()))) == null)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else {
            try {
                result.next();
                Admin admin = new Admin(result.getString("name"), result.getInt("id"),
                        result.getString("login"), result.getString("password"));
                ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
                ServerWork.sout.println(gson.toJson(admin));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                ServerWork.sout.println(Const.FUNCTION_FAILED);
            }
        }
    }

    public void deleteCurrentAdmin() throws IOException{
        DBConnect db = new DBConnect();
        if (db.deleteCurrentAdminOnId(Integer.parseInt(ServerWork.sin.readLine())) == 0)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }


    public void createEmployee() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        if (db.createEmployee(gson.fromJson(ServerWork.sin.readLine(), Employee.class)) == 0)
            ServerWork.sout.println(Const.FUNCTION_FAILED);
        else
            ServerWork.sout.println(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void initializeRejectRequestTableView(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForRejectRequestTableView();
        try{
            while (result.next()){
                RejectRequestViewTable requestViewTable = new RejectRequestViewTable(result.getString("emailU"),
                        result.getString("emailE"), result.getString("phoneNumber"), result.getString("dateStart"),
                        result.getString("comment"), result.getString("reason"));
                ServerWork.sout.println(gson.toJson(requestViewTable));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // get data for table view

    public void getDataForViewRequest() throws IOException{
        DBConnect db = new DBConnect();
        ObservableList<ViewRequest> viewRequest = FXCollections.observableArrayList();
        ResultSet result = db.getDataForViewRequest(Integer.parseInt(ServerWork.sin.readLine()));
       /* try{
            while (result.next()){
                viewRequest.add(new ViewRequest(result.getInt("id"), result.getString("name")
                        , result.getString("phoneNumber"), result.getString("comment")
                        , result.getString("dateForMeeting")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        Gson gson = new Gson();
        ServerWork.sout.println(gson.toJson(result));
    }


    // get data for view table in employee account

    public void getDataForInitializeViewRequest() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForViewRequest(Integer.parseInt(ServerWork.sin.readLine()));
        try{
            while (result.next()){

                ViewRequest viewRequest = new ViewRequest(result.getInt("id"), result.getString("name")
                        , result.getString("phoneNumber"), result.getString("comment")
                        , result.getString("dateForMeeting"));
                ServerWork.sout.println(gson.toJson(viewRequest));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initializeCurrentOrderViewTable() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForCurrentOrderViewTable(Integer.parseInt(ServerWork.sin.readLine()));
        try{
            while (result.next()){
                CurrentOrderViewTable currentOrderViewTable = new CurrentOrderViewTable(result.getInt("idOrder"),
                        result.getString("s.name"), result.getString("u.name"), result.getString("email"),
                        result.getString("definition"), result.getDouble("cost"), result.getString("dateStart"));
                ServerWork.sout.println(gson.toJson(currentOrderViewTable));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initializeCompletedOrderViewTable() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForInitializeCompletedOrderViewTable(Integer.parseInt(ServerWork.sin.readLine()));
        try{
            while (result.next()){
                ActsOfWork acts = new ActsOfWork(result.getInt("id"), result.getString("emailUser"),
                        result.getString("endDate"), result.getString("startDate"), result.getDouble("cost"),
                        result.getString("emailEmployee"), result.getString("city"), result.getString("definition"),
                        result.getString("name"), result.getInt("idUser"), result.getInt("idEmployee"));
                ServerWork.sout.println(gson.toJson(acts));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initializeEmployeeTableView(){
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        ResultSet result = db.getDataForInitializeEmployeeTableView();
        try {
            while (result.next()){
                EmployeeTableView employeeTableView = new EmployeeTableView(result.getInt("id"), result.getString("name"),
                        result.getString("position"), result.getDouble("mark"));
                ServerWork.sout.println(gson.toJson(employeeTableView));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    //graphic

    public void getCityAndCostOnIdEmployee() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getByIdEmployeeDataCityAndCostFromActsOfWork(Integer.parseInt(ServerWork.sin.readLine()));
        try {
            while (result.next()){
                InitializeGraphicArrows init = new InitializeGraphicArrows(result.getDouble("cost"), result.getString("city"));
                ServerWork.sout.println(gson.toJson(init));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllDataForAreaCharts(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getAreaChartsAllDataCityAndCostFromActsOfWork();
        try {
            while (result.next()){
                InitializeGraphicArrows init = new InitializeGraphicArrows((result.getDouble("cost") / 2), result.getString("city"));
                ServerWork.sout.println(gson.toJson(init));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //diagram
    public void getCostAndMonthByIdEmployee() throws IOException{
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataByIdEmployeeCostAndMonthFromActsOfWork(Integer.parseInt(ServerWork.sin.readLine()));
        try{
            while (result.next()){
                InitializeGraphicArrows init = new InitializeGraphicArrows(result.getDouble("cost"), result.getString("month"));
                init.setyString(mothToString(init.getyString()));
                ServerWork.sout.println(gson.toJson(init));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getBarAllCostAndMonth(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getBarAllDataCostAndMonthFromActsOfWork();
        try{
            while (result.next()){
                InitializeGraphicArrows init = new InitializeGraphicArrows((result.getDouble("cost") / 2), result.getString("month"));
                init.setyString(mothToString(init.getyString()));
                ServerWork.sout.println(gson.toJson(init));
            }
            ServerWork.sout.println("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    String mothToString(String number){
        switch (number){
            case "1":
                return "Январь";
            case "2":
                return "Февраль";
            case "3":
                return "Март";
            case "4":
                return "Апрель";
            case "5":
                return "Май";
            case "6":
                return "Июнь";
            case "7":
                return "Июль";
            case "8":
                return "Август";
            case "9":
                return "Сентябрь";
            case "10":
                return "Октябрь";
            case "11":
                return "Ноябрь";
            case "12":
                return "Декабрь";
            default:
                new RuntimeException();
        }
        return number;
    }
}
