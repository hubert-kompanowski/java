package histogram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class Hist implements Initializable {

    @FXML
    private BarChart<String,Integer> HistogramChart;

    public void prev(ActionEvent event){

        HistogramApp.setScene(0);


        Menu.list.clear();
        Menu.listOfHist.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int p,k;
        String s;
        XYChart.Series set = new XYChart.Series();
        int add = (int)Math.ceil((Menu.endInt-Menu.startInt+1)/Menu.intervals);
        p = Menu.startInt;
        k = Menu.startInt + add;
        for(int i=0; i < Menu.intervals; i++){

            s = String.valueOf(p);
            s+= " - ";
            s+= String.valueOf(k);
            set.getData().add(new XYChart.Data(s,Menu.listOfHist.get(i)));
            p+=add;
            k+=add;
        }
        HistogramChart.getData().addAll(set);

    }
}
