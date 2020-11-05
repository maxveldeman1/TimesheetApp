package be.vDAB.timeSheetApp.slots;


import java.time.LocalDate;
import java.time.LocalTime;

public interface Slot {

    void setDescription(String description);
    String getDescription();

    void setMinutesByType(LocalTime start,LocalTime end);
    long[] getMinutesByType();
    boolean isWorkslot();
    void setEnd(LocalTime end);
    LocalTime getEnd();

    void setStart(LocalTime start);
    LocalDate getDate();

    LocalTime getStart();

    long getTotalMinutes();

    void printSlotInfo();


}
