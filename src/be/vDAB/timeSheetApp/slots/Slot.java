package be.vDAB.timeSheetApp.slots;

import java.time.LocalTime;

/**
 * Interface for my break and work slot with the basic methods for Slots.
 */
public interface Slot {

    void setDescription(String description);

    void setMinutesByType(LocalTime start, LocalTime end);

    long[] getMinutesByType();

    boolean isWorkSlot();
}
