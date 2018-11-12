package pl.edu.agh.kis.java2015.main;

import pl.edu.agh.kis.java2015.domain.Heap;

public class App {

    public static void main(String[] argv){
        Heap h = new Heap();
        h.insert(1.5);
        h.insert(2.5);
        h.insert(3.5);
        h.insert(4.5);
        h.insert(5.5);
        h.insert(4.5);

        h.printHeap();

    }
}
