package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.utility.AskTime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AskTime askTime = new AskTime();

        askTime.getLocalTime(askTime.controlTimeHours(askTime.askStartTimeHours()), askTime.controlTimeMinutes(askTime.askStartMinutes()));





    }

    }

