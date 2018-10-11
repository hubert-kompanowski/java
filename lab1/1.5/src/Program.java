import java.io.*;
import javaIn.JIn;

public class Program {
	public static void main(String[] argv){
		int num;

		//InputStreamReader rd = new InputStreamReader(System.in);
		//BufferedReader bfr = new BufferedReader(rd);

		System.out.print("Wpisz liczbę: ");
		num = JIn.getInt(); // jak zaimportować z JIN ???
		for(int i=2; i<num; i++){
			LiczbyPierwsze n = new LiczbyPierwsze(i);
			if(n.check()){
				System.out.print(i+" ");}
		}

	}
}		