package zad3;

/**
 * @author hubert kompanowski
 */

public class InvalidString extends Exception {

    private int anInt;

    /**
     *
     * @param i - odpowiedzialny za określenie rodzaju błędu
     */

    public InvalidString(int i){
        anInt =i;
    }

    public void printException(){
        if(anInt ==0){
            System.out.println("Zły przedział klatek");
        }
        else if(anInt ==1){
            System.out.println("Zły format tekstu");
        }
    }
}
