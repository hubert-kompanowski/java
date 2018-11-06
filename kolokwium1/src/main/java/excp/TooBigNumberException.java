package excp;

public class TooBigNumberException extends Exception {
    public TooBigNumberException(int x){
        System.out.print("Podano za duża liczbę zwieloktornienia: ");
        System.out.print(x);
    }
}
