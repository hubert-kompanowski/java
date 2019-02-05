package DataOffer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;


    public void init(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/projekt",
                    "root","");
        } catch ( SQLException e) {
            e.printStackTrace();
        }

    }


    public void run(Data dt)  {



        try {
            ps = con.prepareStatement("insert into dane(name,phone,email,address,district,heating,roomsNumber,building,surface, prize," +
                    "f0,f1,f2,f3,f4,f5,f6,f7,f8,f9) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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

            for(int i = x; i<=20; i++){
                ps.setString(i, "");
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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
                        System.out.println("nie null");
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
}



/*

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
                ps.setString(5, fp);
            } else{
                ps.setString(5, "0");
            }

            if(!tp.equals("")) {
                ps.setString(6, tp);
            } else{
                ps.setString(6, "1000000");
            }

            if(!fs.equals("")) {
                ps.setString(7, fs);
            } else{
                ps.setString(7, "0");
            }

            if(!ts.equals("")) {
                ps.setString(8, ts);
            } else{
                ps.setString(8, "1000000");
            }
 */