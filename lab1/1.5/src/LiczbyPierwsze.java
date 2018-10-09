public class LiczbyPierwsze {
	private int number;
	public LiczbyPierwsze(int _number){
		number=_number;}
	public boolean check(){
		int d=number/2;
		for(int i=2; i<=d; i++){
			if(number%i==0){
				return false;}
		}
		return true;
	}
}
		