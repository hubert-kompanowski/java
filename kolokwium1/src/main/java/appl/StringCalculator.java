package appl;


import excp.BadInputException;
import excp.NothingToSubstractFromException;
import excp.TooBigNumberException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public  class StringCalculator extends Calculator{

    public String getResult() {
        return result;
    }

    private String result;

    public StringCalculator(){
        result = "";
    }

    public StringCalculator(String result) {
        this.result = result;
    }

    public static void main(String [] argv){

        try {
            StringCalculator s = new StringCalculator("ah");
            s.Add("eh");
            s.Subtract("h");
            s.Multiply(2);
            System.out.print(s.getResult());

            s.SaveToFile("/home/hubert/Pulpit/3 semestr/JAVA/java/kolokwium1/src/main/java/appl/plik.txt");

        } catch (NothingToSubstractFromException | BadInputException | TooBigNumberException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void SaveToFile(String filename) throws FileNotFoundException {
        PrintWriter file = new PrintWriter(filename);
        file.print(result);
        file.close();

    }

    @Override
    public void Add(String x) {  // łączenie
        result += x;
    }

    @Override
    public void Subtract(String x) throws NothingToSubstractFromException, BadInputException {
        if(result.isEmpty()){
            throw new NothingToSubstractFromException();
        }

        if(!result.contains(x)){
            throw new BadInputException(x);
        }

        result = result.replace(x, "");
    }

    @Override
    public void Multiply(int x) throws TooBigNumberException {
        if(x>5){
            throw new TooBigNumberException(x);
        }
        String tmp = result;
        for(int i=0; i<x-1; i++){
            result+=tmp;
        }

    }
}
