package be.vDAB.timeSheetApp.slots;

import be.vDAB.timeSheetApp.utility.AskTime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class BreakSlot implements Slot {
    String description;
    long[] minutesByType;
    LocalTime end;
    LocalTime start;
    long totalMinutes;

    public BreakSlot(LocalTime start, LocalTime end) {
    setEnd(end);
    setStart(start);
    }

    public BreakSlot(LocalTime start,LocalTime end, String description) {
    this(start, end);
    setDescription(description);
    }


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
        this.end = end;
    }

    @Override
    public LocalTime getEnd() {
        return end;
    }

    @Override
    public void setStart(LocalTime start) {
        this.start = start;


    }

    @Override
    public LocalTime getStart() {
        return start;
    }

    @Override
    public long getTotalMinutes() {
        return totalMinutes = ChronoUnit.MINUTES.between(getStart(),getEnd());

    }

    @Override
    public void printSlotInfo() {

    }
}
