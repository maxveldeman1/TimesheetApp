package be.vDAB.timeSheetApp.weeks;

import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class WorkedWeek implements Week {

    public LocalDate firstDayOfWeek;
    public WorkedDay[] workweek = new WorkedDay[7];
    public boolean isInitialised;
    DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void initialiseWorkweek(){
        System.out.println("Starting a new workweek");
        System.out.println("---------------------------");
        System.out.println("Give you starting date.");
        setFirstDayOfWeek();

        setWorkweek();
        isInitialised = true;
    }

    @Override
    public void setFirstDayOfWeek() {
        Keyboard keyboard = new Keyboard();
        int jaar = keyboard.askForInt("Give the year: ");
        while(jaar <2020) {
            System.out.println("Can not go in to the previous years");
            jaar = keyboard.askForInt("Give the year:");
        }
        int maand = keyboard.askForInt("Give the month: ");
        while(maand >12 || maand <1) {
            System.out.println("A years only has 12 months.");
            maand = keyboard.askForInt("Give the month:");
        }
        int dag = keyboard.askForInt("Give the day: ");
        while(dag > Month.of(maand).length(Year.isLeap(jaar)) || dag <1) {
            System.out.printf("%s has between 1 and %d days %n",Month.of(maand),Month.of(maand).length(Year.isLeap(jaar)));
            jaar = keyboard.askForInt("Give the day:");
        }
        firstDayOfWeek = LocalDate.of(jaar, maand, dag);
        System.out.println("Your first day of the week is: " +firstDayOfWeek.format(formatter));
    }


    public LocalDate getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public WorkedDay[] getWorkweek() {
        return workweek;
    }


    public void setWorkweek() {
        System.out.println("Dit is uw werkweek:");
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            WorkedDay workedDay = new WorkedDay(firstDayOfWeek.plusDays(i));
            workweek[i] = workedDay;
            System.out.println((i+1) +". " +workweek[i]);
            }
        System.out.println(""+"\n"+"----------------------------");
        System.out.println("Returning to menu");


    }

    public void resetWorkWeek() {
            firstDayOfWeek = null;
            for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
                workweek[i] = null;
            }
            System.out.println("Your workweek has been reset.");
            System.out.println("");
            System.out.println("Returning to menu");
            isInitialised =false;
        }

}



