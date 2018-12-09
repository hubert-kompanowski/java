package javafxpierwszaaplikacja;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JavaFXPierwszaAplikacja extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        Circle circle = new Circle(0, 0, 30, Color.RED);

        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                circle.setFill(Color.GREEN);


            }
        });



        StackPane root = new StackPane();
        root.getChildren().add(circle);
        root.getChildren().add(btn);


        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}