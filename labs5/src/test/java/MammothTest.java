import org.junit.*;
import org.junit.rules.ExpectedException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class MammothTest {
    private  static File file;

    @BeforeClass
    public static void tworzeniePliku() throws IOException {
        file = new File("file.txt");
        if(file.createNewFile()) System.out.println("Plik istnieje");
        else System.out.println("Nie udało się stworzyć pliku");
    }
    @AfterClass
    public static void tworzenieUsuwanie() {
        if (file.delete()) System.out.println("Plik został usunięty");
        else System.out.println("Plik nie istniał");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsInadequateFoodException() throws InadequateFoodException{
        thrown.expect(InadequateFoodException.class);
        thrown.expectMessage("I don't like meat");
        Mammoth m = new Mammoth();
        m.eat(new Meat());
    }



}