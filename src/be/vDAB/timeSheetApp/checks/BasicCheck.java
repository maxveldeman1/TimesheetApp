package be.vDAB.timeSheetApp.checks;

import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.Processor;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;

/**
 * This class only prints out the workweek, for each day of the week it also prints the total time worked
 * on that day and the total money earned for that day.
 * This class can be used the following way:
 * <pre>
 *     BasicCheck bc = new BasicCheck(WorkedWeek ww);
 *     bs.printDayDetails();
 * </pre>
 *
 * @see WorkedWeek
 */
public class BasicCheck {
    WorkedWeek workweek;
    long overMinutes = 0;
    long somOverMinutes = 0;
    long normalMinutes = 0;
    long sumNormalMinutes = 0;
    Processor processor = new Processor();

    /**
     * Constructor for BasicCheck.
     * Needs a WorkedWeek as param to be created.
     *
     * @param workWeek the initialised workweek
     * @see WorkedWeek;
     */
    public BasicCheck(WorkedWeek workWeek) {
        setWorkweek(workWeek);
    }


    public void setWorkweek(WorkedWeek workedWeek) {
        this.workweek = workedWeek;
    }


    /**
     * Prints out the whole workweek.
     * For each day of the workedWeek returns total time worked at both rates and how much you have earned that day.
     */
    public void printDayDetails() {
        for (int i = 0; i < DayOfWeek.values().length - 1; i++) {
            System.out.println(workweek.getWorkedWeek()[i] + "\n" + "");
            for (int j = 0; j < workweek.getWorkedWeek()[i].getTimeSlots().length; j++) {
                if (workweek.getWorkedWeek()[i].getTimeSlots()[j] != null) {
                    if (workweek.getWorkedWeek()[i].getTimeSlots()[j].isWorkSlot()) {
                        somOverMinutes = overMinutes + workweek.getWorkedWeek()[i].getTimeSlots()[j].getMinutesByType()[0];
                        overMinutes = somOverMinutes;
                        sumNormalMinutes = normalMinutes + workweek.getWorkedWeek()[i].getTimeSlots()[j].getMinutesByType()[1];

                    } else {
                        somOverMinutes = overMinutes - workweek.getWorkedWeek()[i].getTimeSlots()[j].getMinutesByType()[0];
                        overMinutes = somOverMinutes;
                        sumNormalMinutes = normalMinutes - workweek.getWorkedWeek()[i].getTimeSlots()[j].getMinutesByType()[1];
                    }
                    normalMinutes = sumNormalMinutes;
                }
            }
            double normalHours = processor.goFromMinutesToHours(sumNormalMinutes);
            double overHours = processor.goFromMinutesToHours(somOverMinutes);
            double normalRate = Rates.valueOf(workweek.getWorkedWeek()[i].getDate().getDayOfWeek().toString()).getNormalHourlyRate();
            double overtimeRate = Rates.valueOf(workweek.getWorkedWeek()[i].getDate().getDayOfWeek().toString()).getOvertimeHourlyRate();
            double totalEarned = normalRate * normalHours + overtimeRate * overHours;
            double btwPercentage = 21;
            printTimeWorkedAndEarningsOfDay(normalHours, overHours, normalRate, overtimeRate, totalEarned, btwPercentage);
            somOverMinutes = 0;
            overMinutes = 0;
            normalMinutes = 0;
            sumNormalMinutes = 0;
        }

    }

    /**
     * prints these param in a text.
     *
     * @param normalHours   the hours worked between 8:00 and 18:00
     * @param overHours     the hours not between 8:00 and 18:00
     * @param normalRate    the rate for working normal hours
     * @param overtimeRate  the rate for working overtime
     * @param totalEarned   total of normal hours times the normal rate and the overhours times the overtime rate.
     * @param btwPercentage basic percentage which we need to subtract of what is earned.
     */
    public void printTimeWorkedAndEarningsOfDay(double normalHours, double overHours, double normalRate, double overtimeRate, double totalEarned, double btwPercentage) {
        System.out.println("You have worked " + somOverMinutes + "min or " + String.format("%.2f", overHours) + "h of " +
                "extra time at " + String.format("%.2f euro/h.", overtimeRate));
        System.out.println("You have worked " + sumNormalMinutes + "min or " + String.format("%.2f", normalHours) + "h " +
                "of normal hours at " + String.format("%.2f euro/h.", normalRate));
        System.out.println("Total worked: " + (sumNormalMinutes + somOverMinutes) + "min or " + String.format("%.2fh.", (overHours + normalHours)));
        System.out.println("");

        System.out.printf("Gross: You have earned %.2f euro.\n" +
                "Net: You have earned %.2f euro.\n" +
                "BTW: %.2f euro at %.2f%%. \n \n", totalEarned, ((totalEarned * (100 - btwPercentage)) / 100), ((totalEarned * btwPercentage) / 100), btwPercentage);
    }


}
