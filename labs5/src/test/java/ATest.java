import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ATest {

    @Test
    public void pierwszyTest(){
        assertEquals(A.met(1), "pierwszy");
    }


    @Test
    public void drugiTest(){
        assertEquals(A.met(2), "drugi");
    }

    @Test
    public void innyTest(){
        assertEquals(A.met(10), "inny");
        assertEquals(A.met(-23), "inny");
        assertEquals(A.met(23), "inny");
        assertEquals(A.met(134), "inny");
        assertEquals(A.met(0), "inny");

    }

}