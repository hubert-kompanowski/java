package Ludzie;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private TextField FirstName;
    @FXML private TextField SecondName;
    @FXML private TextField Address;
    @FXML private TextField PESEL;
    @FXML private TextField PhoneNumber;

    @FXML private TextField DelByID;
    @FXML private TextField ChangeID;
    @FXML private TextField ChangeNumOfCol;
    @FXML private TextField ChangeData;

    @FXML private ListView<String> ListFirstName;
    @FXML private ListView<String> ListSecondName;
    @FXML private ListView<String> ListAddress;
    @FXML private ListView<String> ListPESEL;
    @FXML private ListView<String> ListPhoneNumber;
    @FXML private ListView<Integer> ListID;

    People people = new People();




    @FXML
    public void add(){

        int id = ListID.getItems().get(ListID.getItems().size()-1)+1;

        people.peopleList.add(new Person(id, FirstName.getText(), SecondName.getText(), Address.getText(), PESEL.getText(), PhoneNumber.getText()));
        app.db.addPerson(id, FirstName.getText(), SecondName.getText(), Address.getText(), PESEL.getText(), PhoneNumber.getText());

        ListFirstName.getItems().add(FirstName.getText());
        ListSecondName.getItems().add(SecondName.getText());
        ListAddress.getItems().add(Address.getText());
        ListPESEL.getItems().add(PESEL.getText());
        ListPhoneNumber.getItems().add(PhoneNumber.getText());
        ListID.getItems().add(id);
    }

    @FXML
    public void delete(){
        int index = -1;
        for(int p = 0; p<people.peopleList.size(); p++){
            if(people.peopleList.get(p).getId() == Integer.parseInt(DelByID.getText())){
                index = p;
            }
        }

        app.db.deletePerson(people.peopleList.get(index).getPESEL());
        if(index != -1) {
            people.peopleList.remove(index);
            ListID.getItems().remove(index);
            ListFirstName.getItems().remove(index);
            ListSecondName.getItems().remove(index);
            ListAddress.getItems().remove(index);
            ListPESEL.getItems().remove(index);
            ListPhoneNumber.getItems().remove(index);
        }
        DelByID.clear();
    }

    @FXML
    public void change(){
        int index = -1;
        for(int p = 0; p<people.peopleList.size(); p++){
            if(people.peopleList.get(p).getId() == Integer.parseInt(ChangeID.getText())){
                index = p;
            }
        }
        if(index != -1) {

            switch (Integer.parseInt(ChangeNumOfCol.getText())){
                case 1:
                {
                    ListFirstName.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setFirstName(ChangeData.getText());
                    app.db.chandeAtribute(ChangeData.getText(), "first_name", String.valueOf(people.peopleList.get(index).getId()));
                    break;
                }
                case 2:
                {
                    ListSecondName.getItems().add(index, ChangeData.getText());
                    people.peopleList.get(index).setSecondName(ChangeData.getText());
                    app.db.chandeAtribute(ChangeData.getText(), "second_name", String.valueOf(index));
                    break;
                }
                case 3:
                {
                    ListAddress.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setAddress(ChangeData.getText());
                    app.db.chandeAtribute(ChangeData.getText(), "address", String.valueOf(index));
                    break;
                }
                case 4:
                {
                    ListPESEL.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setPESEL(ChangeData.getText());
                    app.db.chandeAtribute(ChangeData.getText(), "pesel", String.valueOf(index));
                    break;
                }
                case 5:
                {
                    ListPhoneNumber.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setPhoneNumber(ChangeData.getText());
                    app.db.chandeAtribute(ChangeData.getText(), "phone_number", String.valueOf(index));
                    break;
                }
            }
        }
        ChangeID.clear();
        ChangeData.clear();
        ChangeNumOfCol.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app.db.initFromDB(ListID, ListFirstName, ListSecondName, ListAddress, ListPESEL, ListPhoneNumber, people);
    }
}
