import java.io.*;
import java.util.Scanner;

public class Cryptographer {
    public static void cryptfile(File plik, File nowy_plik, Algorithm alg) throws IOException {

        FileReader fr = null;
        String linia;
        FileWriter fw = new FileWriter(nowy_plik);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            fr = new FileReader(plik);
        } catch (FileNotFoundException e) {
            System.out.println("Blad z plikiem");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        try {
            while ((linia = bfr.readLine()) != null) {
                bw.write(alg.crypt(linia));
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Blad z plikiem");
            System.exit(2);
        }

    }

    public static void decryptfile(File plik, File nowy_plik, Algorithm alg) throws IOException {
        FileReader fr = null;
        String linia;
        FileWriter fileWriter = new FileWriter(nowy_plik);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            fr = new FileReader(plik);
        } catch (FileNotFoundException e) {
            System.out.println("Blad z plikiem");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        try {
            while ((linia = bfr.readLine()) != null) {
                bufferedWriter.write(alg.decrypt(linia));
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Blad z plikiem");
            System.exit(2);
        }

    }
}
