package histogram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HistogramApp extends Application {

    private static AnchorPane root = new AnchorPane();

    private static List<Pane> grid = new ArrayList<Pane>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        grid.add(FXMLLoader.load(getClass().getResource("menu.fxml")));

        root.getChildren().setAll(grid.get(0));
        Scene scene = new Scene(root, 400, 500);

        primaryStage.setTitle("menu");
        primaryStage.setScene(scene);
        primaryStage.show();

       // HistogramApp.setScene(1);

    }

    public static void main(String [] argv){
        launch(argv);
    }


    public static void removeLastScene(){
        grid.remove(grid.size()-1);
    }

    public static void setScene(int index){
        root.getChildren().setAll(grid.get(index));
    }

    public static void addScene(Pane p){
        grid.add(p);
    }

}
