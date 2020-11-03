package be.vDAB.timeSheetApp.days;

import be.vDAB.timeSheetApp.slots.Slot;
import be.vDAB.timeSheetApp.weeks.WorkedWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkedDay extends WorkedWeek implements Day {
    long[] minutesByType;
    DayOfWeek hourlyRate;
    LocalDate date;
    Slot[] timeSlots;


    public void getWorkedDay(){
//        welke dag wil je wijzigen? => keuze gebruiker => dat wordt onze workedday
//        eerst checken of werkweek null is? indien null => eerst werkweek laten invullen
//        if checkWorkedweek is filled in = true dan workedDay laten lopen, if is false dan automatisch naar
//        setworkweek
        System.out.println("We gaan checken");
        if (!isCheckWorkWeekIsFilledIn()){

            setFirstDayOfWeek();
            setWorkweek(getWorkweek());
        }


        System.out.println("Welke dag wilt u wijzigen van uw werkweek?");
        System.out.println(getWorkweek());
    }
    @Override
    public void addSlot(Slot slot) {

    }

    @Override
    public void removeSlot(Slot slot) {

    }


    @Override
    public long totalWorkedMinutes() {
        return 0;
    }

    @Override
    public void setMinutesByType(long[] minutesByType) {

    }
}
