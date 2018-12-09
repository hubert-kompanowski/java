package histogram;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    public static int intervals = 10, startInt =0, endInt=50;
    public static String str;
    private int i=1;
    public static List<Integer> list = new ArrayList<Integer>();
    public static List<Integer> listOfHist = new ArrayList<Integer>();


    @FXML
    public  TextField data;
    @FXML
    public  TextField numberOfIntervals;
    @FXML
    public  TextField start;
    @FXML
    public  TextField end;

    public void drawHist(ActionEvent event) throws IOException {


        if(isNumeric(numberOfIntervals.getText())) {
            intervals = Integer.parseInt(numberOfIntervals.getText());
        }

        if(isNumeric(start.getText())) {
            startInt = Integer.parseInt(start.getText());
        }

        if(isNumeric(end.getText())) {
            endInt = Integer.parseInt(end.getText())+1;
        }

        str = data.getText();

        String[] pomiedzy = str.split(" ");

        for(String s : pomiedzy){
            list.add(Integer.parseInt(s));
        }


        listOfHist.clear();
        fillListOfHist();

        HistogramApp.addScene(FXMLLoader.load(getClass().getResource("hist.fxml")));
        HistogramApp.setScene(i);
        HistogramApp.removeLastScene();


    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


    private void fillListOfHist(){

        int tmp;
        for(int i=startInt, j=1; j<=intervals; i+=Math.ceil((endInt-startInt+1)/intervals), j++){

            tmp=0;
            for(int x : list){
                if(x>=i && x<i+Math.ceil((endInt-startInt+1)/intervals)){
                    tmp++;
                }
            }
            listOfHist.add(tmp);
        }
    }
}



//     10      60           /   10

//
