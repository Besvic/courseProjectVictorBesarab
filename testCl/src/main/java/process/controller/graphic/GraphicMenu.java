package process.controller.graphic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

public class GraphicMenu {

    @FXML
    private ComboBox<String> menuChoiceGraphic;

    @FXML
    private AreaChart<String, Integer> areaChartOnRequestFromCountry;

    @FXML
    void initialize() {
        ObservableList<String> itemX = FXCollections.observableArrayList(
                "Количество заказов в городе",
                "Количество заказов встране");
        menuChoiceGraphic.setItems(itemX);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("12", 15));
        series.getData().add(new XYChart.Data<>("13", 25));
        series.setName("test");
        areaChartOnRequestFromCountry.getData().setAll(series);

    }

}
