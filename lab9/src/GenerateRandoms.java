import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GenerateRandoms {

    public void fill(){
        int r;

        try {
            File n = new File("randoms.txt");
            PrintWriter wr = new PrintWriter(n, "UTF-8");
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 1000; j++) {
                    r = (int) (Math.random() * 50 + 1);
                    wr.print(r + " ");
                }
                wr.print("\n");
            }
            wr.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}