package Meme.Serialize;

import java.io.*;

public class Make {

    public static void run(Data dt){

        try {
            ObjectOutputStream oop= new ObjectOutputStream(new FileOutputStream("src/Meme/Serialize/tmp.bin"));
            oop.writeObject(dt);
            System.out.println("kagsd");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}




