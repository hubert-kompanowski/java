package appl;

import excp.BadInputException;
import excp.NothingToSubstractFromException;
import excp.TooBigNumberException;

import java.io.FileNotFoundException;

public abstract class Calculator {

    public  abstract void SaveToFile(String filename) throws FileNotFoundException;

    public abstract void Add(String x);
    public abstract void Subtract(String x) throws NothingToSubstractFromException, BadInputException;
    public abstract void Multiply(int x) throws TooBigNumberException;


}
