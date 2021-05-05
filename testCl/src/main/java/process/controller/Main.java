package process.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Methods;

import java.io.IOException;

public class Main extends Application{

    public static Stage primaryStage;
    public static Methods methods = null;

    public static Methods getMethod(){
        if (methods == null) {
            methods = new Methods("127.0.0.1", 7776);
            System.out.println("Подключение к серверу прошло успешно.");
        }
        return methods;
    }

    public void start(Stage primary) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start/mainWindow.fxml"));
        primary.setTitle("Вход/Авторизация");
        primary.setScene(new Scene(root));

        primaryStage = primary;
        primaryStage.setMaxHeight(900);
        primaryStage.setMaxWidth(800);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);

        /*try ( Methods method = new Methods("127.0.0.1", 7777)){
        System.out.println("Подключение к серверу прошло успешно.");
            //getMethod();
           // methods = method;
            primaryStage.show();
                methods.writeLine("12");
                System.out.println(methods.readLine());
            //System.out.println(methods.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void getWindow(String FXMLString, String title){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(FXMLString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.setTitle(title);
        Main.primaryStage.show();
    }
}




/*public class controller.ControllerAuthorise extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        Group group = new Group();

        Scene scene = new Scene(group,600,400);

        Parent content = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane root = new BorderPane();

        root.setCenter(content);

        group.getChildren().add(root);


        //Button button = new Button("start");
        //stage.setTitle("Consulting company");
        stage.setScene(scene);
        stage.show();
    }
}*/




