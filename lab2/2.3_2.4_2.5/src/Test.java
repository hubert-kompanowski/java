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
                {
                    System.out.print("Podaj wspolrzedne punktu:\n");
                    Scanner odczyt1 = new Scanner(System.in);
                    int new_x = odczyt1.nextInt();
                    int new_y = odczyt1.nextInt();
                    int new_z = odczyt1.nextInt();

                    System.out.print("Podaj nazwe punktu:\n");
                    odczyt1 = new Scanner(System.in);

                    Punkt3D new_point = new Punkt3D(new_x, new_y, new_z, odczyt1.nextLine());

                    if(punkty!=null){
                        punkty.add(new_point);}
                    else{
                        punkty = new LinkedList<Punkt3D>();
                        punkty.addFirst(new_point);
                    }

                    System.out.print("1. Wczytaj punkt 3D\n" +
                            "2. Wyświetl wszystkie punkty\n" +
                            "3. Oblicz odległość\n" +
                            "4. Zakończ\n" +
                            "?");
                    break;
                }

                case(2):
                {
                    for(int x=0; x<punkty.size(); x++){
                        Punkt3D p = punkty.get(x);
                        System.out.print("{" + p.getX() + ", " + p.getY() + ", " + p.getZ() + "} " + p.name + "\n");
                    }

                    System.out.print("1. Wczytaj punkt 3D\n" +
                            "2. Wyświetl wszystkie punkty\n" +
                            "3. Oblicz odległość\n" +
                            "4. Zakończ\n" +
                            "?");

                    break;
                }

                case(3):
                {
                    System.out.print("Podaj nazwe 1 punktu:\n");
                    Scanner odczyt1 = new Scanner(System.in);
                    String nazwa1 = odczyt1.nextLine();

                    System.out.print("Podaj nazwe 2 punktu:\n");
                    odczyt1 = new Scanner(System.in);
                    String nazwa2 = odczyt1.nextLine();


                    for(int j=0; j<punkty.size(); j++){
                        if(nazwa1==punkty.get(j).name){

                        }
                    }

                    System.out.print("odleglosc miedzy punktami wynosi: " + );


                    break;
                }

                case(4):
                {
                    return;
                }

                default:
                {
                    System.out.print("Spróbuj jeszcze raz\n");
                }
            }


        }


    }
}
