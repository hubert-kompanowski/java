package GUI;

import APP.Main;
import DataOffer.Data;
import DataOffer.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class NewOfferController {

    private List<File> fileList;
    private ArrayList<String> ListPath = new ArrayList<>();
    private Stage primaryStage;

    @FXML TextField name;
    @FXML TextField phone;
    @FXML TextField email;
    @FXML TextField address;
    @FXML TextField district;
    @FXML TextField heating;
    @FXML TextField roomsNumber;
    @FXML TextField building;
    @FXML TextField surface;
    @FXML TextField prize;



    @FXML
    public void prev(){
        Main.setScene(0);
    }


    @FXML
    public void addPic(){
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images","*.IMAGE", "*.jpg","*.gif","*.png");

        fc.getExtensionFilters().add(extFilter);
        fileList = fc.showOpenMultipleDialog(primaryStage);
        for (File f : fileList) {
            ListPath.add(f.getAbsolutePath());
        }

        System.out.println("addPic");
        System.out.println(ListPath.size());
    }

    @FXML
    public void addOffer(){

        try {
            Data dt = new Data(name.getText(), phone.getText(), email.getText(),
                    address.getText(), district.getText(), heating.getText(),
                    roomsNumber.getText(), building.getText(), surface.getText(),
                    prize.getText(), ListPath);
            DataBase db = new DataBase();

            db.init();
            db.run(dt);



        } catch ( FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
