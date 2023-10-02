// Opmerking vooraf: Ik zal eerlijk zijn, ik heb niet alle code volledig zelf geschreven. Een opdracht als deze is nog behoorlijk pittig, en daarom heb ik o.a. ChatGPT gebruikt bij de gedeelten waar ik niet uitkwam, en ook bij nieuwe dingen die ik leuk vond om toe te voegen (error handling en betere user feedback). Ik heb me uiteraard wel serieus in de code verdiept, en ook bij de niet zelf bedachte code gezorgd dat ik van elke regel begrijp wat er gebeurt. Dat maakte het toch ook heel leerzaam en ik vond het ontzettend leuk! Met een beetje hulp kon ik al dingen maken die ik nog niet voor mogelijk had gehouden in deze fase.

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Bonus {

    public static void main(String[] args) {
        boolean play = true; // Geeft aan dat het spel bezig is

        while (play) {
            // Hier worden alle gebruikte methodes aangeroepen, die daaronder worden gedefinieerd
            HashSet<Integer> secretNumber = randomNumberGenerator();
            String stringNumber = setToString(secretNumber);
            // System.out.println(stringNumber); // Deze was handig bij het testen, maar staat nu uit, anders krijgt de gebruiker het te raden nummer al te zien...
            feedback(stringNumber); // User input wordt meegegeven aan de feedback methode
            System.out.println("Wil je nog een keer spelen? (ja/nee)"); // Als het getal is geraden krijgt de gebruiker deze vraag
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().toLowerCase(); // toLowerCase zorgt ervoor dat het niet uitmaakt of de gebruiker hoofletters of kleine letters ingeeft
            if (!response.equals("ja")) { // Alle antwoorden anders dan ja zetten play op false en het programma stopt. Bij "ja" begint het opnieuw
                play = false;
            }
        }
    }

    public static HashSet<Integer> randomNumberGenerator() { // De return value is de HashSet<Integer>, er zijn geen parameters mee te geven.
        Random random = new Random(); // Maakt een nieuwe instantie van de Random class
        HashSet<Integer> secretNumber = new HashSet<>(); // Maakt een nieuwe HashSet, die in eerste instantie leeg is.
        while (secretNumber.size() < 4) { // Zolang de set kleiner is dan 4
            int randomNumber = random.nextInt(9) + 1; // wordt er een random number gegenereerd tussen 0 en 9 en opgeslagen als variabele
            secretNumber.add(randomNumber); // en wordt dit random number toegevoegd aan de set
        }
        return secretNumber; // Returnt de HashSet, die nu bestaat uit 4 random numbers.
        // Gebruik van een HashSet garandeert dat alle nummers uniek zijn, in een set mogen per definitie niet twee dezelfde waardes voorkomen.
    }

    public static String setToString(HashSet<Integer> secretNumber) { // De return value is eens string, de parameter is de HashSet uit bovenstaande methode
        StringBuilder stringNumber = new StringBuilder(); // Maakt een nieuwe instantie van de StringBuilder
        for (Integer element : secretNumber) { // Itereert door elke element in de secretNumber set
            stringNumber.append(element.toString()); // Maakt van elke integer een string, en met append worden deze string elementen tot één string samengevoegd
        }
        return stringNumber.toString(); // Returnt de cijferreeks als string
    }

    // Onderstaande code was al grotendeels gegeven, toegevoegd is:
    // - De while-loop die ervoor zorgt dat de gebruiker meerdere keren kan raden, totdat alle getallen kloppen.
    // - Het try/catch blok dat errors afhandelt. Dit voorkomt dat het programma crasht als de gebruiker iets anders invoert dan getallen.
    public static void feedback(String stringNumber) { // Void, want er us geen return value, de parameter is de string uit bovenstaande methode

        Scanner scanner = new Scanner(System.in); // Maakt een Scanner object om user input te kunnen lezen
        StringBuilder feedback = new StringBuilder(); // Maakt een nieuwe instantie van de StringBuilder
        System.out.println("Kun je de reeks van vier getallen raden? Geef steeds 4 verschillende getallen en kijk goed naar de informatie die je terugkrijgt: \n + = juiste nummer op de juiste plek, O = juiste nummer verkeerde plek, X = verkeerde nummer. Doe maar een gok!"); // Print instructies voor de gebruiker

        while (true) { // while loop, die blijft doorgaan zolang niet alle getallen geraden zijn

            try { // try/catch constructies ken ik vanuit JavaScript. Hier gebruikt ik dit om te kijken of de user input correct is (dus bestaat uit precies 4 cijfers) en hier feedback over te geven.
                String guess = scanner.nextLine(); // Slaat de input van de gebruiker op in een variabele
                Integer.parseInt(guess); // Probeert van de input integers te maken, dit lukt alleen als de input bestaat uit getallen

                HashSet<Character> uniqueDigits = new HashSet<>(); // Nog een HashSet maken, maar dan van chars, om deze te kunnen vergelijken
                boolean hasDuplicates = false; // in principe mogen er geen dubbele getallen in de opgegeven reeks zitten
                for (char digit : guess.toCharArray()) { // Itereert langs alle getallen in de array en zet ze om in een char array
                    if (!uniqueDigits.add(digit)) { // Voegt de cijfers toe aan de set, maar dit kan niet als het cijfer al in de set zit
                        hasDuplicates = true; // True als er dubbele cijfers zijn
                        break; // Stopt de loop
                    }
                }

                if (guess.length() != 4 || hasDuplicates) { // Indien er niet voldaan wordt aan een van deze condities (lengte anders dan 4, dubbele getallen)
                    System.out.println("Je moet precies vier verschillende getallen invoeren."); // Als dat niet het geval is, ziet de gebruiker deze foutmelding
                    continue; // Gaat verder naar de volgende iteratie van de while loop
                }
                if (Objects.equals(guess, stringNumber)) { // Als je input van de gebruiker gelijk is aan de eerder gedefinieerde reeks van 4 getallen...
                    System.out.println("Gefeliciteerd, je hebt alle getallen geraden!"); // ... wordt in de console aangegeven dat het goed geraden is
                    break; // Stopt de while loop als alle getallen correct geraden zijn
                } else {
                    feedback.setLength(0); // Verwijdert de feedback van de vorige beurt
                    for (int i = 0; i < 4; i++) {  // Itereert door de stringNumber set (bestaande uit vier elementen) en checkt elke waarde
                        if (guess.substring(i, i + 1).equals(stringNumber.substring(i, i + 1))) { // checkt of het getal van de gebruiker overeenkomt qua waarde én positie in de set
                            feedback.append("+"); // en voegt dan + toe aan de feedback string
                        } else if (stringNumber.contains(guess.substring(i, i + 1))) { // checkt of het getal van de gebruiker ergens in de reeks voorkomt, maar dus niet in de juiste positie
                            feedback.append("0"); // en voegt dan 0 toe aan de feedback string
                        } else { // Als het getal vanuit de input nergens in de set voorkomt
                            feedback.append("X"); // wordt X toegevoegd aan de feedback string
                        }
                    }
                    System.out.println(feedback + "\nDat is helaas niet correct. Probeer het nog eens!"); // geeft de feedback (+, ), X) en het zinnetje aan de gebruiker terug.
                }
            } catch (NumberFormatException e) { // Als het niet lukt om integers te maken van de input (Integer.parseInt, zie try blok), dan "weet" Java dat de input niet bestaat uit getallen
                System.out.println("Ongeldige invoer, je mag alleen cijfers invullen"); // en krijgt de gebruiker dit te zien
                scanner.nextLine();
            }
        }
    }
}
 // Comment bij stap 4: Ik meen me te herinneren dat in het originele Master Mind spel de nummers (of kleuren, dat is hoe ik het ken) niet uniek hoeven te zijn, ze mogen zelfs allevier gelijk zijn. In die zin is een HashSet een minder goede keuze, want per definitie zijn daarin alle waardes uniek. Wellicht was het beter geweest om een HashMap te gebruiken.