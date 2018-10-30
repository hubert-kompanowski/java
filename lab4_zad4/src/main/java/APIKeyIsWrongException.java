/**
 * Wyjątek Klucz API
 *
 * @author Hubert Kompanowski
 */

public class APIKeyIsWrongException extends Exception {
    private static String ex = "Klucz API jest nieprawidłowy";

    public String getMessage() {
        return ex;
    }
}
