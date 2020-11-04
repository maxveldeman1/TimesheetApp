package be.vDAB.timeSheetApp.slots;


import java.time.LocalTime;

public interface Slot {

    void setDescription(String description);
    String getDescription();

    void setMinutesByType(LocalTime start,LocalTime end);

    void setEnd(LocalTime end);
    LocalTime getEnd();

    void setStart(LocalTime start);

    LocalTime getStart();

    long getTotalMinutes();

    void printSlotInfo();


}
