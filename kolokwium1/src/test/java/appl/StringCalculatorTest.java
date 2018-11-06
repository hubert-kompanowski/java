package appl;

import excp.TooBigNumberException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void add() {
        StringCalculator s = new StringCalculator("abcde");
        s.Add("ABCDE");
        Assert.assertEquals(s.getResult(), "abcdeABCDE");
    }

    @Test
    public void multiply() {

        try {
            StringCalculator s = new StringCalculator("XYZ");
            s.Multiply(3);

            Assert.assertEquals(s.getResult(), "XYZXYZXYZ");
        } catch (TooBigNumberException e) {
            e.printStackTrace();
        }



    }
}