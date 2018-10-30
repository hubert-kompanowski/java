package zad3;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Klasa odpowiedzialna za przedszymywanie linijki napisów
 * i odzielonych wartości klatek
 *
 * @author hubert kompanowski
 *
 *
 */

public class MovieSubtitles {


    private String subtitles;
    private int firstFrame, secondFrame;


    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public void setFirstFrame(int firstFrame) {
        this.firstFrame = firstFrame;
    }

    public void setSecondFrame(int secondFrame) {
        this.secondFrame = secondFrame;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public int getFirstFrame() {
        return firstFrame;
    }

    public int getSecondFrame() {
        return secondFrame;
    }

    public void addToSubtitles(char c){
        subtitles += c;
    }

    public int size(){
        return (int)Math.log10(firstFrame) +(int)Math.log10(secondFrame) + 2 + 4;
    }

    /**
     *
     * @param subtitles  - linijka kodu przed opóżnieniem/przyspieszeniem
     * @throws InvalidString Jeśli wystąpił błąd związany z plikiem wejściowym
     */

    public MovieSubtitles(String subtitles) throws InvalidString {
        Pattern pattern = Pattern.compile("\\{(\\d+)}\\{(\\d+)}");
        Matcher matcher = pattern.matcher(subtitles);
        if(matcher.find()){
            firstFrame = Integer.parseInt(matcher.group(1));
            secondFrame = Integer.parseInt(matcher.group(2));
            if(firstFrame > secondFrame){
                throw new InvalidString(0);
            }
            this.subtitles = subtitles;

        }
        else {
            throw new InvalidString(1);
        }
    }
}
