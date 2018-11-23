package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public TextField x0, x1, x2, x3, x4, start, end, step;


    public void draw(ActionEvent actionEvent) throws IOException {

        Group g = new Group();

        Scene scene = new Scene(g, 800, 600);

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("sample.fxml"));
        stage.setScene(new Scene(root, 600, 475));
        stage.show();

        Parent root2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));

        scene.setRoot(root2);

    }
}

