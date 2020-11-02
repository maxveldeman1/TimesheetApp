package be.vDAB.timeSheetApp.slots;

import java.time.LocalTime;

public interface Slot {

    void setDescription(String description);
    String getDescription();

    void setMinutesByType(long[] minutesByType);

    void setEnd(LocalTime end);
    LocalTime getEnd();

    void setStart(LocalTime start);

    LocalTime getStart();

    void setTotalMinutes(long totalMinutes);

    void printSlotInfo();


}
