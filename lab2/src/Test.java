import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {

    private static LinkedList<Punkt3D> punkty;

    public static void main(String[] args) {
        int i=0;
        System.out.print("1. Wczytaj punkt 3D\n" +
                "2. Wyświetl wszystkie punkty\n" +
                "3. Oblicz odległość\n" +
                "4. Zakończ\n" +
                "?");

        while(true){
            Scanner odczyt = new Scanner(System.in);
            i = odczyt.nextInt();
            switch (i){
                case(1):
                    System.out.print("Podaj wspolrzedne punktu:\n");
                    odczyt = new Scanner(System.in);
                    int new_x = odczyt.nextInt();
                    int new_y = odczyt.nextInt();
                    int new_z = odczyt.nextInt();

                    System.out.print("Podaj nazwe punktu:\n");
                    odczyt = new Scanner(System.in);

                    Punkt3D new_point = new Punkt3D(new_x, new_y, new_z, odczyt.nextLine());

                    if(punkty!=null){
                        punkty.add(new_point);}
                    else{
                        punkty = new LinkedList<Punkt3D>();
                        punkty.addFirst(new_point);
                    }
                    break;

                case(2):
                    for (Punkt3D p : punkty) {
                        System.out.print("{" + p.getX() + ", " + p.getY() + ", " + p.getZ() + "} " + p.name + "\n");
                    }
                    break;

                case(3):
                    System.out.print("Podaj nazwe 1 punktu:\n");
                    odczyt = new Scanner(System.in);
                    String nazwa1 = odczyt.nextLine();

                    System.out.print("Podaj nazwe 2 punktu:\n");
                    odczyt = new Scanner(System.in);
                    String nazwa2 = odczyt.nextLine();

                    double distance=0;

                    for (Punkt3D aPunkty : punkty) {
                        if (nazwa1.equals(aPunkty.name)) {
                            for (Punkt3D bPunkty : punkty) {
                                if (nazwa2.equals(bPunkty.name)) {
                                    distance = aPunkty.distance(bPunkty);
                                }
                            }
                        }
                    }

                    System.out.print("odleglosc miedzy punktami wynosi: " + distance);
                    break;

                case(4):
                    return;

                default:
                    System.out.print("Spróbuj jeszcze raz\n\n");
            }

            System.out.print("\n1. Wczytaj punkt 3D\n" +
                    "2. Wyświetl wszystkie punkty\n" +
                    "3. Oblicz odległość\n" +
                    "4. Zakończ\n" +
                    "?\n");
        }
    }
}