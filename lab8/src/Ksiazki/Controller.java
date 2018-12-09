package Ksiazki;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

    @FXML private ListView<String> ResultList;
    @FXML private TextField Author;
    @FXML private TextField ISBN;
    @FXML private TextField AddISBN;
    @FXML private TextField AddTitle;
    @FXML private TextField AddAuthor;
    @FXML private TextField AddYear;




    @FXML public void showAll(){
        ResultList.getItems().clear();
        Main.db.showAllBooks(ResultList, 1, "");
    }

    @FXML public void showByAuthor(){
        ResultList.getItems().clear();
        Main.db.showAllBooks(ResultList, 2, Author.getText());
    }


    @FXML public void showByISBN(){
        ResultList.getItems().clear();
        Main.db.showAllBooks(ResultList, 3, ISBN.getText());
    }

    @FXML public void addBook(){
        ResultList.getItems().clear();
        Main.db.showAllBooks(ResultList, 4, "'"+AddISBN.getText()+"', '"+AddTitle.getText()+"', '"+AddAuthor.getText()+"', '"+AddYear.getText()+"'");

    }


}
