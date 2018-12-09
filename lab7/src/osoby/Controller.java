package osoby;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller{
    @FXML private TextField FirstName;
    @FXML private TextField SecondName;
    @FXML private TextField Address;
    @FXML private TextField ZipCode;
    @FXML private TextField PhoneNumber;

    @FXML private TextField DelByID;
    @FXML private TextField ChangeID;
    @FXML private TextField ChangeNumOfCol;
    @FXML private TextField ChangeData;

    @FXML private ListView<String> ListFirstName;
    @FXML private ListView<String> ListSecondName;
    @FXML private ListView<String> ListAddress;
    @FXML private ListView<String> ListZipCode;
    @FXML private ListView<String> ListPhoneNumber;
    @FXML private ListView<Integer> ListID;

    People people = new People();

    private int id = 1;


    @FXML
    public void add(){
        people.peopleList.add(new Person(id, FirstName.getText(), SecondName.getText(), Address.getText(), ZipCode.getText(), PhoneNumber.getText()));
        ListFirstName.getItems().add(FirstName.getText());
        ListSecondName.getItems().add(SecondName.getText());
        ListAddress.getItems().add(Address.getText());
        ListZipCode.getItems().add(ZipCode.getText());
        ListPhoneNumber.getItems().add(PhoneNumber.getText());
        ListID.getItems().add(id);
        id++;
    }

    @FXML
    public void delete(){
        int index = -1;
        for(int p = 0; p<people.peopleList.size(); p++){
            if(people.peopleList.get(p).getId() == Integer.parseInt(DelByID.getText())){
                index = p;
            }
        }
        if(index != -1) {
            people.peopleList.remove(index);
            ListID.getItems().remove(index);
            ListFirstName.getItems().remove(index);
            ListSecondName.getItems().remove(index);
            ListAddress.getItems().remove(index);
            ListZipCode.getItems().remove(index);
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
                    break;
                }
                case 2:
                {
                    ListSecondName.getItems().add(index, ChangeData.getText());
                    people.peopleList.get(index).setSecondName(ChangeData.getText());
                    break;
                }
                case 3:
                {
                    ListAddress.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setAddress(ChangeData.getText());
                    break;
                }
                case 4:
                {
                    ListZipCode.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setZipCode(ChangeData.getText());
                    break;
                }
                case 5:
                {
                    ListPhoneNumber.getItems().set(index, ChangeData.getText());
                    people.peopleList.get(index).setPhoneNumber(ChangeData.getText());
                    break;
                }
            }
        }
        ChangeID.clear();
        ChangeData.clear();
        ChangeNumOfCol.clear();
    }


}
