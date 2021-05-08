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
                                case Const.UPDATE_USER_DETAILS:
                                    String strGson = getServerStream.readLine();
                                    process.updateUserDetails(strGson);
                                    break;
                                case Const.DELETE_CURRENT_USER:
                                    String idCurrent =  getServerStream.readLine();
                                    process.deleteUserAccountOnCurrentId(idCurrent);
                                    break;
                                case Const.SEND_REQUEST_fROM_USER_MENU:
                                    process.setUserRequest(getServerStream.readLine(), getServerStream.readLine(), getServerStream.readLine(), getServerStream.readLine());
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
                                case Const.GET_DATA_FOR_INITIALISE_VIEW_REQUEST:
                                    process.getDataForViewRequest();
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
