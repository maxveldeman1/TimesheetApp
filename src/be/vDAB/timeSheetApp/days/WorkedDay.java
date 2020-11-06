package be.vDAB.timeSheetApp.days;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.Slot;
import be.vDAB.timeSheetApp.slots.TimeSlot;
import be.vDAB.timeSheetApp.utility.Choice;
import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for creating a WorkedDay, however we only create this by creating a WorkedWeek in our program.
 *
 * @see be.vDAB.timeSheetApp.weeks.WorkedWeek;
 * @see DateTimeFormatter;
 * @see Keyboard
 * @see Slot;
 * @see Choice;
 */
public class WorkedDay implements Day {
    LocalDate date;
    Slot[] timeSlots = new Slot[0];
    Keyboard keyboard = new Keyboard();
    int numberOfTimeSlots = 0;
    int emptyPlace;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    Choice ch = new Choice();

    /**
     * Out of our WorkedWeek we get a date on which we create a WorkedDay on which we can use the methods in this class.
     *
     * @param date the date on which we use our methods.
     */
    public WorkedDay(LocalDate date) {
        setDate(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * This returns the timeSlots created in addSlot.
     */
    public Slot[] getTimeSlots() {
        return timeSlots;
    }


    /**
     * This adds a break or time slot to this Workeday
     */
    @Override
    public void addSlot() {
        Slot[] tijdelijkeArray = new Slot[timeSlots.length + 1];
        System.arraycopy(timeSlots, 0, tijdelijkeArray, 0, timeSlots.length);
        timeSlots = tijdelijkeArray;
        int choice = ch.choice("What do you want to add:" + "\n" + "1. Work slot." + "\n" + "2. Break slot." + "\n" + "----------------", 2);
        switch (choice) {
            case 1:
                addWorkSlot();
                break;
            case 2:
                addBreakSlot();
                break;

        }

    }


    /**
     * Add a break slot.
     */
    public void addBreakSlot() {
        System.out.println("Adding a new Break slot on " + date.getDayOfWeek() + ": " + date.format(formatter));
        BreakSlot breakSlot = new BreakSlot(date);
        breakSlot.setDescription(keyboard.askForText("Name your break slot:"));
        findEmptyPlaceInTimeSlots();
        timeSlots[emptyPlace] = breakSlot;
        System.out.println("Break slot added." + "\n" + "--------------------" + "\n" + "Returning to Menu");
        numberOfTimeSlots++;
    }

    /**
     * Add work slot
     */
    public void addWorkSlot() {
        System.out.println("Adding a new work slot moment on " + date.getDayOfWeek() + ": " + date.format(formatter));
        TimeSlot timeSlot = new TimeSlot(date);
        timeSlot.setDescription(keyboard.askForText("Name your work slot:"));
        findEmptyPlaceInTimeSlots();
        timeSlots[emptyPlace] = timeSlot;
        System.out.println("Work slot added." + "\n" + "--------------------" + "\n" + "Returning to Menu");
        numberOfTimeSlots++;
    }

    /**
     * Checks the array timeSlots where an empty space is to add a slot on.
     */
    private void findEmptyPlaceInTimeSlots() {
        for (int i = 0; i < timeSlots.length; i++) {
            if (timeSlots[i] == null) {
                emptyPlace = i;
            }
        }
    }

    /**
     * Prints out the date of the WorkedDay.
     */
    @Override
    public String toString() {
        return date.getDayOfWeek() + ": " + date.format(formatter);
    }

    /**
     * Removes a slot of the workedDay, it first checks if there are slots to remove and then gives you the choice
     * which time slot you want to remove.
     */
    @Override
    public void removeSlot() {
        if (numberOfTimeSlots == 0) {
            System.out.println("No slots added yet!" + "\n" + "Returning you to the menu." + "\n");
        } else {
            System.out.println("Which slot would you like to remove?: ");
            for (int j = 0; j < timeSlots.length; j++) {
                if (timeSlots[j] != null) {
                    System.out.println((j + 1) + ". " + timeSlots[j]);
                }
            }

            int removeSlotChoice = ch.choice("Please choose a number: ", numberOfTimeSlots);
            Slot[] tijdelijkeArray = new Slot[timeSlots.length - 1];
            int index = 0;
            for (int l = 0; l < timeSlots.length; l++) {
                if (l != removeSlotChoice - 1) {
                    tijdelijkeArray[index++] = timeSlots[l];
                }
            }
            numberOfTimeSlots--;
            timeSlots = tijdelijkeArray;
        }

    }

}

