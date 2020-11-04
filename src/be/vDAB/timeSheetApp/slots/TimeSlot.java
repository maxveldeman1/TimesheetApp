package be.vDAB.timeSheetApp.slots;

import be.vDAB.timeSheetApp.utility.AskTime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class TimeSlot implements Slot {
    String description;
    long[] minutesByType = new long[2];
    LocalTime end;
    LocalTime start;
    long totalMinutes;
    boolean isNormalHour;
    final LocalTime START_NORMAL_HOURS = LocalTime.of(8,0);
    final LocalTime END_NORMAL_HOURS = LocalTime.of(18,0);
    long overUren;
    long normaleUren;


    public TimeSlot(){
        beginEnEindtijdBepalen();
        setTotalMinutes(start,end);
        setMinutesByType(start,end);
    }
    public TimeSlot(LocalTime start, LocalTime end){
        this();
        setTotalMinutes(start, end);
    }
    public TimeSlot(LocalTime start, LocalTime end, String description) {
        setStart(start);
        setEnd(end);
        setDescription(description);
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
    public void setEnd(LocalTime end) {
        this.end = end;

    }

    @Override
    public LocalTime getEnd() {
        return end;
    }

    @Override
    public void setStart(LocalTime start) {
        this.start= start;


    }

    @Override
    public LocalTime getStart() {
        return start;
    }

    @Override
    public long getTotalMinutes() {
        return totalMinutes = ChronoUnit.MINUTES.between(start,end);

    }
    public void setTotalMinutes(LocalTime start, LocalTime end){
        this.totalMinutes = ChronoUnit.MINUTES.between(start,end);

    }
    @Override
    public void setMinutesByType(LocalTime start,LocalTime end) {
      if(start.isBefore(START_NORMAL_HOURS) && end.isBefore(START_NORMAL_HOURS)) {
          overUren=ChronoUnit.MINUTES.between(start,end);
          minutesByType[0] = overUren;
      } else if (start.isBefore(START_NORMAL_HOURS) && (end.isAfter(START_NORMAL_HOURS) && end.isBefore(END_NORMAL_HOURS))) {
          overUren =   ChronoUnit.MINUTES.between(start, START_NORMAL_HOURS);
          normaleUren = ChronoUnit.MINUTES.between(START_NORMAL_HOURS, end);
          minutesByType[0] = overUren;
          minutesByType[1] = normaleUren;
      } else if(start.isAfter(START_NORMAL_HOURS) && end.isBefore(END_NORMAL_HOURS)){
          normaleUren =ChronoUnit.MINUTES.between(start,end);
          minutesByType[1] = normaleUren;
      } else if ((start.isAfter(START_NORMAL_HOURS) && start.isBefore(END_NORMAL_HOURS))&& end.isAfter(END_NORMAL_HOURS)) {
          normaleUren = ChronoUnit.MINUTES.between(start, END_NORMAL_HOURS);
          overUren = ChronoUnit.MINUTES.between(END_NORMAL_HOURS, end);
          minutesByType[0] = overUren;
          minutesByType[1] = normaleUren;
      } else if(start.isAfter(END_NORMAL_HOURS )&& end.isAfter(END_NORMAL_HOURS)) {
          overUren = ChronoUnit.MINUTES.between(start,end);
          minutesByType[0]= overUren;
      } else if(start.isBefore(START_NORMAL_HOURS) && end.isAfter(END_NORMAL_HOURS)) {
          overUren = ChronoUnit.MINUTES.between(start,START_NORMAL_HOURS)+ChronoUnit.MINUTES.between(END_NORMAL_HOURS,end);
      }








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
        return "TimeSlot: "  +
                "Description: '" + description + '\'' +
                ", MinutesByType: " + Arrays.toString(minutesByType) +
                ", Start: " + start +
                ", End: " + end +
                ", Total minutes: " + totalMinutes;
    }
}
