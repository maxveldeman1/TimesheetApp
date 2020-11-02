package be.vDAB.timeSheetApp.slots;

import java.time.LocalTime;

public class BreakSlot implements Slot {
    String description;
    long[] minutesByType;
    LocalTime end;
    LocalTime start;
    long totalMinutes;




    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setMinutesByType(long[] minutesByType) {

    }

    @Override
    public void setEnd(LocalTime end) {

    }

    @Override
    public LocalTime getEnd() {
        return end;
    }

    @Override
    public void setStart(LocalTime start) {

    }

    @Override
    public LocalTime getStart() {
        return start;
    }

    @Override
    public void setTotalMinutes(long totalMinutes) {

    }

    @Override
    public void printSlotInfo() {

    }
}
