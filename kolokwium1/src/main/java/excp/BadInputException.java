package excp;

public class BadInputException extends Exception {
    public BadInputException(String x){
        System.out.print("Brak podanego nnapisu w tekscie. Podano: " + x);

    }
}
