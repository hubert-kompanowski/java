package main.java.crypt;

public class ROT11 implements Algorithm {
    private String alfabet = "abcdefghijklmnopqrstuvwxyz";


    @Override
    public String crypt(String slowo) {
        String nowe_slowo = "";
        for (int i = 0; i < slowo.length(); i++) {
            if ((slowo.charAt(i) >= 97 && slowo.charAt(i) <= 122)) {
                nowe_slowo = nowe_slowo + alfabet.charAt(((slowo.charAt(i) - 97) + 11) % alfabet.length());
            } else if (slowo.charAt(i) >= 65 && slowo.charAt(i) <= 90) {
                nowe_slowo = nowe_slowo + alfabet.toUpperCase().charAt(((slowo.charAt(i) - 65) + 11) % alfabet.length());
            } else {
                nowe_slowo = nowe_slowo + slowo.charAt(i);
            }
        }
        return nowe_slowo;
    }

    @Override
    public String decrypt(String slowo) {
        String nowe_slowo = "";
        for (int i = 0; i < slowo.length(); i++) {
            if (slowo.charAt(i) >= 97 && slowo.charAt(i) <= 122) {
                nowe_slowo = nowe_slowo + alfabet.charAt((slowo.charAt(i) - 97 + (alfabet.length() - 11)) % alfabet.length());
            } else if (slowo.charAt(i) >= 65 && slowo.charAt(i) <= 90) {
                nowe_slowo = nowe_slowo + alfabet.toUpperCase().charAt((slowo.charAt(i) - 65 + (alfabet.length() - 11)) % alfabet.length());
            } else {
                nowe_slowo = nowe_slowo + slowo.charAt(i);
            }
        }
        return nowe_slowo;
    }
}
