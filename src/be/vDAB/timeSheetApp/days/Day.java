package be.vDAB.timeSheetApp.days;

import be.vDAB.timeSheetApp.slots.Slot;

public interface Day {
    void removeSlot(Slot slot);

    void addSlot(Slot slot);

    long totalWorkedMinutes();

    void setMinutesByType(long[] minutesByType);


}
