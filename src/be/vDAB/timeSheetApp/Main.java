package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.days.Day;
import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.menu.Menu;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;

public class Main {
    public static void main(String[] args) {
        WorkedDay workedDay= new WorkedDay();
        workedDay.getWorkedDay();
    }

}
