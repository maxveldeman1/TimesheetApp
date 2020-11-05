package be.vDAB.timeSheetApp.checks;

import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.numberFormatter;
import be.vDAB.timeSheetApp.utility.Processor;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;
import java.util.Arrays;

public class SpecificCheck {
    WorkedWeek workweek;
    double overMinutes =0;
    double somOverMinutes =0;
    double normalMinutes =0;
    double sumNormalMinutes =0;
    Processor processor = new Processor();
    double btwPercentage=21;
    numberFormatter numberFormatter = new numberFormatter();




    public SpecificCheck(WorkedWeek workWeek){
        setWorkweek(workWeek);
    }

    public WorkedWeek getWorkweek() {
        return workweek;
    }

    public void setWorkweek(WorkedWeek workweek) {
        this.workweek = workweek;
    }

    public void printWeekDetails() { //print de hele werkweek met zijn time slots af
        double[] dayTotals = new double[7];
        for (int i = 0; i < DayOfWeek.values().length - 1; i++) {

            double normalRate = Rates.valueOf(workweek.getWorkweek()[i].getDate().getDayOfWeek().toString()).getNormalHourlyRate();
            double overtimeRate = Rates.valueOf(workweek.getWorkweek()[i].getDate().getDayOfWeek().toString()).getOvertimeHourlyRate();

            System.out.println(workweek.getWorkweek()[i]+"\n"+"");
            //print de werkweek af
            for (int j = 0; j < workweek.getWorkweek()[i].getTimeSlots().length; j++) {
                if (workweek.getWorkweek()[i].getTimeSlots()[j] != null) {
                      System.out.println("        "+(j + 1) + ". " + workweek.getWorkweek()[i].getTimeSlots()[j]);
                    if (workweek.getWorkweek()[i].getTimeSlots()[j].isWorkslot()) {

                        double slotOvertime = processor.goFromMinutesToHours(workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[0])*overtimeRate;
                        somOverMinutes = overMinutes + workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[0];
                        overMinutes = somOverMinutes;

                        double slotNormalTime = processor.goFromMinutesToHours(workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[1]) *normalRate;

                        sumNormalMinutes = normalMinutes +workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[1];
                        normalMinutes = sumNormalMinutes;
                        char sign ='+';
                        printSlotRates(slotOvertime, slotNormalTime,sign);

                    } else{
                        double slotOvertime = processor.goFromMinutesToHours(workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[0])*overtimeRate;
                        somOverMinutes = overMinutes - workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[0];
                        overMinutes = somOverMinutes;
                        double slotNormalTime = processor.goFromMinutesToHours(workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[1]) *normalRate;
                        sumNormalMinutes = normalMinutes -workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[1];
                        normalMinutes = sumNormalMinutes;
                        char sign ='-';
                        printSlotRates(slotOvertime,slotNormalTime,sign);

                    }



                }

            }double normalHours = processor.goFromMinutesToHours(normalMinutes);
            double overHours = processor.goFromMinutesToHours(overMinutes);
            double totalDayEarned =  normalRate * normalHours +  overtimeRate *overHours;
            printDaydetails(normalHours, overHours, normalRate, overtimeRate, totalDayEarned);
            somOverMinutes =0;
            overMinutes =0;
            normalMinutes =0;
            sumNormalMinutes =0;
            dayTotals[i] =totalDayEarned;

        }

        System.out.println("This week you earned:\n--------------------\n\nGross: " +Arrays.stream(dayTotals).sum() +" euro.\n"+
                "Net: "+(Arrays.stream(dayTotals).sum()*(100-btwPercentage)/100)+" euro.\n" +
                "BTW: "+((Arrays.stream(dayTotals).sum()*btwPercentage)/100)+" euro at "+btwPercentage+"%");
    }

    public void printSlotRates(double slotOvertime, double slotNormalTime, char sign ) {
        System.out.println("");
        System.out.println("              Gross earned this slot: Extra time: "+ sign+numberFormatter.toTwoDecimals(slotOvertime)+" euro, Normal time: "+ sign+numberFormatter.toTwoDecimals(slotNormalTime)+" euro, Total: "+ sign+numberFormatter.toTwoDecimals(slotOvertime + slotNormalTime)+" euro.");
        System.out.println("              Net earned this slot: Extra time: "+ sign+numberFormatter.toTwoDecimals((slotOvertime *(100-btwPercentage))/100)+" euro, Normal time: "+ sign+numberFormatter.toTwoDecimals((slotNormalTime *(100-btwPercentage))/100)+" euro, Total: "+ sign+numberFormatter.toTwoDecimals(((slotOvertime *(100-btwPercentage))/100)+((slotNormalTime *(100-btwPercentage))/100))+" euro.");
        System.out.println("");
    }

    public void printDaydetails(double normalHours, double overHours, double normalRate, double overtimeRate, double totalEarned) {
        System.out.println("");
        System.out.println("You have worked "+ numberFormatter.toTwoDecimals(somOverMinutes) +"min or "+ numberFormatter.toTwoDecimals(overHours)+"h of extra time at "+String.format("%.2f euro/h.", overtimeRate));
        System.out.println("You have worked "+ sumNormalMinutes +"min or "+ numberFormatter.toTwoDecimals(normalHours)+"h of normal hours at "+String.format("%.2f euro/h.", normalRate));
        System.out.println("Total worked: "+ numberFormatter.toTwoDecimals(sumNormalMinutes+somOverMinutes)+"min or "+ numberFormatter.toTwoDecimals(overHours + normalHours)+"h.");
        System.out.println("");

        System.out.printf("Gross:\nExtra time: you have earned %.2f euro.\nNormal time: you have earned %.2f euro\nTotal: %.2f euro\n\n" +
                "Net:\nExtra time: you have earned %.2f euro.\nNormal time: you have earned %.2f euro\nTotal: %.2f euro\n\n" +
                "BTW: %.2f euro at %.2f%%. \n \n",overHours*overtimeRate ,normalHours*normalRate,totalEarned,(((overHours*overtimeRate) *(100-btwPercentage))/100),(((normalHours*normalRate)*(100-btwPercentage) )/100),((totalEarned *(100-btwPercentage)/100)),((totalEarned *btwPercentage)/100),btwPercentage);
        System.out.println("--------------------");
    }

}
