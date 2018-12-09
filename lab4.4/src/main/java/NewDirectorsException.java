/**
 * Wyjątek Tworzenie folderów
 *
 * @author Hubert Kompanowski
 */

public class NewDirectorsException extends Exception {
    private static String ex = "Błąd tworzeniu folderów";

    public String getMessage() {
        return ex;
    }
}
