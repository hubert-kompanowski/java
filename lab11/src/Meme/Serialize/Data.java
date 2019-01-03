package Meme.Serialize;

import java.io.*;

public class Data implements Serializable {

    //private InputStream is;
    private String path;
    private String name;
    private String UpString;
    private String DownString;



    public Data(String path, String name, String UpString, String DownString) {
        this.name = name;
        this.UpString = UpString;
        this.DownString = DownString;
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getUpString() {
        return UpString;
    }

    public String getDownString() {
        return DownString;
    }
}

