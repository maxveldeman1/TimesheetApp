package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.menu.Menu;
import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Choice;
import be.vDAB.timeSheetApp.utility.Keyboard;
import be.vDAB.timeSheetApp.utility.Processor;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.bootUpMenu();
        menu.askMenu();
        menu.endMenu();
    }

    }






