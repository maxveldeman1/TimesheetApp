package be.vDAB.timeSheetApp.menu;

import be.vDAB.timeSheetApp.checks.BasicCheck;
import be.vDAB.timeSheetApp.checks.SpecificCheck;
import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.Choice;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;

/**
 * This class is the main hub of the program. From here we get the choice of what the user wants to do. It then links
 * all the classes in this program together.
 */
public class Menu {
    /**
     * Prints out a starting text.
     */
    public void bootUpMenu() {
        System.out.println("Welcome to our Time Sheet App" + "\n" +
                "We are booting up the system for you" + "\n" +
                ".   .   . Done" + "\n" +

                "+++++   =====   +++++");
    }

    /**
     * Prints a menu of choices, then lets you enter a choice, on base of that it leads you to the specific method.
     * after each method is completed it returns you to this menu. Only if you choose '8' will the program end.
     */
    public void askMenu() {
        WorkedWeek workedWeek = new WorkedWeek();
        Choice choice = new Choice();
        int menuChoice = 0;
        do {
            try {
                printMenu();
                menuChoice = choice.choice("Your choice:", 8);
                System.out.println("" + "\n" + "---------------------------");
                switch (menuChoice) {
                    case 1:
                        printHourlyRates();
                        break;
                    case 2:
                        initialiseWorkedWeek(workedWeek);
                        break;
                    case 3:
                        addingSlot(workedWeek, choice);
                        break;
                    case 4:
                        removingSlot(workedWeek, choice);
                        break;
                    case 5:
                        workedWeek.resetWorkedWeek();
                        break;
                    case 6:
                        printBasicCheck(workedWeek);
                        break;
                    case 7:
                        printSpecificCheck(workedWeek);
                        break;
                }
            } catch (Exception ex) {
                System.out.println("An error has occurred, returning to menu!");
                ex.printStackTrace();
            }
        } while (menuChoice != 8);

    }


    /**
     * Prints out the SpecificCheck.
     *
     * @see SpecificCheck
     */
    public void printSpecificCheck(WorkedWeek workedWeek) {
        if (workedWeek.isInitialised) {
            System.out.println("Printing detailed paycheck\n---------------------------");
            SpecificCheck specificCheck = new SpecificCheck(workedWeek);
            specificCheck.printWeekDetails();
            System.out.println("\nPrint complete\nReturning to menu.\n---------------------------");
        } else {
            System.out.println("Your workweek has not been started yet.\n---------------------------\n" +
                    "Returning to menu.");

        }
    }

    /**
     * Prints out the BasicCheck
     *
     * @see BasicCheck
     */
    public void printBasicCheck(WorkedWeek workedWeek) {
        if (workedWeek.isInitialised) {
            System.out.println("Printing paycheck\n---------------------------");
            BasicCheck basicCheck = new BasicCheck(workedWeek);
            basicCheck.printDayDetails();
            System.out.println("\nPrint complete\nReturning to menu.\n---------------------------");
        } else {
            System.out.println("Your workweek has not been started yet.\n---------------------------\n" +
                    "Returning to menu.");
        }
    }

    /**
     * Prints out the basic menu text.
     */
    public void printMenu() {
        System.out.println("+++++   =====   +++++" + "\n" +
                "What do you want to do?" + "\n" +
                "Type in the number that corresponds with your choice:" + "\n" +
                "1. Show the different Hourly rates." + "\n" +
                "2. Start a new workweek." + "\n" +
                "3. Add a Worked Moment or Break." + "\n" +
                "4. Remove a Worked Moment or Break." + "\n" +
                "5. Reset." + "\n" +
                "6. Print PayCheck." + "\n" +
                "7. Print Detailed PayCheck." + "\n" +
                "8. Quit Application.");
    }

    /**
     * Starts a new WorkedWeek
     *
     * @see WorkedWeek
     */
    public void initialiseWorkedWeek(WorkedWeek workedWeek) {
        if (workedWeek.isInitialised) {
            System.out.println("A week has already been started.\nIf you want to start a new one,\n" +
                    "please Reset the current week in the menu.\n---------------------------\n" +
                    "Returning to menu.");
        } else {
            workedWeek.initialiseWorkedWeek();
        }
    }

    /**
     * adds a Slot on a WorkedDay in your WorkedWeek
     *
     * @param workedWeek prints this workedweek and lets you choose om which day you want to add something
     * @param choice     the choice of the day.
     */
    public void addingSlot(WorkedWeek workedWeek, Choice choice) {
        WorkedDay huidigeWerkdag;
        if (workedWeek.isInitialised) {
            printWeekTimeSlots(workedWeek);
            int keuze = choice.choice("To which day do you want to add a work/break slot?", 7);
            huidigeWerkdag = workedWeek.getWorkedWeek()[keuze - 1];
            System.out.println("Adding a work slot on " + huidigeWerkdag);
            huidigeWerkdag.addSlot();

        } else {
            System.out.println("Your workweek has not been started yet.\n---------------------------\n" +
                    "Returning to menu.");
        }
    }

    /**
     * removes a slot on a WorkedDay in your WorkedWeek
     *
     * @param workedWeek prints this workedweek and lets you choose om which day you want to remove something
     * @param choice     the choice of day
     */
    public void removingSlot(WorkedWeek workedWeek, Choice choice) {
        WorkedDay huidigeWerkdag;
        if (workedWeek.isInitialised) {
            printWeekTimeSlots(workedWeek);
            int keuze = choice.choice("Of which day do you want to remove a work/break slot?", 7);
            huidigeWerkdag = workedWeek.getWorkedWeek()[keuze - 1];
            huidigeWerkdag.removeSlot();
            System.out.println("Your slot has been removed.\n---------------------------\n" +
                    "Returning to menu");
        } else {
            System.out.println("Your workweek has not been started yet.\n---------------------------\n" +
                    "Returning to menu.");
        }
    }

    /**
     * Prints out the WorkedWeek with its timeSlots
     *
     * @param workedWeek the workweek of which we will print the timeSlots
     */
    public void printWeekTimeSlots(WorkedWeek workedWeek) {
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            System.out.println((i + 1) + ". " + workedWeek.getWorkedWeek()[i]);
            for (int j = 0; j < workedWeek.getWorkedWeek()[i].getTimeSlots().length; j++) {
                if (workedWeek.getWorkedWeek()[i].getTimeSlots()[j] != null) {
                    System.out.println("        " + workedWeek.getWorkedWeek()[i].getTimeSlots()[j]);
                }
            }
        }
    }

    /**
     * Prints out a little ending menu at the end of the program
     */
    public void endMenu() {
        System.out.println("+++++   =====   +++++\nThank you for using our app!\n" +
                "We will close down everything and clean up for you.\n" +
                "  .   .   . Done!\nSee you soon!\n---------------------------");

    }

    /**
     * Prints out the rates at which you will work
     *
     * @see Rates;
     */
    public void printHourlyRates() {
        System.out.printf("Your hourly rates are:%n" +
                        "---------------------------%n" +
                        "From monday until friday: %n" +
                        "       - From 8h00-18h00: %.2f euro/h %n" +
                        "       - From 0h00-8h00 and from 18h00-24h00: %.2f euro/h %n" +
                        "Saturday: %.2f euro/h %n" +
                        "Sunday:   %.2f euro/h %n" +
                        "---------------------------%n", Rates.FRIDAY.getNormalHourlyRate()
                , Rates.FRIDAY.getOvertimeHourlyRate(),
                Rates.SATURDAY.getNormalHourlyRate(), Rates.SATURDAY.getNormalHourlyRate());
    }

}
