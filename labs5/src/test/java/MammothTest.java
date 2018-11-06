import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MammothTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ordinaryTest(){

    }

    @Test
    public void meatTest() throws InadequateFoodException{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("argument nie może byc Meat");

        Mammoth.eat(new Meat());
    }






}