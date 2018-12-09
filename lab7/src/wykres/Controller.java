package wykres;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML private LineChart<?,?> LineChart;
    @FXML private TextField rangeStartTextField;
    @FXML private TextField rangeEndTextField;
    @FXML private TextField stalePrzyX;
    private int rangeStart;
    private int rangeEnd;

    public static List<Integer> list = new ArrayList<Integer>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        LineChart.getData().clear();
    }

    @FXML public void draw(){
        XYChart.Series series= new XYChart.Series();

        list.clear();


        String str = stalePrzyX.getText();
        String[] pomiedzy = str.split(" ");
        for(String s : pomiedzy){
            list.add(Integer.parseInt(s));
        }

        rangeStart = Integer.parseInt(rangeStartTextField.getText());
        rangeEnd = Integer.parseInt(rangeEndTextField.getText());

        for (int i = rangeStart; i <= rangeEnd; i++) {
            series.getData().add(new XYChart.Data(String.valueOf(i), funkcja(i)));
        }
        LineChart.getData().addAll(series);
    }

    int funkcja(int x){
        int result = 0;
        int pot = 0;
        for(int i : list){
            result += i*Math.pow(x,pot);
            pot++;
        }
        return result;
    }
}