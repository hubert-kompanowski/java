import zad3.ImpossibeDelay;
import zad3.InvalidString;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] argv){

        try {
            zad3.Program.start("/home/hubert/Pulpit/3 semestr/JAVA/java/lab4/src/zad3/fetch.txt", "/home/hubert/Pulpit/3 semestr/JAVA/java/lab4/src/zad3/out.txt", -10, 30);
        }catch(InvalidString e){
            e.printException();
            e.printStackTrace();

        }
        catch (ImpossibeDelay | FileNotFoundException e) {
            e.printStackTrace();
        }




    }
}
