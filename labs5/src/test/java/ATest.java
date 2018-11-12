import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ATest {
    @Test
    public void metPierwszy() {
        A a = new A();
        Assert.assertEquals(a.met(1), "pierwszy" );
    }
    @Test
    public void metDrugi() {
        A a = new A();
        Assert.assertEquals(a.met(2), "drugi" );
    }
    @Test
    public void metInny() {
        A a = new A();
        Assert.assertEquals(a.met(6), "inny" );
    }
}