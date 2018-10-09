/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author Hubert
 */
public class Program {

    public static void main(String[] argv){
        Login log = new Login ("ala", "makota");
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            // TODO: prosba o wpisanie hasła i loginu
            System.out.print("Wpisz  login: ");
            String login = bfr.readLine();

            InputStreamReader rd2 = new InputStreamReader(System.in);
            BufferedReader bfr2 = new BufferedReader(rd2);
            System.out.print("Wpisz  haslo: ");
            String haslo = bfr2.readLine();

        /*TODO: sprawdzenie czy podane hasło i login zgadzaja sie z tymi
         przechowywanymi w obiekcie log Jeśli tak, to ma zostać
         wyswietlone "OK", jesli nie - prosze wyswietlić informacje o błedzie
         */
            log.check(login, haslo);

        }catch(IOException e){e.printStackTrace();}

    }
}