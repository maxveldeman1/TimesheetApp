package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Processor;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
WorkedDay workedDay = new WorkedDay();
workedDay.addSlot();
workedDay.addSlot();

workedDay.printArrayOfSlotsWithoutNull();



    }
}
