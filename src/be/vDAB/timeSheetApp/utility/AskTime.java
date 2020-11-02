package be.vDAB.timeSheetApp.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AskTime {
    private LocalTime userInputTime;
    Scanner keyboard = new Scanner(System.in);
    private String hours;
    private String minutes;

//    Constructoren

//    Methoden:

/** Dit is een methode waarbij we het startuur van de klant opvragen */
    public String askStartTimeHours() {
        System.out.println("Wat is het startuur?");
        hours = keyboard.nextLine();
        controlTimeHours(hours);
        return hours;

    }

    public int controlTimeHours(String hours) {
        int controleHours = Integer.parseInt(hours);

        if (controleHours > 24) {
            System.out.println("A day isn't longer than 24 hours");
            askStartTimeHours();

        }
       return controleHours;

    }
    public String askStartMinutes () {
        System.out.println("Wat zijn de minuten?");
        minutes = keyboard.nextLine();
        controlTimeMinutes(minutes);
        return minutes;
    }
    public int controlTimeMinutes(String minutes) {
        int controleMinutes = Integer.parseInt(minutes);
        if (controleMinutes > 60) {
            System.out.println("An hour isn't longer than 60 minutes");
            askStartMinutes();

        }

       return controleMinutes;

    }

   public LocalTime getLocalTime (int controleHours, int controleMinutes){
    String dateTimeLine = controleHours + ":" + controleMinutes;
    DateTimeFormatter startTijd = DateTimeFormatter.ofPattern("H:mm");
    userInputTime = LocalTime.parse(dateTimeLine, startTijd);
    System.out.println(userInputTime);
    keyboard.close();
    return userInputTime;

    }


}
