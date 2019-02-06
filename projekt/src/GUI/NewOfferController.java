package GUI;

import APP.Main;
import DataOffer.Data;
import DataOffer.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewOfferController  implements Initializable {

    @FXML Text errRoom;
    @FXML Text errSurf;
    @FXML Text errPriz;
    @FXML Text errPhone;
    @FXML Text errEmail;
    @FXML Text err;
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

    private List<File> fileList;
    private ArrayList<String> ListPath = new ArrayList<>();
    private Stage primaryStage;


    @FXML
    public void prev(){
        Main.setScene(1);
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
        err.setText(" ");
        errPhone.setText("");
        errEmail.setText("");
        errRoom.setText("");
        errSurf.setText("");
        errPriz.setText("");

        if(name.getText()==null|| phone.getText()==null||  email.getText()==null||
                address.getText()==null||  district.getText()==null||  heating.getText()==null||
                roomsNumber.getText()==null||  building.getText()==null||  surface.getText()==null||
                prize.getText()==null|| ListPath.size()<1){

            err.setText("Proszę podać wszystkie dane");
            err.setStyle("-fx-font-size: 30px; -fx-color: red");

        } else if(!phone.getText().matches("(\\+\\d{2})?\\d{9}")) {
            errPhone.setText("Błędna wartość");
            errPhone.setStyle("-fx-color: red");

        } else if(!validate(email.getText())){
            errEmail.setText("Błędna wartość");
            errEmail.setStyle("-fx-color: red");

        } else if(!roomsNumber.getText().matches("\\d{0,2}")){
            errRoom.setText("Błędna wartość");
            errRoom.setStyle("-fx-color: red");

        } else if(!surface.getText().matches("\\d{1,5}.?\\d{0,2}")){
            errSurf.setText("Błędna wartość");
            errSurf.setStyle("-fx-color: red");

        } else if(!prize.getText().matches("\\d{1,10}.?\\d{0,2}")) {
            errPriz.setText("Błędna wartość");
            errPriz.setStyle("-fx-color: red");
        }else {

            try {
                Data dt = new Data(name.getText(), phone.getText(), email.getText(),
                        address.getText(), district.getText(), heating.getText(),
                        roomsNumber.getText(), building.getText(), surface.getText(),
                        prize.getText(), ListPath);
                DataBase db = new DataBase();

                db.init();
                db.run(dt);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Main.setScene(1);
        }
    }


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(IndexController.name2 != null && IndexController.email2 != null && IndexController.phone2 != null){
            name.setText(IndexController.name2);
            email.setText(IndexController.email2);
            phone.setText(IndexController.phone2);
            email.setDisable(true);
        }
    }
}
