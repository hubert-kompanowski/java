package main.java;

import main.java.zad4.*;
import main.java.zad5.*;
import main.java.crypt.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Zad4.zad4();


        try {
            crypt.crypt();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        try {
//            Zad5.zad5();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
