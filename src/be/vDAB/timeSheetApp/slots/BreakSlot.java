package be.vDAB.timeSheetApp.slots;

import be.vDAB.timeSheetApp.rates.Rates;
import be.vDAB.timeSheetApp.utility.AskTime;
import be.vDAB.timeSheetApp.utility.Processor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class is used to create a Break slot on a WorkedDay, by giving it a LocalDate in can create everything
 * needed in a breakslot.
 *
 * @see Processor;
 * @see Slot;
 */
public class BreakSlot implements Slot {
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
    double overtimeHourlyRate;
    double normalHourlyRate;
    LocalDate date;

    public BreakSlot(LocalDate date) {
        beginEnEindtijdBepalen();
        setTotalMinutes(start, end);
        setMinutesByType(start, end);
        setDayOfWeek(date);
        setNormalHourlyRate(date);
        setOvertimeHourlyRate(date);
    }

    public void setDayOfWeek(LocalDate dayOfWeek) {
        date = dayOfWeek;
    }

    /**
     * Creates the starting and ending time and checks if the end isn't before the starting time.
     */
    private void beginEnEindtijdBepalen() {
        start = inputSlot("Give your starting time.");
        end = inputSlot("Give your ending time");
        checkIfEndHourIsBeforeStartTime();
    }

    /**
     * Checks if end isn't before the starting time, otherwise it will give an error and lets you re-enter a start and end.
     */
    private void checkIfEndHourIsBeforeStartTime() {
        if (end.isBefore(start)) {
            System.out.println("Please make sure your ending time is not before your starting time." + "\n" + "If your work time is spread across two days, make 2 separate time slots.");
            beginEnEindtijdBepalen();
        }
    }

    /**
     * By using AskTime utility class, it lets you enter a time.
     *
     * @param text the question which asks for a time.
     */
    public LocalTime inputSlot(String text) {
        AskTime askTime = new AskTime();
        return askTime.getLocalTime(text);
    }


    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Checks all the possibilities of your times to calculate the overtime and normal time worked.
     * This can be simplified, however i haven't found out how.
     *
     * @param start the start time needed to calculate the minutes with
     * @param end   the end time needed to calculate the minutes with
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
    public boolean isWorkSlot() {
        return false;
    }

    /**
     * calculates the total minutes between start and end time.
     */
    public void setTotalMinutes(LocalTime start, LocalTime end) {
        this.totalMinutes = ChronoUnit.MINUTES.between(start, end);
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
     * @param o auto generated
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakSlot breakSlot = (BreakSlot) o;

        if (totalMinutes != breakSlot.totalMinutes) return false;
        if (!Objects.equals(description, breakSlot.description))
            return false;
        if (!Arrays.equals(minutesByType, breakSlot.minutesByType)) return false;
        if (!Objects.equals(end, breakSlot.end)) return false;
        return Objects.equals(start, breakSlot.start);
    }

    /**
     * auto generated hashCode
     *
     * @return an integer hashcode
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
     * @return an outprint of the slot
     */
    @Override
    public String toString() {
        return "Break slot: " +
                "Description: " + description + '\'' +
                ", Hours: Extra Hours (" + String.format("%.2f", overtimeHourlyRate) + " euro/h): " + String.format("%.2fh", processor.goFromMinutesToHours(minutesByType[0])) + ", Normal hours (" + String.format("%.2f", normalHourlyRate) + "euro/h): " + String.format("%.2fh", processor.goFromMinutesToHours(minutesByType[1])) +
                ", Start: " + start +
                ", End: " + end +
                ", Total: " + totalMinutes + "min or " + String.format("%.2fh.", processor.goFromMinutesToHours(totalMinutes));
    }
}
