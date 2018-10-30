package zad3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za obsługę plików - odczytanie i zapisanie napisów
 * oraz zmiana wartości klatki początkowej i końcowej wg. odpowiedniego
 * opóźnienia/przyśpieszenia dla każdej lini napisów
 *
 * @author hubert kompanowski
 *
 *
 */

public class Program {

    /**
     *
     * @param in - linijka napisów przed opóźnieniem/przyśpieszeniem
     * @param delay - opóźnienie/prześpieszenie w mili sekundach
     * @param fps - liczba klatek na sekundę
     * @return - linijka napisów po dokonanym opóźnieniu/przyśpieszeniu
     * @throws ImpossibeDelay - Jeśli opóźnienie w klatkach jest większe niż wartość pierwszej klatki
     * @throws InvalidString - Jeśli parametr in jest niepoprawny
     */

    public static String deley(String in, int delay, int fps) throws ImpossibeDelay, InvalidString{
        MovieSubtitles actual = new MovieSubtitles(in);
        int time = delay*fps;
        if((actual.getFirstFrame() + time) < 0){
            throw new ImpossibeDelay();
        }
        else{
            int sizeOfIn  = actual.size();
            actual.setFirstFrame(actual.getFirstFrame() + time);
            actual.setSecondFrame(actual.getSecondFrame() + time);
            actual.setSubtitles("{" + actual.getFirstFrame() + "}{" + actual.getSecondFrame() + "}");
            for(int i = sizeOfIn; i < in.length(); i++ ){
                actual.addToSubtitles(in.charAt(i));
            }
            return actual.getSubtitles();
        }

    }

    /**
     *
     * @param path_in - ścieżka do pliku wejściowego
     * @param path_out - ścieżka do pliku wyjsciowego
     * @param milisec - liczba milisekund do opóźnienia/przyśpieszenia
     * @param fps - liczba klatek na sekundę
     * @throws ImpossibeDelay -Jeśli opóźnienie w klatkach jest większe niż wartość pierwszej klatki
     * @throws InvalidString - Jeśli parametr reader jest niepoprawny
     * @throws FileNotFoundException - Jeśli podana ścieżka jest nieprawidłowa
     */

    public static void start(String path_in, String path_out, int milisec, int fps)  throws ImpossibeDelay, InvalidString, FileNotFoundException {
        try {
            String out = "";
            File file_in = new File(path_in);
            PrintWriter file_out = new PrintWriter(path_out);
            Scanner odczyt = new Scanner(file_in);
            String reader = odczyt.nextLine();
            try {
                while (odczyt.hasNext()) {
                    out = deley(reader, milisec, fps);
                    reader = odczyt.nextLine();
                    file_out.println(out);

                }
            } catch (ImpossibeDelay | InvalidString e) {
                throw e;
            }
        }
        catch (FileNotFoundException e){
            throw e;
        }


    }

}
