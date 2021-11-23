package server;

import program.classes.Const;
import program.process.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerWork extends Thread{

    private static Socket cl = null;
    public static BufferedReader sin;
    public static PrintStream sout;


    public ServerWork(Socket cl) {
        this.cl = cl;
    }

    public void run() {
        try {
            try {
                sin = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sout = new PrintStream(cl.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                String input = null;//получаем информацию о команде
                try {
                    try {
                        input = sin.readLine();
                    } catch (SocketException ex) {
                        System.out.println("Клиент отключился");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Process process = new Process();
                    switch (input){
                        case Const.RegistrationUser:
                            String strG = sin.readLine();
                            String answer = process.setRegistrationUser(strG);
                            sout.println(answer);
                            break;
                        case Const.AUTHORISATION:
                            if (sin.readLine().equals(Const.EMPLOYEE_AUTHORISATION)) {
                                String strGson = sin.readLine();
                                answer = process.checkEmployeeAuthorisation(strGson);
                                sout.println(answer);
                            }else{
                                String strGson = sin.readLine();
                                process.checkUserAuthorisation(strGson);
                            }
                            break;
                        case Const.AUTHORISATION_ADMIN:
                            process.checkAdmin();
                            break;
                        case Const.UPDATE_USER_DETAILS:
                            process.updateUserDetails(ServerWork.sin.readLine());
                            break;
                        case Const.DELETE_CURRENT_USER:
                            process.deleteUserAccountOnCurrentId(ServerWork.sin.readLine());
                            break;
                        case Const.SEND_MANAGER_REQUEST_fROM_USER_MENU:
                            process.setUserRequest();
                            break;
                        case Const.GET_USER_CURRENT_DETAILS:
                            process.getUserDetailsOnId(Integer.parseInt(ServerWork.sin.readLine()));
                            break;
                        case Const.UPDATE_EMPLOYEE_DETAILS:
                            process.updateEmployeeDetails(ServerWork.sin.readLine());
                            break;
                        case Const.GET_EMPLOYEE_CURRENT_DETAILS:
                            process.getEmployeeDetailsOnId(Integer.parseInt(ServerWork.sin.readLine()));
                            break;
                        case Const.DELETE_CURRENT_EMPLOYEE:
                            process.deleteEmployeeOnId(Integer.parseInt(ServerWork.sin.readLine()));
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
                        case Const.INITIALIZE_CURRENT_DETAILS_ADMIN:
                            process.getDetailsAdmin();
                            break;
                        case Const.DELETE_CURRENT_ADMIN:
                            process.deleteCurrentAdmin();
                            break;
                        case Const.CREATE_EMPLOYEE:
                            process.createEmployee();
                            break;
                        case Const.DELETE_EMPLOYEE:
                            process.deleteEmployeeOnId(Integer.parseInt(ServerWork.sin.readLine()));
                            break;
                        case Const.INITIALIZE_DIAGRAM_COST_AND_MONTH_ALL_EMPLOYEE:
                            process.getBarAllCostAndMonth();
                            break;
                        case Const.INITIALIZE_GRAPHIC_DEPENDENCE_COST_ON_CITY_ALL_EMPLOYEE:
                            process.getAllDataForAreaCharts();
                            break;
                        case Const.GET_DATA_FOR_REJECT_REQUEST_TABLE_VIEW:
                            process.initializeRejectRequestTableView();
                            break;
                        case Const.INITIALIZE_COST_LABEL_FOR_CREATE_ORDER:
                            process.getCostForCreateOrder();
                            break;
                        case Const.Exit:
                            break;
                    }
                } catch (NullPointerException ex) {
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                sin.close();
                sout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerSetting.connectionNumber--;
        }
    }






























   /* public static Methods ServerWork = null;
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(7775)) {
            System.out.println("Server started.");
            while (true) {
                Methods methods = new Methods(server);
                ServerWork = methods;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Process process = new Process();
                        while (true) {
                            String input = ServerWork.sin.sin.readLine();
                            switch (input){
                                case Const.RegistrationUser:
                                    String strG = methods.sin.sin.readLine();
                                    String answer = process.setRegistrationUser(strG);
                                    methods.sout.println(answer);
                                    break;
                                case Const.AUTHORISATION:
                                    if (methods.sin.sin.readLine().equals(Const.EMPLOYEE_AUTHORISATION)) {
                                        String strGson = methods.sin.sin.readLine();
                                        answer = process.checkEmployeeAuthorisation(strGson);
                                        methods.sout.println(answer);
                                    }else{
                                        String strGson = methods.sin.sin.readLine();
                                        process.checkUserAuthorisation(strGson);
                                    }
                                    break;
                                case Const.AUTHORISATION_ADMIN:
                                    process.checkAdmin();
                                    break;
                                case Const.UPDATE_USER_DETAILS:
                                    process.updateUserDetails(ServerWork.sin.sin.readLine());
                                    break;
                                case Const.DELETE_CURRENT_USER:
                                    process.deleteUserAccountOnCurrentId(ServerWork.sin.readLine());
                                    break;
                                case Const.SEND_MANAGER_REQUEST_fROM_USER_MENU:
                                    process.setUserRequest();
                                    break;
                                case Const.GET_USER_CURRENT_DETAILS:
                                    process.getUserDetailsOnId(Integer.parseInt(ServerWork.sin.readLine()));
                                    break;
                                case Const.UPDATE_EMPLOYEE_DETAILS:
                                    process.updateEmployeeDetails(ServerWork.sin.readLine());
                                    break;
                                case Const.GET_EMPLOYEE_CURRENT_DETAILS:
                                    process.getEmployeeDetailsOnId(Integer.parseInt(ServerWork.sin.readLine()));
                                    break;
                                case Const.DELETE_CURRENT_EMPLOYEE:
                                    process.deleteEmployeeOnId(Integer.parseInt(ServerWork.sin.readLine()));
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
                                case Const.INITIALIZE_CURRENT_DETAILS_ADMIN:
                                    process.getDetailsAdmin();
                                    break;
                                case Const.DELETE_CURRENT_ADMIN:
                                    process.deleteCurrentAdmin();
                                    break;
                                case Const.CREATE_EMPLOYEE:
                                    process.createEmployee();
                                    break;
                                case Const.DELETE_EMPLOYEE:
                                    process.deleteEmployeeOnId(Integer.parseInt(ServerWork.sin.readLine()));
                                case Const.INITIALIZE_DIAGRAM_COST_AND_MONTH_ALL_EMPLOYEE:
                                    process.getBarAllCostAndMonth();
                                    break;
                                case Const.INITIALIZE_GRAPHIC_DEPENDENCE_COST_ON_CITY_ALL_EMPLOYEE:
                                    process.getAllDataForAreaCharts();
                                    break;
                                case Const.GET_DATA_FOR_REJECT_REQUEST_TABLE_VIEW:
                                    process.initializeRejectRequestTableView();
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
    }*/
}
