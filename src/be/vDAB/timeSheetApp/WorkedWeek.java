package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.utility.Keyboard;

import java.time.DayOfWeek;

public class WorkedWeek implements Week{
    Keyboard keyboard = new Keyboard();
    public DayOfWeek firstDayOfWeek;
    public DayOfWeek[] workweek = new DayOfWeek[7];

    public void setFirstDayOfWeek(){
        int count =1;
        for (DayOfWeek d: DayOfWeek.values()) {
            System.out.format("%d. %s %n",count++,d);
        }
        firstDayOfWeek =DayOfWeek.of(keyboard.askForInt("Kies de begindag van uw werkweek"));
    }

    public DayOfWeek getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setWorkweek(DayOfWeek[] workweek) {

    }
}
