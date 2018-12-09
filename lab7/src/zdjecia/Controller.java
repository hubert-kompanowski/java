package zdjecia;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ListView ListFile;
    @FXML private Button ButtFoto;
    @FXML private ImageView FotoView;
    private Stage primaryStage;
    private File SelectedDir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        SelectedDir = directoryChooser.showDialog(primaryStage);
        addFotoFromDir();
    }

    @FXML public void show(){
        Image image = new Image(new File(ListFile.getSelectionModel().getSelectedItem().toString()).toURI().toString());
        FotoView.setImage(image);

    }

    private void addFotoFromDir() {
        ArrayList<File> listOfFiles = new ArrayList<>(Arrays.asList(SelectedDir.listFiles()));
        for (final File f : listOfFiles) {
            ListFile.getItems().add(f.getPath());
        }
    }

}
