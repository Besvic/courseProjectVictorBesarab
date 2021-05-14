package server;

import program.classes.Const;
import program.process.Process;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Locale;


public class ServerWork {
    /*public static void main(String[] args) {
        try ( ServerSocket server = new ServerSocket(7777)) {
            Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Client started.");




                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    public static Methods getServerStream = null;



    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(7775)) {
            System.out.println("Server started.");
            while (true) {

                Methods methods = new Methods(server);
                getServerStream = methods;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Process process = new Process();
                        while (true) {
                            String input = getServerStream.readLine();
                            switch (input){
                                case Const.RegistrationUser:
                                    String strG = methods.readLine();
                                    String answer = process.setRegistrationUser(strG);
                                    methods.writeLine(answer);
                                    break;
                                case Const.AUTHORISATION:
                                    if (methods.readLine().equals(Const.EMPLOYEE_AUTHORISATION)) {
                                        String strGson = methods.readLine();
                                        answer = process.checkEmployeeAuthorisation(strGson);
                                        methods.writeLine(answer);
                                    }else{
                                        String strGson = methods.readLine();
                                        process.checkUserAuthorisation(strGson);
                                    }
                                    break;
                                case Const.AUTHORISATION_ADMIN:
                                    process.checkAdmin();
                                    break;
                                case Const.UPDATE_USER_DETAILS:
                                    process.updateUserDetails(getServerStream.readLine());
                                    break;
                                case Const.DELETE_CURRENT_USER:
                                    process.deleteUserAccountOnCurrentId(getServerStream.readLine());
                                    break;
                                case Const.SEND_MANAGER_REQUEST_fROM_USER_MENU:
                                    process.setUserRequest();
                                    break;
                                case Const.GET_USER_CURRENT_DETAILS:
                                    process.getUserDetailsOnId(Integer.parseInt(getServerStream.readLine()));
                                    break;
                                case Const.UPDATE_EMPLOYEE_DETAILS:
                                    process.updateEmployeeDetails(getServerStream.readLine());
                                    break;
                                case Const.GET_EMPLOYEE_CURRENT_DETAILS:
                                    process.getEmployeeDetailsOnId(Integer.parseInt(getServerStream.readLine()));
                                    break;
                                case Const.DELETE_CURRENT_EMPLOYEE:
                                    process.deleteEmployeeOnId(Integer.parseInt(getServerStream.readLine()));
                                    break;
                                case Const.GET_DATA_FOR_INITIALISE_VIEW_REQUEST:
                                    process.getDataForViewRequest();
                                    break;
                                case Const.COMMIT_REQUEST_IN_EMPLOYEE_ACCOUNT:
                                    process.insertOrder();
                                    break;
                                case Const.REJECT_REQUEST:
                                    process.rejectRequest();
                                    break;
                                case Const.INITIALIZE_VIEW_REQUEST:
                                    process.getDataForInitializeViewRequest();
                                    break;
                                case Const.INITIALIZE_CURRENT_ORDER_VIEW_TABLE:
                                    process.initializeCurrentOrderViewTable();
                                    break;
                                case Const.INITIALIZE_COMPLETED_VIEW_TABLE:
                                    process.initializeCompletedOrderViewTable();
                                    break;
                                case Const.ADD_ACTS_OF_WORK:
                                    process.addActsOfWork();
                                    break;
                                case Const.ADD_STATISTIC_EMPLOYEE:
                                    process.addStatisticMark();
                                    break;
                                case Const.INITIALIZE_ALL_EMPLOYEE_TABLE_VIEW:
                                    process.initializeEmployeeTableView();
                                    break;
                                case Const.INITIALIZE_GRAPHIC_DEPENDENCE_COST_ON_CITY_BY_ID_EMPLOYEE:
                                    process.getCityAndCostOnIdEmployee();
                                    break;
                                case Const.INITIALIZE_DIAGRAM_COST_AND_MONTH_BY_ID_EMPLOYEE:
                                    process.getCostAndMonthByIdEmployee();
                                    break;
                                case Const.SEND_REQUEST_FOR_EMPLOYEE:
                                    process.addRequest();
                                    break;


                                case Const.Exit:
                                    break;
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
