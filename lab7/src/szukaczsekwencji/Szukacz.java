package szukaczsekwencji;

public class Szukacz {
    public static String szukaj (String sekwencja, String szukana) {
        // Tniemy długą sekwecję na fragmenty w miescach gdzie
        // znajduje się szukana sekwencja. Tablica pomiedzy zawiera fragmenty
        // długiej sekwencji znajdujące się pomiędzy miejcami występowania szukanej
        String[] pomiedzy = sekwencja.split(szukana);
        // Tu będzie docelowa sekwencja z zaznaczonymi miejscami występowania
        // szukanej sekwencji
        String rezultat = "";
        // Jeżeli znaleziono przyjamniej jedno miejsce występowania sekwencji
        // tablica pomiedzy zawiera przynajmniej 2 odcinki
        if (pomiedzy.length > 1) {
            // Pobieramy kolejno odcinki występujące pomiędzy miescami występowania
            // szukanej sekwencji aż do przedostatniej
            for(int i = 0; i < pomiedzy.length - 1 ; i++ ) {
                // doklejamy do wyniku "<", szukaną sekwencję, która w tym miejscu
                // wystepuje, znak ">" oraz kolejny fragment długiej sekwencji
                // Przy pierwszym miescu wystąpnienia trzeba dodać poprzedni odcinek
                if (rezultat.length()==0)
                    rezultat = rezultat + pomiedzy[i] + "<" + szukana + ">" + pomiedzy[i+1];
                else
                    rezultat = rezultat + "<" + szukana + ">" + pomiedzy[i+1];

            }
            // To na wypadek, jeśli szukana sekwencja znajduje się także na końcu
            // długiej sekwencji.
            if (sekwencja.indexOf(szukana, sekwencja.length()-szukana.length()) > 0) {
                rezultat = rezultat + "<" + szukana + ">";
            }
        }
        // Jeśli nie ma szukanej sekwencji, zwróć całą długa sekwencję bez
        // żadnych zaznaczeń
        else {
            rezultat = sekwencja;
        }
        return rezultat;
    }
}
