package Ksiazki;

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

    public void showAllBooks(ListView<String> r, int selector, String data){
        try {
            stmt = conn.createStatement();



            switch (selector){
                case 1:
                {
                    rs = stmt.executeQuery("SELECT * FROM books");
                    break;
                }
                case 2:
                {
                    rs = stmt.executeQuery("SELECT * FROM books WHERE author LIKE '"+data+"'");
                    break;
                }
                case 3:
                {
                    rs = stmt.executeQuery("SELECT * FROM books WHERE isbn LIKE '"+data+"'");
                    break;
                }
                case 4:
                {
                    stmt.executeUpdate("INSERT INTO books VALUES ("+data+")" );
                    break;
                }

            }
            if(rs!= null) {
                while (rs.next()) {
                    String data1 = rs.getString(1);
                    String data2 = rs.getString(2);
                    String data3 = rs.getString(3);
                    String data4 = rs.getString(4);

                    r.getItems().add(data1 + "  |  " + data2 + "  |  " + data3 + "  |  " + data4);

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
}
