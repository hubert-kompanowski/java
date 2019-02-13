package GUI;

import APP.Main;
import DataOffer.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Klasa odpowiadająca za kontrolę pól konta użytkownika
 */

public class AccountController implements Initializable {

    @FXML ListView offers;
    @FXML Text data;

    private int sceneIndex=2;


    /**
     * Ustawianie poprzedniej sceny
     */
    @FXML
    public void prev(){
        IndexController.name2 = "";
        IndexController.phone2 = "";
        IndexController.email2 = "";
        try {
            Main.removeLastScene();
            Main.removeLastScene();
            Main.addScene(FXMLLoader.load(getClass().getResource("index.fxml")));
            Main.setScene(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dodanie nowej ofrty
     * @throws IOException
     */
    @FXML public void newOffer() throws IOException {
        System.out.println("newOffer");
        Main.addScene(FXMLLoader.load(getClass().getResource("newOffer.fxml")));
        Main.setScene(sceneIndex);
        Main.removeLastScene();
    }

    /**
     * Inicjalizacja przed wyświetleniem sceny
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();
        db.init();
        data.setText("Witaj "+IndexController.name2+"\n\n" +
                "Twój numer telefonu:\n"+IndexController.phone2+"\n\n" +
                "Twój adres email:\n" + IndexController.email2);

        ArrayList<String> al = db.allOffers(IndexController.email2);

        for(String s : al){
            offers.getItems().add(s);
        }
    }
}