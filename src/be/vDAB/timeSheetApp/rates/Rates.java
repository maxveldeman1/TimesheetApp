package be.vDAB.timeSheetApp.rates;

/**
 * Enum of all the days of the week with its rates/h for normal and extra hours, also with the index of the DayOfWeek.
 */
public enum Rates {
    MONDAY(20, 15, 1),
    TUESDAY(20, 15, 2),
    WEDNESDAY(20, 15, 3),
    THURSDAY(20, 15, 4),
    FRIDAY(20, 15, 5),
    SATURDAY(25, 25, 6),
    SUNDAY(35, 35, 7);

    double overtimeHourlyRate = 20;
    double normalHourlyRate = 15;
    int dayOfWeek;

    Rates(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    Rates(double overtimeHourlyRate, double normalHourlyRate, int dayOfWeek) {
        this(dayOfWeek);
        this.normalHourlyRate = normalHourlyRate;
        this.overtimeHourlyRate = overtimeHourlyRate;

    }

    /**
     * Returns the overtimeHourly Rate of a specific day
     *
     * @return gives the OvertimeHourlyRate
     */
    public double getOvertimeHourlyRate() {
        return overtimeHourlyRate;
    }

    /**
     * Returns the normalHourlyRate of a specific day
     *
     * @return gives the normalHourlyRate
     */
    public double getNormalHourlyRate() {
        return normalHourlyRate;
    }


}
