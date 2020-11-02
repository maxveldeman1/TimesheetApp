package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Processor;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
//        AskTime uren = new AskTime();
//        LocalTime beginuur =uren.getLocalTime("Het beginuur:");
//        LocalTime einduur = uren.getLocalTime("Het eind uur:");
//        LocalTime beginPauze = uren.getLocalTime("Beginuur Pauze");
//        LocalTime eindePauze = uren.getLocalTime("Einduur Pauze");
//        TimeSlot werkDag = new TimeSlot(beginuur,einduur);
//        BreakSlot pauze = new BreakSlot(beginPauze,eindePauze);
//
//        System.out.println(werkDag.getTotalMinutes());
//        Processor processor = new Processor();
//       processor.goFromMinutesToHours(werkDag.getTotalMinutes());
//
        WorkedWeek workedWeek = new WorkedWeek();
        workedWeek.setFirstDayOfWeek();
    }

}
