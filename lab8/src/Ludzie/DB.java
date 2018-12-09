package Ludzie;


import java.sql.*;
import javafx.scene.control.*;


public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn =
                    DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/kompan",
                            "kompan","kc9kQMu3jqvAzLgP");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }

    public void initFromDB(ListView<Integer> listID, ListView<String> FirstName, ListView<String> SecondName, ListView<String> Address, ListView<String> PESEL, ListView<String> PhoneNumber, People p){
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM people ORDER BY 1");


            if(rs!= null) {
                while (rs.next()) {
                    int data1 = rs.getInt(1);
                    String data2 = rs.getString(2);
                    String data3 = rs.getString(3);
                    String data4 = rs.getString(4);
                    String data5 = rs.getString(5);
                    String data6 = rs.getString(6);


                    p.peopleList.add(new Person(data1, data2, data3, data4, data5, data6));
                    listID.getItems().add(data1);
                    FirstName.getItems().add(data2);
                    SecondName.getItems().add(data3);
                    Address.getItems().add(data4);
                    PESEL.getItems().add(data5);
                    PhoneNumber.getItems().add(data6);

                }
            }
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void addPerson(int id, String FirstName, String SecondName, String Address, String PESEL, String PhoneNumber){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO people VALUES ('"+id+"' ,' "  +FirstName+  "', '"  +SecondName+  "', '"  +Address+  "', '"  +PESEL+"','"  +  PhoneNumber+    "')" );

        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void deletePerson(String pesel){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM people WHERE pesel LIKE'"+pesel+"'");

        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void chandeAtribute(String data, String atr, String con){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE people SET "+atr+" = '"+data+"' WHERE id = '"+con+"'");

        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
}
