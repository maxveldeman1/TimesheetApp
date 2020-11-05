package be.vDAB.timeSheetApp.utility;


import java.util.Scanner;

public class Keyboard {
    Scanner keyboard = new Scanner(System.in);
    String answer;
    int number;
    double dbl;

    public String askForText(String text) {
        System.out.println(text);
//        keyboard.close();
        return answer = keyboard.nextLine();
    }

    /**
     * Dit is een keyboard voor een nummer op te vragen, als de gebruiker een letter ingeeft wordt dit opgevangen
     * door de exception NumberFormatException
     */
    public int askForInt(String text) {

        try {
            System.out.println(text);
            number = Integer.parseInt( keyboard.nextLine());
        }
        catch (NumberFormatException nfe){
            System.out.println("Please enter a number.");
            askForInt(text);
        }

//        keyboard.close();
        return number;
    }

    public double askForDouble(String text) {
        System.out.println(text);
//        keyboard.close();
        return dbl = keyboard.nextDouble();
    }


}
