package dbconnection;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import com.google.gson.Gson;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import program.classes.Const;
import program.classes.Employee;
import program.classes.User;
import server.ServerWork;

import java.sql.*;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/mycource4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
public static Statement statement;
private static Connection connection;

/*static {
    try {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
        throw new RuntimeException();
    }

}
static {
    try {
        statement = connection.createStatement();
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException();
    }
}

    public static void main(String[] args) {
       // Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            statement.executeUpdate("select *" +"from users");
        } catch (SQLException throwables) {
            System.out.println("Error");
            throwables.printStackTrace();
        }

    }

 */





    public static Connection getConnect () throws SQLException {

        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//            if (!connection.isClosed()){
//                System.out.println("Connection");
//            }

        }
        catch (SQLException e){
            System.err.println("Error download driver");
            e.getMessage();
        }
        return connection;
       /* try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){
//            statement.execute("insert into users (nameUser, loginUser, passwordUser) " +
//                    "values ('Arina', 'Arin', '123')");
//            statement.executeUpdate("update users set nameUser = 'Kate', loginUser = 'Rin', passwordUser = 'Rin'  where nameUser = 'Arina'");
//            ResultSet res = statement.executeQuery("select* " +
//                    "from users");
//            System.out.println(res);

        } catch (SQLException e){
            e.getMessage();
            System.out.println("err");
        }*/
    }

    public ResultSet getUserOnLogin(User user){
        String queryCheckLoginSQL = "SELECT * " +
                "FROM users " +
                "WHERE users.login = ?";
        try {
            PreparedStatement preparedStatement = getConnect().prepareStatement(queryCheckLoginSQL);
            preparedStatement.setString(1, user.getLogin());
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void insertUser(User userAdd){
        String insertUserSQL = "INSERT INTO users (name, email, login, password)" +
                "VALUE(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnect().prepareStatement(insertUserSQL);
            preparedStatement.setString(1, userAdd.getName());
            preparedStatement.setString(2, userAdd.getEmail());
            preparedStatement.setString(3, userAdd.getLogin());
            preparedStatement.setString(4, userAdd.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet checkUserAuthorisation(User user){
        ResultSet resultSet = null;
        String queryCheckLoginSQL = "SELECT * " +
                "FROM users " +
                "WHERE users.login = ? && users.password = ?";
        try {
            PreparedStatement preparedStatement = getConnect().prepareStatement(queryCheckLoginSQL);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public int updateUserDetails(String stringG){
        Gson gson = new Gson();
        User user = new User();
        user = gson.fromJson(stringG, User.class);
        String query = "UPDATE users " +
                "SET name = ?, email = ?, login = ?, password = ? " +
                "where idUser = ?";
        try{
            PreparedStatement pS = getConnect().prepareStatement(query);
            pS.setString(1, user.getName());
            pS.setString(2, user.getEmail());
            pS.setString(3, user.getLogin());
            pS.setString(4, user.getPassword());
            pS.setInt(5, user.getId());
            return pS.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void userDeleteOnCurrentID(int id){
        String query = "DELETE FROM users " +
                "WHERE idUser = ?";
        try{
            PreparedStatement pS = getConnect().prepareStatement(query);
            pS.setInt(1, id);
            pS.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public ResultSet getUserDetails(int id){
        String query = "SELECT * " +
                "FROM users " +
                "WHERE idUser = ? ";
        try {
            PreparedStatement preparedStatement = getConnect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int setUserRequest(int idUser, int idEmployee, String comment, String phoneNumber){
        String query = null;
        if (idEmployee != 0)
            query = "INSERT INTO request (idUser, phoneNumber, comment, choiceIdEmployee) " +
                    "value (?, ?, ?, ?)";
            else
            query =  "INSERT INTO request (idUser, phoneNumber, comment) " +
                    "value (?, ?, ?)";
            try {
                PreparedStatement pS = getConnect().prepareStatement(query);
                pS.setInt(1, idUser);
                pS.setString(2, phoneNumber);
                pS.setString(3, comment);
                if (idEmployee != 0)
                    pS.setInt(4, idEmployee);
                int i = pS.executeUpdate();
                return i;
            } catch (SQLException throwables) {
                return 0;
            }


    }






    //employee function
    public ResultSet getEmployeeDetails(int id){
        String query = "SELECT * " +
                "FROM employee " +
                "WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = getConnect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
    public ResultSet checkEmployeeAuthorisation(Employee employee){
        ResultSet resultSet = null;
        String queryCheckLoginPasswordSQL = "SELECT * " +
                "FROM employee " +
                "WHERE employee.login = ? && employee.password = ?";
        try {
            PreparedStatement preparedStatement = getConnect().prepareStatement(queryCheckLoginPasswordSQL);
            preparedStatement.setString(1, employee.getLogin());
            preparedStatement.setString(2, employee.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public int updateEmployeeDetails(int id, String name, String email, String phoneNumber, String login
            , String password){
        String query = "UPDATE employee " +
                "SET name = ?, login = ?, password = ?, phoneNumber = ?, email = ? " +
                "WHERE id = ? ";
        try{
            PreparedStatement pS = getConnect().prepareStatement(query);
            pS.setString(1, name);
            pS.setString(2, login);
            pS.setString(3, password);
            pS.setString(4, phoneNumber);
            pS.setString(5, email);
            pS.setInt(6, id);
            return pS.executeUpdate();

        } catch (SQLException throwables) {
            return 0;
        }
    }

    public int deleteEmployeeOnId(int id){
        String query = "DELETE FROM employee " +
                "WHERE id = ? ";
        try{
            PreparedStatement pS = getConnect().prepareStatement(query);
            pS.setInt(1, id);
            return pS.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }




    public ResultSet getDataFromRequestOnIdUser(int id){
        ResultSet resultSet = null;
        String query = "SELECT * " +
                "FROM request " +
                "WHERE request.idUser = ?";
        try {
            PreparedStatement pS = getConnect().prepareStatement(query);
            pS.setInt(1, id);
            resultSet = pS.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getDataFromOrderOnIdUser(int id){
        ResultSet result = null;
        String query = "select order.idOrder, order.action, order.startDate," +
                " employee.name, employee.email,employee.phoneNumber, service.name " +
                "from `order`" +
                "inner join employee on order.idEmpl = employee.id " +
                "inner join service on `order`.idService = service.idService " +
                "where idUser = " + id;
        try {
            PreparedStatement pS = getConnect().prepareStatement(query);
            result = pS.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    // get data for table view

    public ResultSet getDataForViewRequest(int id){
        String query = "SELECT request.*, u.name " +
                "from request " +
                "inner join users u on request.idUser = u.idUser " +
                "where request.idUser = ? ";
        try {
            PreparedStatement pS = getConnect().prepareStatement(query);
            pS.setInt(1, id);
            return pS.executeQuery();

        } catch (SQLException throwables) {
            return null;
        }
    }



}
































































//    // JDBC variables for opening and managing connection
//    private static Connection con;
//    private static Statement stmt;
//    private static ResultSet rs;
//
//    public static void main(String args[]) {
//        String query = "select count(*) from users";
//
//        try {
//            // opening database connection to MySQL server
//            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//            // getting Statement object to execute query
//            stmt = con.createStatement();
//
//            // executing SELECT query
//            rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                int count = rs.getInt(1);
//                System.out.println("Total number of books in the table : " + count);
//            }
//
//        } catch (SQLException sqlEx) {
//            sqlEx.printStackTrace();
//        } finally {
//            //close connection ,stmt and resultset here
//            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
//        }
//    }