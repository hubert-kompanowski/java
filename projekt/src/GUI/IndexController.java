package GUI;

import DataOffer.DataBase;
import javafx.fxml.FXML;
import APP.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexController implements Initializable {

    @FXML Text textRej;
    @FXML Text textLog;
    @FXML TextField emailReg;
    @FXML PasswordField passwordReg;
    @FXML TextField nameReg;
    @FXML TextField phoneReg;
    @FXML TextField email;
    @FXML PasswordField password;

    private int sceneIndex=1;
    static public String name2;
    static public String email2;
    static public String phone2;


    @FXML public void find() throws IOException {
        System.out.println("find");
        Main.addScene(FXMLLoader.load(getClass().getResource("find.fxml")));
        Main.setScene(sceneIndex);
        Main.removeLastScene();
    }



    @FXML public void logIn() {

        DataBase db = new DataBase();
        db.init();

        try {
            ArrayList<String> tmp = db.login(email.getText(), password.getText());
            if(tmp != null){
                email2 = email.getText();
                name2 = tmp.get(0);
                phone2 = tmp.get(1);
                Main.addScene(FXMLLoader.load(getClass().getResource("account.fxml")));
                Main.setScene(sceneIndex);
                //Main.removeLastScene();
            } else {
                textLog.setText("Błędny login lub hasło");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void register() {

        if(!validate(emailReg.getText())){
            textRej.setText("Błędny email");
        }else if(!phoneReg.getText().matches("(\\+\\d{2})?\\d{9}")) {
            textRej.setText("Błędny numer telefonu");
        }else {
            DataBase db = new DataBase();
            db.init();
            boolean flag = true;
            for(String s : db.allLogins()){
                if(s.equals(emailReg.getText())){
                    flag = false;
                }
            }
            if(flag) {
                db.register(emailReg.getText(), passwordReg.getText(), nameReg.getText(), phoneReg.getText());
                emailReg.setText("");
                passwordReg.setText("");
                nameReg.setText("");
                phoneReg.setText("");
                textRej.setText("Pomyślna rejestracja!");
            }else{
                textRej.setText("Podany Email jest zajęty");

            }
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
        email.setText("");
        password.setText("");
    }
}
