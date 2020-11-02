package be.vDAB.timeSheetApp.slots;

import java.time.LocalTime;
import java.util.Arrays;

public class TimeSlot implements Slot {
    String description;
    long[] minutesByType;
    LocalTime end;
    LocalTime start;
    long totalMinutes;



    public TimeSlot(LocalTime start, LocalTime end){
        setStart(start);
        setEnd(end);
    }
    public TimeSlot(LocalTime start, LocalTime end, String description) {
       this(start,end);
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
        LocalTime WORKDAY_END_HOUR = end;

    }

    @Override
    public LocalTime getEnd() {
        return end;
    }

    @Override
    public void setStart(LocalTime start) {
        LocalTime WORKDAY_START_HOUR = start;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (totalMinutes != timeSlot.totalMinutes) return false;
        if (description != null ? !description.equals(timeSlot.description) : timeSlot.description != null)
            return false;
        if (!Arrays.equals(minutesByType, timeSlot.minutesByType)) return false;
        if (end != null ? !end.equals(timeSlot.end) : timeSlot.end != null) return false;
        return start != null ? start.equals(timeSlot.start) : timeSlot.start == null;
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
        return "TimeSlot{" +
                "description='" + description + '\'' +
                ", minutesByType=" + Arrays.toString(minutesByType) +
                ", end=" + end +
                ", start=" + start +
                ", totalMinutes=" + totalMinutes +
                '}';
    }
}
