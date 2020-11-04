package be.vDAB.timeSheetApp.slots;

import be.vDAB.timeSheetApp.utility.AskTime;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class BreakSlot implements Slot {
    String description;
    long[] minutesByType;
    LocalTime end;
    LocalTime start;
    long totalMinutes;

   public BreakSlot() {
       beginEnEindtijdBepalen();
       setTotalMinutes(start, end);
   }
    public BreakSlot(LocalTime start, LocalTime end) {
    this();
    setTotalMinutes(start, end);

    }

    public BreakSlot(LocalTime start,LocalTime end, String description) {
    this(start, end);
    setDescription(description);
        setTotalMinutes(start, end);
    }
    private void beginEnEindtijdBepalen() {
        start = inputSlot("Give your starting time.");
        end = inputSlot("Give your ending time");
        checkIfEndhourIsBeforeStarttime();
    }

    private void checkIfEndhourIsBeforeStarttime() {
        if (end.isBefore(start)) {
            System.out.println("Please make sure your ending time is not before your starting time."+ "\n"+"If your work time is spread across two days, make 2 separate time slots.");
            beginEnEindtijdBepalen();
        }
    }
    public LocalTime inputSlot(String text) {
        AskTime askTime = new AskTime();
        LocalTime time = askTime.getLocalTime(text);
        return time;
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
    public void setMinutesByType(LocalTime start, LocalTime end) {

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
        return totalMinutes = ChronoUnit.MINUTES.between(start,end);

    }
    public void setTotalMinutes(LocalTime start, LocalTime end) {
       this.totalMinutes = ChronoUnit.MINUTES.between(start,end);
    }
    @Override
    public void printSlotInfo() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakSlot breakSlot = (BreakSlot) o;

        if (totalMinutes != breakSlot.totalMinutes) return false;
        if (description != null ? !description.equals(breakSlot.description) : breakSlot.description != null)
            return false;
        if (!Arrays.equals(minutesByType, breakSlot.minutesByType)) return false;
        if (end != null ? !end.equals(breakSlot.end) : breakSlot.end != null) return false;
        return start != null ? start.equals(breakSlot.start) : breakSlot.start == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(minutesByType);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (int) (totalMinutes ^ (totalMinutes >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BreakSlot: " +
                "Description: " + description + '\'' +
                ", MinutesByType: " + Arrays.toString(minutesByType) +
                ", Start: " + start +
                ", End: " + end +
                ", TotalMinutes: " + totalMinutes;
    }
}
