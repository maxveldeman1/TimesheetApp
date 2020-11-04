package be.vDAB.timeSheetApp.days;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.Slot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class WorkedDay implements Day {
    long[] minutesByType;
    LocalDate date;
    Slot[] timeSlots = new Slot[50];
    Keyboard keyboard = new Keyboard();
    DayOfWeek dayOfWorkWeek;
    int numberOfTimeSlots =0;
    DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public WorkedDay(LocalDate date) {
    setDate(date);
    }


    public void initialiseWorkedDay() {

    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

//    public void setDayOfWorkweek(DayOfWeek dayOfWorkWeek){
////        welke dag wil je wijzigen? => keuze gebruiker => dat wordt onze workedday
////        eerst checken of werkweek null is? indien null => eerst werkweek laten invullen
////        if checkWorkedweek is filled in = true dan workedDay laten lopen, if is false dan automatisch naar
////        setworkweek
//        System.out.println("We gaan checken ");
//        if (!isCheckWorkWeekIsFilledIn()){
//            System.out.println("You haven't started a work week yet, please fill this in first.");
//          // return to menu.
//        }
//        int cijferVanDag =keyboard.askForInt("Welke dag wilt u wijzigen van uw werkweek?(Geef het cijfer van de dag)");
//        if (cijferVanDag > 7 || cijferVanDag <= 0) {
//            System.out.println("Kies een nummer van 1 tot 7");
//            setDayOfWorkweek(dayOfWorkWeek);
//        }
//        System.out.println("U heeft " +workweek[cijferVanDag-1] +" gekozen.");
//        this.dayOfWorkWeek = workweek[cijferVanDag-1];
//
//
//    }

//    public DayOfWeek getDayOfWorkWeek() {
//        return dayOfWorkWeek;
//    }

    @Override
    public void addSlot() {
        if (findEmptyPlaceInTimeSlots() < timeSlots.length) {
            int choice = getChoiceForSlot("What do you want to add:" + "\n" + "1. Work slot." + "\n" + "2. Break slot." + "\n" + "----------------");

            if (choice == 1) {
                System.out.println("Adding a new work slot moment on " + date.getDayOfWeek() + ": "+ date.format(formatter));
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setDescription(keyboard.askForText("Name your work slot:"));
                timeSlots[findEmptyPlaceInTimeSlots()] = timeSlot;
                System.out.println("Work slot added."+"\n"+"--------------------"+"\n"+"Returning to Menu");

                numberOfTimeSlots++;
               }
            if (choice == 2) {
                System.out.println("Adding a new Break slot on " + date.getDayOfWeek() + ": "+ date.format(formatter));
                BreakSlot breakSlot = new BreakSlot();
                breakSlot.setDescription(keyboard.askForText("Name your break slot:"));
                timeSlots[findEmptyPlaceInTimeSlots()] = breakSlot;
                System.out.println("Break slot added."+"\n"+"--------------------"+"\n"+"Returning to Menu");
                numberOfTimeSlots++;
            }
        } else{
            System.out.println("Memory full, please remove a time slot first.");
        }
    }

    @Override
    public long totalWorkedMinutes() {
        return 0;
    }

    public void printArrayOfSlotsWithoutNull() {
        for( Slot ts : timeSlots) {
            if (ts != null) {
                System.out.println(ts);
            }
        }
    }




    private int getChoiceForSlot(String text) {
        int choice = keyboard.askForInt(text);
        if (choice > 2 || choice < 1) {
            System.out.println("Choose a number between 1 and 2.");
            getChoiceForSlot(text);
        }
        return choice;
    }

    private int findEmptyPlaceInTimeSlots() {
        for (int i = 0; i < timeSlots.length; i++){
            if (timeSlots[i] == null){
               return i;
            }
        }
        return 51;
    }


    @Override
    public String toString() {

        return date.getDayOfWeek() + ": "+date.format(formatter);
    }

    @Override
    public void removeSlot() {
        if (numberOfTimeSlots == 0) {
            System.out.println("No slots added yet!"+"\n"+"Returning you to the menu."+"\n");
        } else {
            System.out.println("Which slot would you like to remove?: ");
            for (int j = 0; j < timeSlots.length; j++) {
                if (timeSlots[j] != null) {
                    System.out.println((j + 1) + ". " + timeSlots[j]);
                }

                int removeSlotchoice = keyboard.askForInt("Please choose a number: ");
                if (removeSlotchoice > numberOfTimeSlots || removeSlotchoice <= 0) {
                    System.out.println("This is not a valid timeslot!");
                    removeSlot();
                } else {
                    timeSlots[removeSlotchoice - 1] = null;
                    numberOfTimeSlots--;
                }
            }
        }
    }

    @Override
    public void setMinutesByType(long[] minutesByType) {


    }
}
