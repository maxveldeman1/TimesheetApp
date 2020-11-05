package be.vDAB.timeSheetApp.menu;

import be.vDAB.timeSheetApp.checks.BasicCheck;
import be.vDAB.timeSheetApp.checks.SpecificCheck;
import be.vDAB.timeSheetApp.days.WorkedDay;
import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.Choice;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;


public class Menu {

    public void bootUpMenu() {
        System.out.println("Welcome to our Time Sheet App" + "\n" +
                "We are booting up the system for you" + "\n" +
                ".   .   . Done" + "\n" +

                "+++++   =====   +++++");
    }

    public void askMenu() {
        WorkedWeek workedWeek = new WorkedWeek();
        Choice choice = new Choice();
        WorkedDay huidigeWerkdag;
        int menuChoice = 0;
        do {
            try {
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
                menuChoice = choice.choiceForMenu("Your choice:");
                System.out.println("" + "\n" + "---------------------------");
                switch (menuChoice) {
                    case 1:
                        printHourlyRates();
                        break;
                    case 2:
                        if (workedWeek.isInitialised) {
                            System.out.println("A week has already been started.\nIf you want to start a new one,\n" +
                                    "please Reset the current week in the menu.\n---------------------------\n" +
                                    "Returning to menu.");
                        } else {
                            workedWeek.initialiseWorkweek();
                        }
                        break;
                    case 3:
                        if (workedWeek.isInitialised) {
                            printWeekTimeSlots(workedWeek);
                            int keuze = choice.choiceForDay("To which day do you want to add a work/break slot?");
                            huidigeWerkdag = workedWeek.getWorkweek()[keuze - 1];
                            System.out.println("Adding a workslot on " + huidigeWerkdag);
                            huidigeWerkdag.addSlot();

                        } else {
                            System.out.println("Your workweek has not been started yet.\n---------------------------\n" +
                                    "Returning to menu.");
                        }
                        break;
                    case 4:
                        if (workedWeek.isInitialised) {
                            printWeekTimeSlots(workedWeek);
                            int keuze = choice.choiceForDay("Of which day do you want to remove a work/break slot?");
                            huidigeWerkdag = workedWeek.getWorkweek()[keuze - 1];
                            huidigeWerkdag.removeSlot();
                            System.out.println("Your slot has been removed.\n---------------------------\n" +
                                    "Returning to menu");
                        } else {
                            System.out.println("Your workweek has not been started yet.\n---------------------------\n" +
                                    "Returning to menu.");
                        }
                        break;
                    case 5:
                        workedWeek.resetWorkWeek();
                        break;
                    case 6:
                        System.out.println("Printing paycheck\n---------------------------");
                        BasicCheck basicCheck = new BasicCheck(workedWeek);
                        basicCheck.printDayDetails();
                        System.out.println("\nPrint complete\nReturning to menu.\n---------------------------");
                        break;
                    case 7:
                        System.out.println("Printing detailed paycheck\n---------------------------");
                        SpecificCheck specificCheck = new SpecificCheck(workedWeek);
                        specificCheck.printWeekDetails();
                        System.out.println("\nPrint complete\nReturning to menu.\n---------------------------");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("An error has occurred, returning to menu!");
                ex.printStackTrace();
            }
        } while (menuChoice != 8);

    }

    public void printWeekTimeSlots(WorkedWeek workedWeek) {
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            System.out.println((i + 1) + ". " + workedWeek.getWorkweek()[i]);
            for (int j = 0; j < workedWeek.getWorkweek()[i].getTimeSlots().length; j++) {
                if (workedWeek.getWorkweek()[i].getTimeSlots()[j] != null) {
                    System.out.println("        " + workedWeek.getWorkweek()[i].getTimeSlots()[j]);
                }
            }
        }
    }

    public void endMenu() {
        System.out.println("+++++   =====   +++++\nThank you for using our app!\n" +
                "We will close down everything and clean up for you.\n" +
                "  .   .   . Done!\nSee you soon!\n---------------------------");

    }

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
