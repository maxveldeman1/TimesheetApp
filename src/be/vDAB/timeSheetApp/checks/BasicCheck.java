package be.vDAB.timeSheetApp.checks;

import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.Processor;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;

public class BasicCheck{
    WorkedWeek workweek;
    long overMinutes =0;
    long somOverMinutes =0;
    long normalMinutes =0;
    long sumNormalMinutes =0;
    Processor processor = new Processor();

    public BasicCheck(WorkedWeek workWeek){
            setWorkweek(workWeek);
    }



    public void setWorkweek(WorkedWeek workweek) {
        this.workweek = workweek;
    }

    public void printDayDetails() { //print de hele werkweek met zijn time slots af
        for (int i = 0; i < DayOfWeek.values().length - 1; i++) {
            System.out.println(workweek.getWorkweek()[i]+"\n"+"");
            //print de werkweek af
            for (int j = 0; j < workweek.getWorkweek()[i].getTimeSlots().length; j++) {
                if (workweek.getWorkweek()[i].getTimeSlots()[j] != null) {
//                    System.out.println("        "+(j + 1) + ". " + workweek.getWorkweek()[i].getTimeSlots()[j]);
                    if (workweek.getWorkweek()[i].getTimeSlots()[j].isWorkslot()) {
                        somOverMinutes = overMinutes + workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[0];
                        overMinutes = somOverMinutes;
                        sumNormalMinutes = normalMinutes +workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[1];
                        normalMinutes = sumNormalMinutes;

                    } else{
                        somOverMinutes = overMinutes -workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[0];
                        overMinutes = somOverMinutes;
                        sumNormalMinutes = normalMinutes -workweek.getWorkweek()[i].getTimeSlots()[j].getMinutesByType()[1];
                        normalMinutes = sumNormalMinutes;
                    }

                }

            }
            double normalHours =processor.goFromMinutesToHours(sumNormalMinutes);
            double overHours =processor.goFromMinutesToHours(somOverMinutes);
            double normalRate =Rates.valueOf(workweek.getWorkweek()[i].getDate().getDayOfWeek().toString()).getNormalHourlyRate();
            double overtimeRate = Rates.valueOf(workweek.getWorkweek()[i].getDate().getDayOfWeek().toString()).getOvertimeHourlyRate();
            double totalEarned =  normalRate * normalHours +  overtimeRate *overHours;
            System.out.println("You have worked "+ somOverMinutes +"min or "+String.format("%.2f",overHours)+"h of " +
                    "extra time at "+String.format("%.2f euro/h.",overtimeRate));
            System.out.println("You have worked "+ sumNormalMinutes +"min or "+String.format("%.2f",normalHours)+"h " +
                    "of normal hours at "+String.format("%.2f euro/h.",normalRate));
            System.out.println("Total worked: "+(sumNormalMinutes+somOverMinutes)+"min or "+ String.format("%.2fh.",(overHours+normalHours)));
            System.out.println("");
            double btwPercentage=21;
            System.out.printf("Bruto: You have earned %.2f euro.\n" +
                    "Netto: You have earned %.2f euro.\n" +
                    "BTW: %.2f euro at %.2f%%. \n \n",totalEarned,((totalEarned*(100-btwPercentage))/100),((totalEarned*btwPercentage)/100),btwPercentage);
            somOverMinutes =0;
            overMinutes =0;
            normalMinutes =0;
            sumNormalMinutes =0;
        }

    }


}
