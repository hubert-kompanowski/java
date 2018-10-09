import java.io.*;

public class Program {
	public static void main(String[] argv){
		int num;
		try{
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);

			System.out.print("Wpisz liczbę: ");
            num =  Integer.parseInt(bfr.readLine()); // jak zaimportować z JIN ???
			for(int i=2; i<num; i++){
				LiczbyPierwsze n = new LiczbyPierwsze(i);
				if(n.check()){
					System.out.print(i+" ");}
			}
		}
	  	catch(IOException e){e.printStackTrace();}
	}
}		