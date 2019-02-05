package GUI;

import APP.Main;
import DataOffer.Data;
import DataOffer.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FindController implements Initializable {

    @FXML Text text;
    @FXML TextArea description;
    @FXML TextArea title;
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

    @FXML
    public void prev(){
        Main.setScene(0);
    }


    @FXML
    public void find() throws IOException {
        DataBase db = new DataBase();

        db.init();

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

    private void disp() {
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
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init controller");
    }

    public void prevOffer() {
        if(actual == 0){
            actual = size-1;
        } else {
            actual--;
        }
        actualPhoto = 0;
        disp();
        dispPhoto();

    }

    public void nextOffer() {
        if(actual == size-1){
            actual = 0;
        } else {
            actual++;
        }
        actualPhoto = 0;
        disp();
        dispPhoto();
    }

    public void prevPhoto() {

        if(actualPhoto <= 0){
            actualPhoto = sizePhoto-1;
        } else {
            actualPhoto--;
        }

        String im = "file:src/DataOffer/tmp/t" + actualPhoto + ".jpg";
        Image i = new Image(im);
        imageView.setImage(i);
    }

    public void nextPhoto() {
        if(actualPhoto == sizePhoto-1){
            actualPhoto = 0;
        } else {
            actualPhoto++;
        }

        String im = "file:src/DataOffer/tmp/t" + actualPhoto + ".jpg";
        Image i = new Image(im);
        imageView.setImage(i);
    }
}

