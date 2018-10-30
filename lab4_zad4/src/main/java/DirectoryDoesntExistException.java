/**
 * Wyjątek Istnienie Folderu
 *
 * @author Hubert Kompanowski
 */

public class DirectoryDoesntExistException extends Exception {
    private static String ex = "Podany folder nie istnieje";

    public String getMessage() {
        return ex;
    }
}
