package Ludzie;

public class Person {
    public Person(int id, String firstName, String secondName, String address, String pesel, String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    private String firstName;
    private String secondName;
    private String address;
    private String pesel;
    private String phoneNumber;
    private int id;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPESEL() {
        return pesel;
    }
    public void setPESEL(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
