package sample;

import com.sun.deploy.util.FXLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static AnchorPane root;
    private static List<GridPane> grid = new ArrayList<GridPane>();

    private static int idx_cur = 0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        root =  FXMLLoader.load(getClass().getResource("sample.fxml"));

        grid.add(FXMLLoader.load(getClass().getResource("sample2.fxml")));
        grid.add(FXMLLoader.load(getClass().getResource("histogram.fxml")));


        root.getChildren().add(grid.get(0));
        Scene scene = new Scene(root, 300, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void set_pane(int index){
        root.getChildren().remove(grid.get(idx_cur));
        root.getChildren().add(grid.get(index));
        idx_cur = index;
    }
}