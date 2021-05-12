package process.controller.graphic;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import process.controller.Main;
import program.classes.Const;
import program.classes.Employee;
import program.helperClasses.InitializeGraphicArrows;

import java.util.Vector;

public class GraphicMenu {

    @FXML
    private ComboBox<String> menuChoiceGraphic;

    @FXML
    private AreaChart<String, Double> areaChartOnRequestFromCountry;

    @FXML
    void initialize() {
        ObservableList<String> itemX = FXCollections.observableArrayList(
                "Количество заказов в городе",
                "Количество заказов встране");
        menuChoiceGraphic.setItems(itemX);

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Main.getMethod().writeLine(Const.INITIALIZE_GRAPHIC_DEPENDENCE_COST_ON_CITY_BY_ID_EMPLOYEE);
        Main.getMethod().writeLine(String.valueOf(Employee.CURRENT_ID));
        Gson gson = new Gson();
        Vector<InitializeGraphicArrows> vectorGraphic = new Vector<>();
        while (true){
            String strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                InitializeGraphicArrows graphicArrows = gson.fromJson(strG, InitializeGraphicArrows.class);
                vectorGraphic.add(new InitializeGraphicArrows(graphicArrows.getxDouble(), graphicArrows.getyString()));
            }
        }
        for (var v: vectorGraphic) {
            series.getData().add(new XYChart.Data<>(v.getyString(), v.getxDouble()));
        }
        series.setName("test");
        areaChartOnRequestFromCountry.getData().setAll(series);

    }

}
