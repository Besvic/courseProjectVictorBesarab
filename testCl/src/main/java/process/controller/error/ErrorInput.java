package process.controller.error;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;





import java.io.IOException;


public class ErrorInput {
    /*@FXML
    private Button buttonClose;

    @FXML
    private Label demoLabel;


    @FXML
    void close(ActionEvent event) {
        buttonClose.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {

    }*/

    public void show(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(null);
        alert.setContentText("Проверьте корректность данных");
        alert.showAndWait();

        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/error/errorInputData.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaxHeight(200);
        stage.setMaxWidth(300);
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.showAndWait();*/
    }

    public void showErrorProcess(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Операция не завершена!");
        alert.showAndWait();
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/error/errorProcess.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaxHeight(200);
        stage.setMaxWidth(300);
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.showAndWait();*/
    }

    public void showCompleteSuccessful(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText("Операция успешно завершена!");
        alert.showAndWait();
       /* FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/error/completeSuccessful.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaxHeight(200);
        stage.setMaxWidth(300);
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.showAndWait();*/
    }
    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
