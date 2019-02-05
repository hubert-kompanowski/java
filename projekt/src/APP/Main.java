package APP;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static AnchorPane root = new AnchorPane();

    private static List<Pane> grid = new ArrayList<Pane>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        grid.add(FXMLLoader.load(getClass().getResource("../GUI/account.fxml")));

        root.getChildren().setAll(grid.get(0));
        Scene scene = new Scene(root, 1100, 750);

        primaryStage.setTitle("menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
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