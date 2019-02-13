package DataOffer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Klasa odpowiadająca za połączenia z bazą danych
 */

public class DataBase {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Inicjalizacja połączenia z bazą danych
     */
    public void init(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/projekt",
                    "root","");
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dodawanie nowej oferty do bazy danych
     * @param dt
     */
    public void run(Data dt)  {
        try {
            ps = con.prepareStatement("insert into dane(name,phone,email,address,district,heating,roomsNumber,building,surface, prize," +
                    "f0,f1,f2,f3,f4,f5,f6,f7,f8,f9,k0,k1,k2,k3,k4,k5,k6,k7,k8,k9) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, dt.getName());
            ps.setString(2, dt.getPhone());
            ps.setString(3, dt.getEmail());
            ps.setString(4, dt.getAddress());
            ps.setString(5, dt.getDistrict());
            ps.setString(6, dt.getHeating());
            ps.setString(7, dt.getRoomsNumber());
            ps.setString(8, dt.getBuilding());
            ps.setString(9, dt.getSurface());
            ps.setString(10, dt.getPrize());

            int x = 11;
            for(InputStream i: dt.getISList()){
                ps.setBlob(x, i);
                x++;
            }
            for(int i = x; i<=30; i++){
                ps.setString(i, "");
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wyszukiwanie wszystkich komentarzy dotyczących danej oferty
     *
     * @param email
     * @param adr
     * @param dist
     * @param heat
     * @param room
     * @param buil
     * @param sur
     * @param pr
     * @return
     */

    public ArrayList<String> getComments(String email, String adr, String dist, String heat, String room, String buil, String sur, String pr){
        try {
            ps = con.prepareStatement("SELECT k0,k1,k2,k3,k4,k5,k6,k7,k8,k9 FROM dane WHERE district LIKE ? AND" +
                    " roomsNumber LIKE ? AND heating LIKE ? AND building LIKE ? AND prize = ? AND surface = ?" +
                    " AND email = ? AND address = ? ");

            setSQL(email, adr, dist, heat, room, buil, sur, pr);
            rs = ps.executeQuery();
            ArrayList<String> result = new ArrayList<>();

            if(rs.next()){
                for (int i = 1; i <= 10; i++) {
                    if(rs.getString(i).length()>1) {
                        result.add(rs.getString(i));
                    }
                }
            }
            return result;
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Dodanie nowego komentrza do podanej oferty
     * @param col
     * @param text
     * @param email
     * @param adr
     * @param dist
     * @param heat
     * @param room
     * @param buil
     * @param sur
     * @param pr
     */

    public void addComm(int col, String text, String email, String adr, String dist, String heat, String room, String buil, String sur, String pr){
        try {
            String k = "k"+col;
            ps = con.prepareStatement("UPDATE dane SET "+k+" = \""+text+"\" WHERE district LIKE ? AND " +
                    " roomsNumber LIKE ? AND heating LIKE ? AND building LIKE ? AND prize = ? AND surface = ? " +
                    " AND email LIKE ? AND address LIKE ? ");

            setSQL(email, adr, dist, heat, room, buil, sur, pr);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja pomocnicza do przygodtowania zapytania wysyłanego później do bazy danych
     * @param email
     * @param adr
     * @param dist
     * @param heat
     * @param room
     * @param buil
     * @param sur
     * @param pr
     * @throws SQLException
     */

    private void setSQL(String email, String adr, String dist, String heat, String room, String buil, String sur, String pr) throws SQLException {
        ps.setString(1, dist);
        ps.setString(2, room);
        ps.setString(3, heat);
        ps.setString(4, buil);
        ps.setString(5, pr);
        ps.setString(6, sur);
        ps.setString(7, email);
        ps.setString(8, adr);
    }

    /**
     * Wyszukiwanie ofert pasujących do danych z formularza
     * @param dis
     * @param room
     * @param heat
     * @param buil
     * @param fp
     * @param tp
     * @param fs
     * @param ts
     * @return Lista pasujących ofert
     */

    public ArrayList<Data> search(String dis, String room, String heat, String buil,
                                  String fp, String tp, String fs, String ts){
        try {
            ps = con.prepareStatement("SELECT * FROM dane WHERE district LIKE ? AND roomsNumber LIKE ? " +
                    " AND heating LIKE ? AND building LIKE ? AND prize >= ? AND prize <= ? AND " +
                    "surface >= ? AND  surface <= ?");

            if(!dis.equals("")) {
                ps.setString(1, dis);
            } else{
                ps.setString(1, "%");
            }

            if(!room.equals("")) {
                ps.setString(2, room);
            } else{
                ps.setString(2, "%");
            }

            if(!heat.equals("")) {
                ps.setString(3, heat);
            } else{
                ps.setString(3, "%");
            }

            if(!buil.equals("")) {
                ps.setString(4, buil);
            } else{
                ps.setString(4, "%");
            }

            if(!fp.equals("")) {
                ps.setDouble(5, Double.parseDouble(fp));
            } else{
                ps.setInt(5, 0);
            }

            if(!tp.equals("")) {
                ps.setDouble(6, Double.parseDouble(tp));
            } else{
                ps.setInt(6, 1000000);
            }

            if(!fs.equals("")) {
                ps.setDouble(7, Double.parseDouble(fs));
            } else{
                ps.setInt(7, 0);
            }

            if(!ts.equals("")) {
                ps.setDouble(8, Double.parseDouble(ts));
            } else{
                ps.setInt(8, 1000000);
            }
            rs = ps.executeQuery();

            ArrayList<Data> dataList = new ArrayList<>();

            while (rs.next()) {
                Data dt = new Data(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10), null);

                ArrayList<InputStream> is = new ArrayList<>();

                for(int i=11; i<=20; i++){
                    if(!rs.getString(i).equals("")){
                        is.add(rs.getBinaryStream(i));
                    }
                }
                dt.setISList(is);
                dataList.add(dt);
            }
        return dataList;
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Dodanie nowego uzytkownika do bazy danych
     * @param email
     * @param pass
     * @param name
     * @param phone
     */

    public void register(String email, String pass, String name, String phone) {
        try {
            ps = con.prepareStatement("insert into uzytkownicy(login,password,name,phone) values(?,?,?,?)");
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sprawdzenie czy użytkowik posiada konto
     * @param email
     * @param pass
     * @return Informacje o użytkowniku
     */

    public ArrayList<String> login(String email, String pass){
        try {
            ps = con.prepareStatement("SELECT login,password,name,phone FROM uzytkownicy WHERE login = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            String na = "";
            String ph = "";
            ArrayList<String> result = new ArrayList<>();



            while (rs.next()) {
//                if(rs.getString(1).equals(email) && rs.getString(2).equals(pass)){
//                    na = rs.getString(3);
//                    ph = rs.getString(4);
//                }
                na = rs.getString(3);
                ph = rs.getString(4);
            }
            if(na.length()> 1){
                result.add(na);
                result.add(ph);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Wyszukuje wszystkich ofert dodanych przez użytkownika o podanym emailu
     * @param email
     * @return Lista ofert użytkownika
     */

    public ArrayList<String> allOffers(String email){
        try {
            ps = con.prepareStatement("SELECT building, address, district, prize FROM dane WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            ArrayList<String> result = new ArrayList<>();
            while (rs.next()) {
                StringBuilder sb  = new StringBuilder();
                sb.append(rs.getString(1)+" ");
                sb.append(rs.getString(2)+" ");
                sb.append(rs.getString(3)+" ");
                sb.append(rs.getString(4)+" zł");
                result.add(sb.toString());
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Wszystkie loginy z bazy danych
     * @return Zwraca loginy wszystkich użytkowników
     */

    public ArrayList<String> allLogins(){
        try {
            ArrayList<String> result = new ArrayList<>();
            ps = con.prepareStatement("SELECT login FROM uzytkownicy");
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}