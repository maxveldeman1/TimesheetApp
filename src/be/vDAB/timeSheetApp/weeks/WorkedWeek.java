package be.vDAB.timeSheetApp.weeks;

import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * This class mainly creates a workedWeek, which is an array of WorkedDays.
 * this class can be used the following way:
 * <pre>
 *     WorkedWeek ww = new WorkedWeek();
 *     ww.initialiseWorkWeek;
 * </pre>
 *
 * @see be.vDAB.timeSheetApp.weeks.Week;
 * @see WorkedDay;
 * @see Week;
 */
public class WorkedWeek implements Week {
    public LocalDate firstDayOfWeek;
    public WorkedDay[] workedWeek = new WorkedDay[7];
    public boolean isInitialised;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * A small method that prints some text and sets-up the workedWeek.
     */
    public void initialiseWorkedWeek() {
        System.out.println("Starting a new workweek");
        System.out.println("---------------------------");
        System.out.println("Give you starting date.");
        setFirstDayOfWeek();

        setWorkedWeek();
        isInitialised = true;
    }

    /**
     * Asks for an input for setting up the first day of the workedWeek, also checks of the given input is valid
     * for the year, month, and day.
     */
    @Override
    public void setFirstDayOfWeek() {
        Keyboard keyboard = new Keyboard();
        int jaar = keyboard.askForInt("Give the year: ");
        while (jaar < 2020) {
            System.out.println("Can not go in to the previous years");
            jaar = keyboard.askForInt("Give the year:");
        }
        int maand = keyboard.askForInt("Give the month: ");
        while (maand > 12 || maand < 1) {
            System.out.println("A years only has 12 months.");
            maand = keyboard.askForInt("Give the month:");
        }
        int dag = keyboard.askForInt("Give the day: ");
        while (dag > Month.of(maand).length(Year.isLeap(jaar)) || dag < 1) {
            System.out.printf("%s has between 1 and %d days %n", Month.of(maand), Month.of(maand).length(Year.isLeap(jaar)));
            jaar = keyboard.askForInt("Give the day:");
        }
        firstDayOfWeek = LocalDate.of(jaar, maand, dag);
        System.out.println("Your first day of the week is: " + firstDayOfWeek.format(formatter));
    }

    /**
     * returns the arrays of workedDays called workedWeek.
     */
    @Override
    public WorkedDay[] getWorkedWeek() {
        return workedWeek;
    }

    /**
     * By taking the firstDayOfWeek this generates the rest of the WorkedWeek based on the input of the user in
     * firstDayOfWeek.
     */
    @Override
    public void setWorkedWeek() {
        System.out.println("Dit is uw werkweek:");
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            WorkedDay workedDay = new WorkedDay(firstDayOfWeek.plusDays(i));
            workedWeek[i] = workedDay;
            System.out.println((i + 1) + ". " + workedWeek[i]);
        }
        System.out.println("" + "\n" + "----------------------------");
        System.out.println("Returning to menu");


    }

    /**
     * Method that empties the WorkedDay array so new input can be given.
     */
    @Override
    public void resetWorkedWeek() {
        firstDayOfWeek = null;
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            workedWeek[i] = null;
        }
        System.out.println("Your workweek has been reset.");
        System.out.println("");
        System.out.println("Returning to menu");
        isInitialised = false;
    }

}



