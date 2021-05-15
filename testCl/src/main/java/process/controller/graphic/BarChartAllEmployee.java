package process.controller.graphic;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import process.controller.Main;
import program.classes.Const;
import program.classes.Employee;
import program.helperClasses.InitializeGraphicArrows;

import java.util.Vector;

public class BarChartAllEmployee {
    @FXML
    private BarChart<String, Double> barChartIdEmployeeCostInMonth;

    @FXML
    void comeBack(ActionEvent event) {
        Main main = new Main();
        main.getWindow("/fxml/admin/adminMenu.fxml", "Меню администратора");
    }

    @FXML
    void initialize() {
        Main.getMethod().writeLine(Const.INITIALIZE_DIAGRAM_COST_AND_MONTH_ALL_EMPLOYEE);
        Vector<InitializeGraphicArrows> vectorDiagram = new Vector<>();
        Gson gson = new Gson();
        while (true) {
            String strG = Main.getMethod().readLine();
            if (strG.equals("0"))
                break;
            else {
                InitializeGraphicArrows graphicArrows = gson.fromJson(strG, InitializeGraphicArrows.class);

                vectorDiagram.add(new InitializeGraphicArrows(graphicArrows.getxDouble(), graphicArrows.getyString()));
            }
        }
        XYChart.Series<String, Double> xyChart = new XYChart.Series<>();
        for (var v : vectorDiagram)
            xyChart.getData().add(new XYChart.Data<>(v.getyString(), v.getxDouble()));

        barChartIdEmployeeCostInMonth.setBarGap(20);
//        barChartIdEmployeeCostInMonth.setCategoryGap(10);
//        barChartIdEmployeeCostInMonth.setMaxWidth(50);
//        barChartIdEmployeeCostInMonth.setMinWidth(1);
        barChartIdEmployeeCostInMonth.getData().setAll(xyChart);

    }
}
