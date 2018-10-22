public class Polibiusz implements Algorithm {
    private char[][] tab = new char[5][5];

    public Polibiusz() {
        char k = 97;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                tab[i][j] = k;
                if (k == 105) {
                    k++;
                }
                k++;
            }
        }
    }

    @Override
    public String crypt(String slowo) {
        String nowe_slowo = "";
        char wyjatek;
        slowo = slowo.toLowerCase();
        for (int w = 0; w < slowo.length(); w++) {
            if (slowo.charAt(w) >= 97 && slowo.charAt(w) <= 122) {
                if (slowo.charAt(w) == 'j') {
                    wyjatek = 'i';
                } else
                    wyjatek = slowo.charAt(w);

                for (int i = 0; i <= 4; i++) {
                    for (int j = 0; j <= 4; j++) {
                        if (tab[i][j] == wyjatek) {
                            nowe_slowo = nowe_slowo + String.valueOf(i + 1) + String.valueOf(j + 1);
                        }
                    }
                }
            } else
                nowe_slowo = nowe_slowo + slowo.charAt(w);
        }
        return nowe_slowo;
    }

    @Override
    public String decrypt(String slowo) {
        String nowe_slowo = "";
        int x;
        int y;
        slowo = slowo.toLowerCase();
        for (int w = 0; w < (slowo.length()); w++) {
            if (slowo.charAt(w) >= 48 && slowo.charAt(w) <= 57) {
                if (w < (slowo.length() - 1)) {
                    if (slowo.charAt(w + 1) >= 48 && slowo.charAt(w + 1) <= 57) {
                        x = Character.getNumericValue(slowo.charAt(w));
                        y = Character.getNumericValue(slowo.charAt(w + 1));
                        nowe_slowo = nowe_slowo + tab[x - 1][y - 1];
                        w++;
                    } else
                        nowe_slowo = nowe_slowo + slowo.charAt(w);
                } else
                    nowe_slowo = nowe_slowo + slowo.charAt(w);
            } else
                nowe_slowo = nowe_slowo + slowo.charAt(w);
        }
        return nowe_slowo;
    }
}
