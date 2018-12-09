/**
 * Wyjątek Zły format pliku
 *
 * @author Hubert Kompanowski
 */

public class FileIsNotAFotoException extends Exception {
    private static String ex = "Podany plik posiada zły format";

    public String getMessage() {
        return ex;
    }
}
