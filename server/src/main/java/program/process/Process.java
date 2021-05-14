package program.process;

import com.google.gson.Gson;
/*import controller.employee.MainMenuEmployee;
import controller.employee.ViewRequest;*/
import com.sun.javafx.collections.MappingChange;
import dbconnection.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import program.classes.*;
import program.helperClasses.CurrentOrderViewTable;
import program.classes.Statistic;
import program.helperClasses.EmployeeTableView;
import program.helperClasses.InitializeGraphicArrows;
import program.helperClasses.StatisticMark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static server.ServerWork.getServerStream;


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
                userAdd.setPassword(result.getString("password"));
                userAdd.setEmail(result.getString("email"));
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (i == 1) {
            getServerStream.writeLine(gson.toJson(userAdd));
            getServerStream.writeLine(Integer.toString(userAdd.getId()));
        }
        else{
            getServerStream.writeLine(Const.FUNCTION_FAILED);
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
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Gson gson = new Gson();
        if (i != 0)
            getServerStream.writeLine(gson.toJson(user));
        else
            getServerStream.writeLine("0");
    }

    public void updateUserDetails(String stringG) {
        DBConnect db = new DBConnect();
        int answer = db.updateUserDetails(stringG);
        if ( answer == 0){
            getServerStream.writeLine(Const.FUNCTION_FAILED);
        }
        else{
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        }
    }
    public void deleteUserAccountOnCurrentId(String id){
        int idInt = Integer.parseInt(id);
        DBConnect db = new DBConnect();
        db.userDeleteOnCurrentID(idInt);
    }

    public void setUserRequest(){
       DBConnect db = new DBConnect();
       Gson gson = new Gson();
       Request request = gson.fromJson(getServerStream.readLine(), Request.class);
        if (db.setUserRequestForManager(request) == 0)
            getServerStream.writeLine(Const.FUNCTION_FAILED);
        else
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void addStatisticMark(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ArrayList<StatisticMark> statistic = new ArrayList<>();
        statistic.add(new StatisticMark("serviceSpeed", Double.valueOf(getServerStream.readLine())));
        statistic.add(new StatisticMark("serviceQuality", Double.valueOf(getServerStream.readLine())));
        statistic.add(new StatisticMark("politeness", Double.valueOf(getServerStream.readLine())));
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
                statistic.get(0).getName(), Integer.parseInt(getServerStream.readLine())) != 0)
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            getServerStream.writeLine(Const.FUNCTION_FAILED);
    }

    public void addRequest(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        Request request = gson.fromJson(getServerStream.readLine(), Request.class);
        if (db.addRequest(request.getIdUser(), request.getIdEmployee(), request.getPhoneNumber(), request.getComment(),
                request.getChoiceDate()) != 0)
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            getServerStream.writeLine(Const.FUNCTION_FAILED);

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
            getServerStream.writeLine(gson.toJson(employee));
        else
            getServerStream.writeLine("0");
    }

    public void updateEmployeeDetails(String stringG){
        Gson gson = new Gson();
        Employee employee = gson.fromJson(stringG, Employee.class);
        DBConnect db = new DBConnect();
        int result = db.updateEmployeeDetails(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhoneNumber()
                , employee.getLogin(), employee.getPassword());
        if (result == 0)
            getServerStream.writeLine(Const.FUNCTION_FAILED);
        else
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void deleteEmployeeOnId(int id){
        DBConnect db = new DBConnect();
        if (db.deleteEmployeeOnId(id) != 0 )
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            getServerStream.writeLine(Const.FUNCTION_FAILED);
    }

    public void insertOrder(){
        String idRequest = getServerStream.readLine();
        String stringG = getServerStream.readLine();
        Gson gson = new Gson();
        Service service = gson.fromJson(stringG, Service.class);
        DBConnect db = new DBConnect();
        if (db.insertOrder(service, Integer.parseInt(idRequest)) != 0)
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            getServerStream.writeLine(Const.FUNCTION_FAILED);
    }

    public void rejectRequest(){
        DBConnect db = new DBConnect();
        if (db.deleteRowFromRequest(Integer.parseInt(getServerStream.readLine()), getServerStream.readLine()) == 0)
            getServerStream.writeLine(Const.FUNCTION_FAILED);
        else
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void addActsOfWork(){
        DBConnect db = new DBConnect();
        if (db.insertRowIntoActsOfWork(Integer.parseInt(getServerStream.readLine())) == 1)
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            getServerStream.writeLine(Const.FUNCTION_FAILED);
    }


    // admin function

    public void checkAdmin(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.checkAdminAuthorisation(gson.fromJson(getServerStream.readLine(), Admin.class));
        if (result == null)
            getServerStream.writeLine(Const.FUNCTION_FAILED);
        else {
            getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
            try {
                result.next();
                getServerStream.writeLine(result.getString("id"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                getServerStream.writeLine(Const.FUNCTION_FAILED);
            }

        }
    }


    // get data for table view

    public void getDataForViewRequest(){
        DBConnect db = new DBConnect();
        ObservableList<ViewRequest> viewRequest = FXCollections.observableArrayList();
        ResultSet result = db.getDataForViewRequest(Integer.parseInt(getServerStream.readLine()));
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
        getServerStream.writeLine(gson.toJson(result));
    }


    // get data for view table in employee account

    public void getDataForInitializeViewRequest(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForViewRequest(Integer.parseInt(getServerStream.readLine()));
        try{
            while (result.next()){

                ViewRequest viewRequest = new ViewRequest(result.getInt("id"), result.getString("name")
                        , result.getString("phoneNumber"), result.getString("comment")
                        , result.getString("dateForMeeting"));
                getServerStream.writeLine(gson.toJson(viewRequest));
            }
            getServerStream.writeLine("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initializeCurrentOrderViewTable(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForCurrentOrderViewTable(Integer.parseInt(getServerStream.readLine()));
        try{
            while (result.next()){
                CurrentOrderViewTable currentOrderViewTable = new CurrentOrderViewTable(result.getInt("idOrder"),
                        result.getString("s.name"), result.getString("u.name"), result.getString("email"),
                        result.getString("definition"), result.getDouble("cost"), result.getString("dateStart"));
                getServerStream.writeLine(gson.toJson(currentOrderViewTable));
            }
            getServerStream.writeLine("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void initializeCompletedOrderViewTable(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataForInitializeCompletedOrderViewTable(Integer.parseInt(getServerStream.readLine()));
        try{
            while (result.next()){
                ActsOfWork acts = new ActsOfWork(result.getInt("id"), result.getString("emailUser"),
                        result.getString("endDate"), result.getString("startDate"), result.getDouble("cost"),
                        result.getString("emailEmployee"), result.getString("city"), result.getString("definition"),
                        result.getString("name"), result.getInt("idUser"), result.getInt("idEmployee"));
                getServerStream.writeLine(gson.toJson(acts));
            }
            getServerStream.writeLine("0");
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
                getServerStream.writeLine(gson.toJson(employeeTableView));
            }
            getServerStream.writeLine("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    //graphic

    public void getCityAndCostOnIdEmployee(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getByIdEmployeeDataCityAndCostFromActsOfWork(Integer.parseInt(getServerStream.readLine()));
        try {
            while (result.next()){
                InitializeGraphicArrows init = new InitializeGraphicArrows(result.getDouble("cost"), result.getString("city"));
                getServerStream.writeLine(gson.toJson(init));
            }
            getServerStream.writeLine("0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //diagram
    public void getCostAndMonthByIdEmployee(){
        DBConnect db = new DBConnect();
        Gson gson = new Gson();
        ResultSet result = db.getDataByIdEmployeeCostAndMonthFromActsOfWork(Integer.parseInt(getServerStream.readLine()));
        try{
            while (result.next()){
                InitializeGraphicArrows init = new InitializeGraphicArrows(result.getDouble("cost"), result.getString("month"));
                init.setyString(mothToString(init.getyString()));
                getServerStream.writeLine(gson.toJson(init));
            }
            getServerStream.writeLine("0");
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
