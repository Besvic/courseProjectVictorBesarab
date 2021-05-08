package program.process;

import com.google.gson.Gson;
/*import controller.employee.MainMenuEmployee;
import controller.employee.ViewRequest;*/
import dbconnection.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import program.classes.Const;
import program.classes.Employee;
import program.classes.User;
import program.classes.ViewRequest;
import server.ServerWork;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


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
            ServerWork.getServerStream.writeLine(gson.toJson(userAdd));
            ServerWork.getServerStream.writeLine(Integer.toString(userAdd.getId()));
        }
        else{
            ServerWork.getServerStream.writeLine(Const.FUNCTION_FAILED);
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
            ServerWork.getServerStream.writeLine(gson.toJson(user));
        else
            ServerWork.getServerStream.writeLine("0");
    }

    public void updateUserDetails(String stringG) {
        DBConnect db = new DBConnect();
        int answer = db.updateUserDetails(stringG);
        if ( answer == 0){
            ServerWork.getServerStream.writeLine(Const.FUNCTION_FAILED);
        }
        else{
            ServerWork.getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        }
    }
    public void deleteUserAccountOnCurrentId(String id){
        int idInt = Integer.parseInt(id);
        DBConnect db = new DBConnect();
        db.userDeleteOnCurrentID(idInt);
    }

    public void setUserRequest(String idUser, String idEmployee, String phoneNumber, String comment){
       DBConnect db = new DBConnect();
       int answer = 0;
        if (idEmployee.isEmpty())
            answer = db.setUserRequest(Integer.parseInt(idUser), 0, comment,phoneNumber);
        else
            answer = db.setUserRequest(Integer.parseInt(idUser), Integer.parseInt(idEmployee), comment,phoneNumber);
        if (answer == 0)
            ServerWork.getServerStream.writeLine(Const.FUNCTION_FAILED);
        else
            ServerWork.getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
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
            ServerWork.getServerStream.writeLine(gson.toJson(employee));
        else
            ServerWork.getServerStream.writeLine("0");
    }

    public void updateEmployeeDetails(String stringG){
        Gson gson = new Gson();
        Employee employee = gson.fromJson(stringG, Employee.class);
        DBConnect db = new DBConnect();
        int result = db.updateEmployeeDetails(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhoneNumber()
                , employee.getLogin(), employee.getPassword());
        if (result == 0)
            ServerWork.getServerStream.writeLine(Const.FUNCTION_FAILED);
        else
            ServerWork.getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
    }

    public void deleteEmployeeOnId(int id){
        DBConnect db = new DBConnect();
        if (db.deleteEmployeeOnId(id) != 0 )
            ServerWork.getServerStream.writeLine(Const.FUNCTION_COMPLETED_SUCCESSFUL);
        else
            ServerWork.getServerStream.writeLine(Const.FUNCTION_FAILED);
    }



    // get data for table view

    public void getDataForViewRequest(){
        DBConnect db = new DBConnect();
        ObservableList<ViewRequest> viewRequest = FXCollections.observableArrayList();
        ResultSet result = db.getDataForViewRequest(Integer.parseInt(ServerWork.getServerStream.readLine()));
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
        ServerWork.getServerStream.writeLine(gson.toJson(result));
    }


}
