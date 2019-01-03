package Ksiazki;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.control.*;

public class Tests {

    public static DB db = new DB();



    @Test
    public void showAll() {

        ListView<String> list = new ListView<String>();

        db.connect();
        db.showAllBooks(list, 1, "");

        Assert.assertEquals(list.getItems().get(0), "1234567891249  |  Mechaniczna pomarancza  |  Antoni Burgess  |  1999");
        Assert.assertEquals(list.getItems().get(1), "1234567891248  |  Rok 1984  |  George Orwell  |  2003");
        Assert.assertEquals(list.getItems().get(2), "1234567891243  |  Snieg  |  Orhan Pamuk  |  2004");
        Assert.assertEquals(list.getItems().get(3), "1234567891247  |  Nowy wspanialy swiat  |  Aldous Huxley  |  2007");
        Assert.assertEquals(list.getItems().get(4), "1234567891246  |  Solaris  |  Stanislaw Lem  |  2008");
        Assert.assertEquals(list.getItems().get(5), "1234567891245  |  Nowe zycie  |  Orhan Pamuk  |  2008");


    }

    @Test
    public void showByAuthor() {


        ListView<String> list = new ListView<String>();

        db.connect();
        db.showAllBooks(list, 2, "Ernest Hemingway");

        Assert.assertEquals(list.getItems().get(0), "1234567891237  |  Stary czlowiek i morze  |  Ernest Hemingway  |  2008");
        Assert.assertEquals(list.getItems().get(1), "1234567891248  |  Slonce tez wstaje  |  Ernest Hemingway  |  2008");
        Assert.assertEquals(list.getItems().get(2), "1234567891243  |  Komu bije dzwon  |  Ernest Hemingway  |  2008");


    }

    @Test
    public void showByISBN() {

        ListView<String> list = new ListView<String>();

        db.connect();
        db.showAllBooks(list, 3, "1234567891237");

        Assert.assertEquals(list.getItems().get(0), "1234567891237  |  Stary czlowiek i morze  |  Ernest Hemingway  |  2008");
    }

}