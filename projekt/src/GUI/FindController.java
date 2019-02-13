package GUI;

import APP.Main;
import DataOffer.Data;
import DataOffer.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Klasa odpowiadająca za kontrolę sceny szukania ofert
 */

public class FindController implements Initializable {


    @FXML TextField tr;
    @FXML TextField pseu;
    @FXML ListView<String> comments = new ListView<String>();
    @FXML Text text;
    @FXML Text description;
    @FXML ImageView imageView = new ImageView();
    @FXML TextField district;
    @FXML TextField roomsNumber;
    @FXML TextField heating;
    @FXML TextField building;
    @FXML TextField fromPrize;
    @FXML TextField toPrize;
    @FXML TextField fromSurface;
    @FXML TextField toSurface;

    private ArrayList<Data> listData = null;
    private int actual = 0;
    private int actualPhoto = 0;
    private int size = 0;
    private int sizePhoto = 0;

    public DataBase db = new DataBase();

    /**
     * Powrót do poprzedniej sceny
     */

    @FXML
    public void prev(){
        Main.setScene(0);
    }

    /**
     * Wyszukiwanie ofert pasujących do danych z formularza
     * @throws IOException
     */
    @FXML
    public void find() throws IOException {
        listData =  db.search(district.getText(), roomsNumber.getText(), heating.getText(),
                building.getText(), fromPrize.getText(), toPrize.getText(),
                fromSurface.getText(),toSurface.getText());
        size = listData.size();

        if (size == 0){
            description.setText("Brak wyników");
            description.setStyle("-fx-font-size: 30px");
            text.setText("Spróbuj jeszcze raz\b");
            text.setStyle("-fx-font-size: 30px");

        } else {
            disp();
            dispPhoto();
        }
    }

    /**
     * Wyświetlanie zdjęć z oferty
     */

    private void dispPhoto()  {
        BufferedImage img = null;
        try {
            for(int i=0; i<listData.get(actual).getISList().size(); i++){
                InputStream is = listData.get(actual).getISList().get(i);
                is.reset();
                img = ImageIO.read(is);

                File f = new File("src/DataOffer/tmp/t"+i+".jpg");
                ImageIO.write(img, "jpg", f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String im = "file:src/DataOffer/tmp/t" + actualPhoto + ".jpg";
        Image i = new Image(im);
        imageView.setImage(i);
    }

    /**
     * Wyświetlanie danych z oferty
     */
    private void disp() {
        text.setText("Oferta "+(actual+1)+" z "+ size);
        text.setStyle("-fx-font-size: 30px");
        sizePhoto = listData.get(actual).getISList().size();

        description.setText("WŁAŚCICIEL:\n" +
                listData.get(actual).getName()+ "\n" +
                listData.get(actual).getPhone()+ "\n" +
                listData.get(actual).getEmail()+ "\n\n" +
                "OPIS:\n" +
                "Adres: " + listData.get(actual).getAddress()+ "\n" +
                "           " + listData.get(actual).getDistrict()+ "\n" +
                "Ogrzewanie: " + listData.get(actual).getHeating()+ "\n" +
                "Liczba pokoi: " + listData.get(actual).getRoomsNumber()+ "\n" +
                "Rodzaj zabudowy: " + listData.get(actual).getBuilding()+ "\n" +
                "Powierzcnia: " + listData.get(actual).getSurface()+ " m2\n" +
                "Cena: " + listData.get(actual).getPrize()+ " zł\n"
        );
        description.setStyle("-fx-font-size: 18px; -fx-font-family: 'Bitstream Charter'");

        for(String s : db.getComments(listData.get(actual).getEmail(),listData.get(actual).getAddress(),
                listData.get(actual).getDistrict(), listData.get(actual).getHeating(),listData.get(actual).getRoomsNumber(),
                listData.get(actual).getBuilding(), listData.get(actual).getSurface(), listData.get(actual).getPrize())){

            comments.getItems().add(s);
        }
    }

    /**
     * Inicjalizacja sceny przed uruchomieniem
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db.init();
        imageView.setStyle("-fx-alignment: center");
        Image i = new Image("file:src/DataOffer/tmp/dom.jpg");
        imageView.setImage(i);
    }


    /**
     * Przełączenie poprzedniej oferty
     */
    public void prevOffer() {
        comments.getItems().clear();
        if(size != 0) {
            if (actual == 0) {
                actual = size - 1;
            } else {
                actual--;
            }
            actualPhoto = 0;
            disp();
            dispPhoto();
        }
    }

    /**
     * Przełączenie następnej oferty
     */
    public void nextOffer() {
        comments.getItems().clear();
        if(size != 0) {
            if (actual == size - 1) {
                actual = 0;
            } else {
                actual++;
            }
            actualPhoto = 0;
            disp();
            dispPhoto();
        }
    }

    /**
     * Przełączenie poprzedniego zdjęcia
     */
    public void prevPhoto() {
        if(size != 0) {
            if (actualPhoto <= 0) {
                actualPhoto = sizePhoto - 1;
            } else {
                actualPhoto--;
            }
            String im = "file:src/DataOffer/tmp/t" + actualPhoto + ".jpg";
            Image i = new Image(im);
            imageView.setImage(i);
        }
    }

    /**
     * Przełącznie następnego zdjęcia
     */
    public void nextPhoto() {
        if(size != 0) {
            if (actualPhoto == sizePhoto - 1) {
                actualPhoto = 0;
            } else {
                actualPhoto++;
            }
            String im = "file:src/DataOffer/tmp/t" + actualPhoto + ".jpg";
            Image i = new Image(im);
            imageView.setImage(i);
        }
    }

    /**
     * Dodawanie komentarza
     */
    public void addCom() {
        db.addComm(comments.getItems().size(),pseu.getText()+": "+tr.getText(),listData.get(actual).getEmail(),listData.get(actual).getAddress(),
                listData.get(actual).getDistrict(), listData.get(actual).getHeating(),listData.get(actual).getRoomsNumber(),
                listData.get(actual).getBuilding(), listData.get(actual).getSurface(), listData.get(actual).getPrize());

        nextOffer();
        prevOffer();
    }
}

