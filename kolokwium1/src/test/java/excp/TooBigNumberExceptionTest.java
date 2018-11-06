package excp;

import appl.StringCalculator;
import org.junit.Test;


public class TooBigNumberExceptionTest {

    @Test(expected = TooBigNumberException.class)
    public void TestWyjatku() throws TooBigNumberException{
        StringCalculator s = new StringCalculator("heheh");

        s.Multiply(6);



    }

}