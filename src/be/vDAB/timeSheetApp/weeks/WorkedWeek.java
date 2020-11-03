package be.vDAB.timeSheetApp.weeks;

import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;

public class WorkedWeek implements Week {

    public DayOfWeek firstDayOfWeek;
    public DayOfWeek[] workweek = new DayOfWeek[7];

    public void setFirstDayOfWeek() {
        Keyboard keyboard = new Keyboard();
        int count = 1;
        for (DayOfWeek d : DayOfWeek.values()) {
            System.out.format("%d. %s %n", count++, d);
        }
        int numberOfDay = keyboard.askForInt("Kies de begindag van uw werkweek:");
        if (numberOfDay > 7 || numberOfDay <= 0) {
            System.out.println("Kies een nummer van 1 tot 7");
            setFirstDayOfWeek();

        }
        else {firstDayOfWeek = DayOfWeek.of(numberOfDay);}

    }

    public DayOfWeek getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public DayOfWeek[] getWorkweek() {
        return workweek;
    }

    public void setWorkweek(DayOfWeek[] workweek) {
        for (int i = 0; i <= DayOfWeek.values().length - 1; i++) {
            workweek[i] = firstDayOfWeek.plus(i);
                System.out.println(workweek[i]);

            }

        }

//        public boolean checkWorkWeekIsFilledIn
//                true= return
//                false= setworkweek
//
    public boolean isCheckWorkWeekIsFilledIn() {
    for (DayOfWeek workedWeek: workweek){
        if (workedWeek != null) {
            return true;
        }
    }
        return false;
    }
}



