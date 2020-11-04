package be.vDAB.timeSheetApp.weeks;

import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkedWeek implements Week {

    public LocalDate firstDayOfWeek;
    public WorkedDay[] workweek = new WorkedDay[7];

    public void setFirstDayOfWeek() {
        Keyboard keyboard = new Keyboard();
        int count = 1;
        for (DayOfWeek d : DayOfWeek.values()) {
            System.out.format("%d. %s %n", count++, d);
        }
        int numberOfDay = keyboard.askForInt("Kies de begindag van uw werkweek:");
        if (numberOfDay > 7 || numberOfDay <= 0) {
            System.out.println("Kies een nummer van 1 tot 7");
            setFirstDayOfWeek();

        }
        else {
            firstDayOfWeek = LocalDate.of()}
        System.out.println("Uw eerste dag van de week is: " + firstDayOfWeek);
    }

    public LocalDate getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public WorkedDay[] getWorkweek() {
        return workweek;
    }

    public void setWorkweek(DayOfWeek[] workweek) {
        System.out.println("Dit is uw werkweek:");
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            workweek[i] = firstDayOfWeek.plus(i);
            System.out.println((i+1) +". " +workweek[i]);

            }

        }

    public boolean isCheckWorkWeekIsFilledIn() {
    for (DayOfWeek workedWeek: workweek){
        if (workedWeek != null) {
            return true;
        }
    }
        return false;
    }
}



