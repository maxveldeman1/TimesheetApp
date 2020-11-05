package be.vDAB.timeSheetApp.days;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.Slot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class WorkedDay implements Day {
    LocalDate date;
    Slot[] timeSlots = new Slot[0];
    Keyboard keyboard = new Keyboard();
    int numberOfTimeSlots =0;
    DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public WorkedDay(LocalDate date) {
    setDate(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Slot[] getTimeSlots() {
        return timeSlots;
    }

    @Override
    public void addSlot() {
        Slot[] tijdelijkeArray = new Slot[timeSlots.length+1];
        System.arraycopy(timeSlots, 0, tijdelijkeArray, 0, timeSlots.length);
        timeSlots = tijdelijkeArray;
        if (findEmptyPlaceInTimeSlots() < timeSlots.length) {
            int choice = getChoiceForSlot("What do you want to add:" + "\n" + "1. Work slot." + "\n" + "2. Break slot." + "\n" + "----------------");


            if (choice == 1) {
                System.out.println("Adding a new work slot moment on " + date.getDayOfWeek() + ": "+ date.format(formatter));
                TimeSlot timeSlot = new TimeSlot(date);
                timeSlot.setDescription(keyboard.askForText("Name your work slot:"));
                timeSlots[findEmptyPlaceInTimeSlots()] = timeSlot;
                System.out.println("Work slot added."+"\n"+"--------------------"+"\n"+"Returning to Menu");

                numberOfTimeSlots++;
               }
            if (choice == 2) {
                System.out.println("Adding a new Break slot on " + date.getDayOfWeek() + ": "+ date.format(formatter));
                BreakSlot breakSlot = new BreakSlot(date);
                breakSlot.setDescription(keyboard.askForText("Name your break slot:"));
                timeSlots[findEmptyPlaceInTimeSlots()] = breakSlot;
                System.out.println("Break slot added."+"\n"+"--------------------"+"\n"+"Returning to Menu");
                numberOfTimeSlots++;
            }
        } else{
            System.out.println("Memory full, please remove a time slot first.");
        }
    }




    private int getChoiceForSlot(String text) {
        int choice = keyboard.askForInt(text);
        if (choice > 2 || choice < 1) {
            System.out.println("Choose a number between 1 and 2.");
            choice = getChoiceForSlot(text);
            return choice;
        } else {
        return choice;}
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
            }

                int removeSlotchoice = keyboard.askForInt("Please choose a number: ");
                if (removeSlotchoice > numberOfTimeSlots || removeSlotchoice <= 0) {
                    System.out.println(removeSlotchoice);
                    System.out.println(numberOfTimeSlots);
                    System.out.println("This is not a valid timeslot!");
                    removeSlot();
                } else {
                    Slot[] tijdelijkeArray = new Slot[timeSlots.length-1];
                    int index=0;
                    for(int l =0; l<timeSlots.length; l++){
                        if ( l != removeSlotchoice-1) {
                            tijdelijkeArray[index++] = timeSlots[l];
                        }
                    }
                    numberOfTimeSlots--;
                    timeSlots=tijdelijkeArray;
                }

            }

    }

    @Override
    public void setMinutesByType(long[] minutesByType) {




    }
}
