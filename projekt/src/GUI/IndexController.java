package GUI;

import javafx.fxml.FXML;
import APP.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

public class IndexController {

    private int sceneIndex=1;






    @FXML public void find() throws IOException {
        System.out.println("find");

        Main.addScene(FXMLLoader.load(getClass().getResource("find.fxml")));
        Main.setScene(sceneIndex);
        Main.removeLastScene();

    }


    @FXML public void newOffer() throws IOException {
        System.out.println("newOffer");

        Main.addScene(FXMLLoader.load(getClass().getResource("newOffer.fxml")));
        Main.setScene(sceneIndex);
        Main.removeLastScene();
    }

}
