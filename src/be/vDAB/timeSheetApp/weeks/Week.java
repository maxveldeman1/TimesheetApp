package be.vDAB.timeSheetApp.weeks;


import be.vDAB.timeSheetApp.days.WorkedDay;

/**
 * Basic interface for WorkedWeek with the specific methods for WorkedWeek
 *
 * @see WorkedWeek;
 */
public interface Week {
    void setFirstDayOfWeek();

    void resetWorkedWeek();

    void setWorkedWeek();

    WorkedDay[] getWorkedWeek();
}
