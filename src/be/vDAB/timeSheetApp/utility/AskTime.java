package be.vDAB.timeSheetApp.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class to let the user enter an hour and the minutes and based of that, create a LocalTime, also checks if
 * the entered values are within the range of hour and minutes.
 *
 * @see Keyboard;
 * @see LocalTime
 * @see DateTimeFormatter;
 */
public class AskTime {
    Keyboard keyboard = new Keyboard();

    /**
     * Asks the user for an hour
     *
     * @return gives a string with the hour
     */
    public String askTimeHours() {
        String hours = keyboard.askForText("What is the hour?");
        controlTimeHours(hours);
        return hours;
    }

    /**
     * Checks if the entered hour is between the range
     *
     * @param hours the entered hours is askTimeHours
     * @return returns the string of hours but a checked one if it is in range
     */
    public int controlTimeHours(String hours) {
        int controleHours = Integer.parseInt(hours);
        if (controleHours >= 24 || controleHours < 0) {
            System.out.println("A day has the hours between 0 and 23.");
            askTimeHours();
        }
        return controleHours;
    }

    /**
     * Asks the user for the minutes
     *
     * @return the entered minutes in a string
     */
    public String askMinutes() {
        String minutes = keyboard.askForText("What are the minutes?");
        controlTimeMinutes(minutes);
        return minutes;
    }

    /**
     * Checks if the entered minutes are within the range of minutes(0-60)
     *
     * @param minutes the entered minutes in askMinutes
     * @return returns the checked minutes if it is in range
     */
    public int controlTimeMinutes(String minutes) {
        int controleMinutes = Integer.parseInt(minutes);
        if (controleMinutes >= 60 || controleMinutes < 0) {
            System.out.println("An hour has the minutes between 0 and 59");
            askMinutes();
        }
        return controleMinutes;
    }

    /**
     * Puts the entered hours and minutes into a LocalTime and formats it to yhe right format.
     *
     * @param text asks the question for a time.
     * @return returns a LocalTime made of the checked hour and the checked minutes.
     */
    public LocalTime getLocalTime(String text) {
        System.out.println(text);
        String dateTimeLine = controlTimeHours(askTimeHours()) + ":" + controlTimeMinutes(askMinutes());
        DateTimeFormatter startTijd = DateTimeFormatter.ofPattern("H:m");
        return LocalTime.parse(dateTimeLine, startTijd);
    }
}
