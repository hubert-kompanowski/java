package Meme.DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class DB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    public  void run(String nazwa) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_images",
                    "root","");
//kc9kQMu3jqvAzLgP
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(ID) FROM images");
            rs.next();
            int id = rs.getInt(1)+1;



            PreparedStatement ps = conn.prepareStatement("insert into images(ID,Name,Image) values(?,?,?)");
            InputStream is = new FileInputStream(new File("src/Meme/Serialize/tmp.jpg"));
            ps.setString(1, String.valueOf(id));
            ps.setString(2, nazwa);
            ps.setBlob(3, is);
            ps.executeUpdate();

        } catch (SQLException  ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (IllegalAccessException | FileNotFoundException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
