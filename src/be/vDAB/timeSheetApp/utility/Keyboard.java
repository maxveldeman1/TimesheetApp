package be.vDAB.timeSheetApp.utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Keyboard {
    Scanner keyboard = new Scanner(System.in);
    String answer;
    int number;
    double dbl;

    public String askForText(String text) {
        System.out.println(text);
//        keyboard.close();
        return answer = keyboard.next();
    }

    /**
     * Dit is een keyboard voor een nummer op te vragen, als de gebruiker een letter ingeeft wordt dit opgevangen
     * door de exception NumberFormatException
     */
    public int askForInt(String text) {
        try {
            System.out.println(text);
            number = keyboard.nextInt();
        } catch (NumberFormatException nfe){
            System.out.println("Gelieve een nummer in te geven.");
            askForInt(text);
        }
//        catch (NoSuchElementException nse){
//            System.out.println("Gelieve een correct nummer in te geven");
//            askForInt(text);
//        }

//        keyboard.close();
        return number;
    }

    public double askForDouble(String text) {
        System.out.println(text);
//        keyboard.close();
        return dbl = keyboard.nextDouble();
    }


}
