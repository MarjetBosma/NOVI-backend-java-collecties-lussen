import java.util.HashMap;

public class Translator {

    HashMap<Integer, String> numericAlpha = new HashMap<>();

    public Translator(Integer[] numeric, String[] alphabetic) { // constructor
        for (int i = 0; i < numeric.length; i++) {
            numericAlpha.put(numeric[i], alphabetic[i]); // zet steeds de waarde van i in de HashMap
        }
    }
    public String translate(Integer number) { // methode waarin een nummer wordt meegegeven en een string teruggeeft
        return numericAlpha.get(number); // returnt de value uit de numericAlpha HashMap die hoort bij de key van number
    }
}
