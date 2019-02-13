package DataOffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *  Klasa przechowująca dane o ogłoszeniu
 */

public class Data {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String district;
    private String heating;
    private String roomsNumber;
    private String building;
    private String surface;
    private String prize;
    private ArrayList<InputStream> ISList = new ArrayList<>();

    public void setISList(ArrayList<InputStream> ISList) {
        this.ISList = ISList;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getDistrict() {
        return district;
    }
    public String getHeating() {
        return heating;
    }
    public String getRoomsNumber() {
        return roomsNumber;
    }
    public String getBuilding() {
        return building;
    }
    public String getSurface() {
        return surface;
    }
    public String getPrize() {
        return prize;
    }
    public ArrayList<InputStream> getISList() {
        return ISList;
    }

    /**
     * Konstruktor przyjmujący wszystkie dane z ogłoszenia
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param district
     * @param heating
     * @param roomsNumber
     * @param building
     * @param surface
     * @param prize
     * @param list
     * @throws FileNotFoundException
     */
    public Data(String name, String phone, String email, String address, String district,
                String heating, String roomsNumber, String building, String surface,
                String prize, ArrayList<String> list) throws FileNotFoundException {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.district = district;
        this.heating = heating;
        this.roomsNumber = roomsNumber;
        this.building = building;
        this.surface = surface;
        this.prize = prize;

        if(list != null) {
            for (String s : list) {

                ISList.add(new FileInputStream(new File(s)));
            }
        }
    }

}
