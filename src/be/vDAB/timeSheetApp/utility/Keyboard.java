package be.vDAB.timeSheetApp.utility;


import java.util.Scanner;

/**
 * Utility class for making a keyboard and asking for an input, but also checks it with Exception Handling if needed,
 * if passed the check returns a number or text.
 * Can be used the following way:
 * <pre>
 *     Keyboard kb = new Keyboard();
 *     kb.askForInt("What number do you want");
 * </pre>
 *
 * @see Scanner;
 */
public class Keyboard {
    Scanner keyboard = new Scanner(System.in);
    String answer;
    int number;

    /**
     * This is a keyboard to ask for a text as an answer on the question of String text.
     * It doesn't matter if a text, number or whitespace is added, so there aren't
     * any exceptions.
     *
     * @param text here you enter the question on which you want an input
     */
    public String askForText(String text) {
        System.out.println(text);
        return answer = keyboard.nextLine();
    }

    /**
     * Dit is een keyboard voor een nummer op te vragen, als de gebruiker een letter ingeeft wordt dit opgevangen
     * door de exception NumberFormatException.
     */
    public int askForInt(String text) {

        try {
            System.out.println(text);
            number = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number.");
            askForInt(text);
        }
        return number;
    }


}
