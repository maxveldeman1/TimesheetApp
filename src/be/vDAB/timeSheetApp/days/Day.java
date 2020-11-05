package be.vDAB.timeSheetApp.days;


public interface Day {
    void removeSlot();

    void addSlot();



    void setMinutesByType(long[] minutesByType);


}
