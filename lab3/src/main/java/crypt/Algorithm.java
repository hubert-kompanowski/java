package main.java.crypt;

public interface Algorithm {
    public String crypt(String word);

    public String decrypt(String word);
}
