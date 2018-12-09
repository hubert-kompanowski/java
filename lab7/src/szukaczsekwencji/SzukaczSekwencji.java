package szukaczsekwencji;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SzukaczSekwencji extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox siatkaPionowaVBox = new VBox(5);
        siatkaPionowaVBox.setPadding(new Insets(5));
        Label sekwencjaLabel = new Label();
        sekwencjaLabel.setText("Sekwencja");
        siatkaPionowaVBox.getChildren().add(sekwencjaLabel);


        TextArea sekwencjaTextArea = new TextArea();
        sekwencjaTextArea.setWrapText(true);
        siatkaPionowaVBox.getChildren().add(sekwencjaTextArea);


        HBox szukanaHBox = new HBox(5);
        Label szukanaLabel = new Label("Szukaj: ");
        TextField szukanaTextField = new TextField();
        szukanaTextField.setMinWidth(235);
        szukanaHBox.getChildren().add(szukanaLabel);
        szukanaHBox.getChildren().add(szukanaTextField);
        siatkaPionowaVBox.getChildren().add(szukanaHBox);

        Label wynikLabel = new Label("Wynik:");
        siatkaPionowaVBox.getChildren().add(wynikLabel);

        TextArea wynikTextArea = new TextArea();
        wynikTextArea.setEditable(false);
        wynikTextArea.setWrapText(true);
        siatkaPionowaVBox.getChildren().add(wynikTextArea);

        HBox buttonsHBox = new HBox(5);
        Button szukajButton = new Button("Szukaj");
        szukajButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    wynikTextArea.setText(
                            // Wywołujemy metode statyczną szukaj z klasy Szukacz
                            Szukacz.szukaj(sekwencjaTextArea.getText(),
                                    szukanaTextField.getText()));
                }
        });

        // Teraz przycisk "Zakończ"
        Button zakonczButton = new Button("Zakończ");
        // Akcja dla przycisku "Zakończ"
        zakonczButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Tak się powinno zakańczać działanie aplikacji w JavaFX
                Platform.exit();
            }
        });

        // Dodawanie przycisków do HBox-u, metoda addAll pozwala dodawać
        // na raz wiele elementów
        buttonsHBox.getChildren().addAll(szukajButton,  zakonczButton);
        // Dodajemy buttonsHBox do siatkaPionowaVBox
        siatkaPionowaVBox.getChildren().add(buttonsHBox);
        // Tworzymy Scene
        Scene scene = new Scene(siatkaPionowaVBox, 300, 250);
        // Tutuł okna
        primaryStage.setTitle("Szukanie sekwencji");
        // Ustawianie sceny
        primaryStage.setScene(scene);
        // Pokaż
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}