package main.java.zad4;


import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Zad4 {

    public static void zad4() {

        LinkedList<Shape> lista = new LinkedList<Shape>();

        int i=0;
        while(i<6){
            System.out.print("1.Koło\n2.Trójkąt\n3.Kwadrat\n4.Prostokat\n5.Wypisz\n6.Zakończ\n\n");
            Scanner odczyt = new Scanner(System.in);
            i = odczyt.nextInt();
            if(i==1){
                System.out.print("Podaj nazwę\n");
                odczyt = new Scanner(System.in);
                Shape c = new Circle();
                c.name= odczyt.nextLine();
                lista.add(c);
            }
            if(i==2){
                System.out.print("Podaj nazwę\n");
                 odczyt = new Scanner(System.in);
                Shape t = new Triangle();
                t.name= odczyt.nextLine();
                lista.add(t);
            }

            if(i==3){
                System.out.print("Podaj nazwę\n");
                 odczyt = new Scanner(System.in);
                Shape s = new Square();
                s.name= odczyt.nextLine();
                lista.add(s);
            }

            if(i==4) {
                System.out.print("Podaj nazwę\n");
                odczyt = new Scanner(System.in);
                Shape r = new Rectangle();
                r.name = odczyt.nextLine();
                lista.add(r);
            }
            if(i==5){
                sort_list(lista);
                for(Shape n:lista){
                    n.draw();
                }
            }
        }
    }

    private static void sort_list(LinkedList<Shape> lista) {
        Collections.sort(lista, new Comparator<Shape>() {
            @Override
            public int compare(Shape shape, Shape t1) {
                return Character.compare(shape.name.charAt(0), t1.name.charAt(0));
            }});
    }
}
