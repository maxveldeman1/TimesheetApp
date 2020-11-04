package be.vDAB.timeSheetApp.days;


public interface Day {
    void removeSlot();

    void addSlot();

    long totalWorkedMinutes();

    void setMinutesByType(long[] minutesByType);


}
