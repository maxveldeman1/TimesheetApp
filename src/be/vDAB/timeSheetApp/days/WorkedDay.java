package be.vDAB.timeSheetApp.days;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.Slot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Keyboard;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class WorkedDay extends WorkedWeek implements Day {
    long[] minutesByType;
    DayOfWeek hourlyRate;
    LocalDate date;
    Slot[] timeSlots;
    Keyboard keyboard = new Keyboard();
    DayOfWeek dayOfWorkWeek;
    LocalTime begintijd;
    LocalTime eindtijd;

    public void setDayOfWorkweek(DayOfWeek dayOfWorkWeek){
//        welke dag wil je wijzigen? => keuze gebruiker => dat wordt onze workedday
//        eerst checken of werkweek null is? indien null => eerst werkweek laten invullen
//        if checkWorkedweek is filled in = true dan workedDay laten lopen, if is false dan automatisch naar
//        setworkweek
        System.out.println("We gaan checken ");
        if (!isCheckWorkWeekIsFilledIn()){
            System.out.println("Geef eerst uw begindag van uw werkweek in.");
            setFirstDayOfWeek();
            setWorkweek(workweek);
        }
        int cijferVanDag =keyboard.askForInt("Welke dag wilt u wijzigen van uw werkweek?(Geef het cijfer van de dag)");
        if (cijferVanDag > 7 || cijferVanDag <= 0) {
            System.out.println("Kies een nummer van 1 tot 7");
            setDayOfWorkweek(dayOfWorkWeek);
        }
        System.out.println("U heeft " +workweek[cijferVanDag-1] +" gekozen.");
        this.dayOfWorkWeek = workweek[cijferVanDag-1];


    }

    public DayOfWeek getDayOfWorkWeek() {
        return dayOfWorkWeek;
    }

    @Override
    public void addSlot() {
        int choice = keyboard.askForInt("What do you want to add:"+"\n"+ "1. Work moment." +"\n"+ "2. Break moment." + "\n" + "----------------");
        if (choice > 2 || choice <1) {
            System.out.println("Choose a number between 1 and 2.");
            addSlot();
        }
        setDayOfWorkweek(dayOfWorkWeek);
        if (choice ==1){
            System.out.println("Adding a new Work moment on " + getDayOfWorkWeek());
            inputSlot();
            TimeSlot timeSlot = new TimeSlot(begintijd,eindtijd);


        }
        if (choice ==2){
            System.out.println("Adding a new Break moment on " + getDayOfWorkWeek());
            inputSlot();
            BreakSlot breakSlot = new BreakSlot(begintijd, eindtijd);
//
        }




    }

    public void inputSlot() {
        AskTime askTime = new AskTime();
        LocalTime begintijd = askTime.getLocalTime("Give your starting time");
        LocalTime eindtijd = askTime.getLocalTime("Give your ending time");
        if (eindtijd.isBefore(begintijd)) {
            System.out.println("Please make sure your ending time is not before your starting time."+ "\n"+"If your work time is spread across two days, make 2 separate time slots.");
            inputSlot();
        }
    }

    @Override
    public void removeSlot(Slot slot) {

    }


    @Override
    public long totalWorkedMinutes() {
        return 0;
    }

    @Override
    public void setMinutesByType(long[] minutesByType) {

    }
}
