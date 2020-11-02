package be.vDAB.timeSheetApp.utility;

import java.util.Scanner;

public class Keyboard {
    Scanner keyboard = new Scanner(System.in);
    String answer;
    int number;
    double dbl;

    public String askForText(String text) {
        System.out.println(text);
        return answer = keyboard.next();
    }

    public int askForInt(String text) {
        System.out.println(text);
        return number = keyboard.nextInt();
    }

    public double askForDouble(String text) {
        System.out.println(text);
        return dbl = keyboard.nextDouble();
    }


}
