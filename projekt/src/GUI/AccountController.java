package GUI;

import APP.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class AccountController {

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
