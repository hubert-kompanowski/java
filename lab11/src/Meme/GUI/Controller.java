package Meme.GUI;


import Meme.Serialize.Data;
import Meme.Serialize.Make;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML private ImageView FotoView;
    private File[] ListFile;
    private ArrayList<Image> ListImage = new ArrayList<>();
    private ArrayList<String> ListPath = new ArrayList<>();
    private Integer actualImage = 0;
    private File selectedFile;
    private Stage primaryStage;
    @FXML private TextField name;
    @FXML private TextField UpString;
    @FXML private TextField DownString;



    @FXML public void next(){
        if(actualImage<ListImage.size()-1) {
            actualImage++;
            FotoView.setImage(ListImage.get(actualImage));
        }
    }

    @FXML public void prev(){
        if(actualImage>0) {
            actualImage--;
            FotoView.setImage(ListImage.get(actualImage));
        }
    }

    @FXML public void addPic(){
        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(primaryStage);
        ListPath.add(selectedFile.toURI().toString());
        ListImage.add(new Image(selectedFile.toURI().toString()));
        actualImage = ListImage.size()-1;
        FotoView.setImage(ListImage.get(actualImage));
    }

    @FXML public void enter(){
        String path = ListPath.get(actualImage);


        Data data = new Data(path, name.getText(), UpString.getText(), DownString.getText());

        //Make.run(data);
        try {
            Meme.Client.Start.run(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File repo = new File("src/Meme/Gallery");
        ListFile  = repo.listFiles();

        for(File f : ListFile){
            ListPath.add(f.getPath());
            ListImage.add(new Image(f.toURI().toString()));
        }
        FotoView.setImage(ListImage.get(actualImage));
    }
}