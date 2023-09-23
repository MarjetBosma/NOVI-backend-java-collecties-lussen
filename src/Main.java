import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Integer[] numeric = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String[] alphabetic = {"een", "twee", "drie", "vier", "vijf", "zes", "zeven", "acht", "negen", "nul"};

        Translator t1 = new Translator(numeric, alphabetic);
        // System.out.println(t1.translate(9)); // Testen of de methode werkt: ja, returnt "negen" bij meegegeven van 9.

        boolean play = true; // translator is actief als play true is
        String invalid = "ongeldige invoer";
        Scanner scanner = new Scanner(System.in);

        while (play) {
            System.out.println("Type 'x' om te stoppen \nType 'v' om te vertalen");
            String input = scanner.nextLine(); // Aanspreken van de scanner

            if (input.equals("x")) {
                play = false; // Stopt de uitvoer van de translator
            } else if (input.equals("v")) {
                System.out.println("Type een cijfer van 0 t/m 9");
                int number = scanner.nextInt(); // Haalt de waarde voor number uit de input van de gebruiker
                scanner.nextLine();
                if (number < 10) {
                    String result = t1.translate(number); // Slaat resultaat op als string
                    System.out.println("De vertaling van " + number + " is " + result); // Geeft resultaat terug aan de gebruiker
                } else {
                    System.out.println(invalid); // Geeft "ongeldige invoer" terug aan de gebruiker
                }
            } else {
                System.out.println(invalid);
            }
        }
    }
}
