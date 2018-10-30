import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

/**
 * Klasa Program zawierająca wszystkie potrzebne funkcję aby posortować zdjęcia wg typów
 * przy użyciu Indico Image
 *
 * @author Hubert Kompanowski
 */

public class Program {

    /**
     * Funkcja Photo
     *
     * @param img  - nazwa zdjęcia
     * @param ind - indico
     * @return - zwraca indico result
     * @throws IOException Jeśli wystąpi bład z wejsciem/wyjściem
     * @throws IndicoException Jeśli wystąpi błąd związany z indico
     * @throws NewDirectorsException Jeśli wystąpi bład przy tworzeniu folderów
     */
    private static IndicoResult Photo(String img, Indico ind) throws IOException, IndicoException, NewDirectorsException {
        IndicoResult indicoResult = null;
        indicoResult = ind.imageRecognition.predict(
                img
        );

        Map<String, Double> map = indicoResult.getImageRecognition();

        double resultDouble = 0;
        String string = null;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (resultDouble < entry.getValue()) {
                string = entry.getKey();
                resultDouble = entry.getValue();
            }
        }
        if (string != null && !new File("/home/hubert/Pulpit/3 semestr/JAVA/java/lab4_zad4/src/main/java/foto/" + string.replaceAll("\\s+", "_")).exists()) {
            boolean flag = (new File("/home/hubert/Pulpit/3 semestr/JAVA/java/lab4_zad4/src/main/java/foto/" + string.replaceAll("\\s+", "_"))).mkdirs();
            if (!flag) {
                throw new NewDirectorsException();
            }
        }
        Path Source = Paths.get(img);
        Path Destination = Paths.get("/home/hubert/Pulpit/3 semestr/JAVA/java/lab4_zad4/src/main/java/foto/" + string.replaceAll("\\s+", "_") + "/" + img.substring(img.lastIndexOf("/") + 1));

        Files.copy(Source, Destination, StandardCopyOption.REPLACE_EXISTING);

        return indicoResult;
    }

    /**
     * Funkcja main z obsługą błędów
     *
     * @param argv - w konfiguracji podana jest ścieżka do folderu jako argument pierwszy
     */

    public static void main(String[] argv) {

        try {

            Indico indico = new Indico("a91684316ee5057a65b38ba4b1c631ec");


            File dir = new File(argv[0]);
            if (!dir.isDirectory()) throw new DirectoryDoesntExistException();

            Stream<Path> paths = Files.walk(Paths.get(argv[0]));
            paths
                    .filter((p) -> (p.toString().endsWith(".jpg")))
                    .forEach((p) -> {
                        try {
                            IndicoResult single = Photo(p.toString(), indico);
                        } catch (NewDirectorsException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException | IndicoException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (DirectoryDoesntExistException e) {
            e.getMessage();
        } catch (IndicoException i) {
            i.printStackTrace();
        } catch (IOException o) {
            o.fillInStackTrace();

        }

    }

}
