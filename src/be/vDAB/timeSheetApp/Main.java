package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Processor;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
    Menu menu = new Menu();
    menu.runMenu();
    }

}
