package be.vDAB.timeSheetApp.slots;

import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Processor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * This class is used to create a Work slot on a WorkedDay, by giving it a LocalDate in can create everything
 * needed in a workslot.
 *
 * @see Processor;
 * @see Slot;
 */
public class TimeSlot implements Slot {
    String description;
    long[] minutesByType = new long[2];
    LocalTime end;
    LocalTime start;
    long totalMinutes;
    final LocalTime START_NORMAL_HOURS = LocalTime.of(8, 0);
    final LocalTime END_NORMAL_HOURS = LocalTime.of(18, 0);
    long overUren;
    long normaleUren;
    Processor processor = new Processor();
    LocalDate date;
    double overtimeHourlyRate;
    double normalHourlyRate;

    public TimeSlot(LocalDate date) {
        beginEnEindtijdBepalen();
        setTotalMinutes(start, end);
        setMinutesByType(start, end);
        setDayOfWeek(date);
        setNormalHourlyRate(date);
        setOvertimeHourlyRate(date);
    }

    public void setDayOfWeek(LocalDate dayOfWeek) {
        this.date = dayOfWeek;
    }

    /**
     * Creates the starting and ending time and checks if the end isn't before the starting time.
     */
    private void beginEnEindtijdBepalen() {
        start = inputSlot("Give your starting time.");
        end = inputSlot("Give your ending time");
        checkIfEndhourIsBeforeStarttime();
    }

    /**
     * Checks if end isn't before the starting time, otherwise it will give an error and lets you re-enter a start and end.
     */
    private void checkIfEndhourIsBeforeStarttime() {
        if (end.isBefore(start)) {
            System.out.println("Please make sure your ending time is not before your starting time." + "\n" + "If your work time is spread across two days,\nmake 2 separate time slots split across the different days. .");
            beginEnEindtijdBepalen();
        }
    }

    /**
     * By using AskTime utility class, it lets you enter a time.
     *
     * @param text
     */
    public LocalTime inputSlot(String text) {
        AskTime askTime = new AskTime();
        LocalTime time = askTime.getLocalTime(text);
        return time;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * calculates the total minutes between start and end time.
     */
    public void setTotalMinutes(LocalTime start, LocalTime end) {
        this.totalMinutes = ChronoUnit.MINUTES.between(start, end);

    }

    /**
     * Checks all the possibilities of your times to calculate the overtime and normal time worked.
     * This can be simplified, however i haven't found out how.
     *
     * @param start
     * @param end
     */
    @Override
    public void setMinutesByType(LocalTime start, LocalTime end) {
        if (start.isBefore(START_NORMAL_HOURS) && end.isBefore(START_NORMAL_HOURS)) {
            overUren = ChronoUnit.MINUTES.between(start, end);
            minutesByType[0] = overUren;
        } else if (start.isBefore(START_NORMAL_HOURS) && (end.isAfter(START_NORMAL_HOURS) && end.isBefore(END_NORMAL_HOURS))) {
            overUren = ChronoUnit.MINUTES.between(start, START_NORMAL_HOURS);
            normaleUren = ChronoUnit.MINUTES.between(START_NORMAL_HOURS, end);
            minutesByType[0] = overUren;
            minutesByType[1] = normaleUren;
        } else if (start.isAfter(START_NORMAL_HOURS) && end.isBefore(END_NORMAL_HOURS)) {
            normaleUren = ChronoUnit.MINUTES.between(start, end);
            minutesByType[1] = normaleUren;
        } else if ((start.isAfter(START_NORMAL_HOURS) && start.isBefore(END_NORMAL_HOURS)) && end.isAfter(END_NORMAL_HOURS)) {
            normaleUren = ChronoUnit.MINUTES.between(start, END_NORMAL_HOURS);
            overUren = ChronoUnit.MINUTES.between(END_NORMAL_HOURS, end);
            minutesByType[0] = overUren;
            minutesByType[1] = normaleUren;
        } else if (start.isAfter(END_NORMAL_HOURS) && end.isAfter(END_NORMAL_HOURS)) {
            overUren = ChronoUnit.MINUTES.between(start, end);
            minutesByType[0] = overUren;
        } else if (start.isBefore(START_NORMAL_HOURS) && end.isAfter(END_NORMAL_HOURS)) {
            overUren = ChronoUnit.MINUTES.between(start, START_NORMAL_HOURS) + ChronoUnit.MINUTES.between(END_NORMAL_HOURS, end);
            normaleUren = ChronoUnit.MINUTES.between(START_NORMAL_HOURS, END_NORMAL_HOURS);
            minutesByType[0] = overUren;
            minutesByType[1] = normaleUren;
        }


    }

    @Override
    public long[] getMinutesByType() {
        return minutesByType;
    }

    /**
     * returns a boolean that is used to differentiate the break and work slots.
     */
    @Override
    public boolean isWorkslot() {
        return true;
    }

    /**
     * Goes in Enum Rates and picks the normalHourlyRate linked with this date.
     *
     * @param date;
     * @see Rates;
     */
    public void setNormalHourlyRate(LocalDate date) {
        this.normalHourlyRate = Rates.valueOf(date.getDayOfWeek().toString()).getNormalHourlyRate();
    }

    /**
     * Goes in Enum Rates and picks the OvertimeHourlyRate linked with this date.
     *
     * @param date;
     * @see Rates;
     */
    public void setOvertimeHourlyRate(LocalDate date) {
        this.overtimeHourlyRate = Rates.valueOf(date.getDayOfWeek().toString()).getOvertimeHourlyRate();
    }

    /**
     * auto generated equals method
     *
     * @param o
     * @return
     */
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

    /**
     * auto generated hashCode
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(minutesByType);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (int) (totalMinutes ^ (totalMinutes >>> 32));
        return result;
    }

    /**
     * toString method of BreakSlot
     *
     * @return
     */
    @Override
    public String toString() {
        return "Work slot: " +
                "Description: '" + description + '\'' +
                ", Hours: Extra Hours (" + String.format("%.2f", overtimeHourlyRate) + " euro/h): " + String.format("%.2f", processor.goFromMinutesToHours(minutesByType[0])) + " h, Normal hours (" + String.format("%.2f", normalHourlyRate) + "euro/h): " + String.format("%.2f", processor.goFromMinutesToHours(minutesByType[1])) + "h" +
                ", Start: " + start +
                ", End: " + end +
                ", Total: " + totalMinutes + "min or " + String.format("%.2f", processor.goFromMinutesToHours(totalMinutes)) + "h.";
    }
}
