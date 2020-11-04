package be.vDAB.timeSheetApp.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



public class AskTime  {
    private LocalTime userInputTime;
    Keyboard keyboard = new Keyboard();
    private String hours;
    private String minutes;

//    Constructoren

//    Methoden:

/** Dit is een methode waarbij we een uur van de klant opvragen */
    public String askTimeHours() {
        hours = keyboard.askForText("What is the hour?");
        controlTimeHours(hours);
        return hours;

    }

    public int controlTimeHours(String hours) {
        int controleHours = Integer.parseInt(hours);

        if (controleHours > 24) {
            System.out.println("A day isn't longer than 24 hours");
            askTimeHours();

        }
       return controleHours;

    }
    public String askMinutes () {
        minutes = keyboard.askForText("What are the minutes?");
        controlTimeMinutes(minutes);
        return minutes;
    }


    public int controlTimeMinutes(String minutes) {
        int controleMinutes = Integer.parseInt(minutes);
        if (controleMinutes > 60) {
            System.out.println("An hour isn't longer than 60 minutes");
            askMinutes();

        }

       return controleMinutes;

    }

   public LocalTime getLocalTime(String text){
       System.out.println(text);
    String dateTimeLine = controlTimeHours(askTimeHours()) + ":" + controlTimeMinutes(askMinutes());
    DateTimeFormatter startTijd = DateTimeFormatter.ofPattern("H:m");
    userInputTime = LocalTime.parse(dateTimeLine, startTijd);
    return userInputTime;

    }

}
