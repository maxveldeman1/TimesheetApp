package be.vDAB.timeSheetApp;

public enum Rates {
    MON(true),
    TUE(true),
    WED(true),
    FRI(true),
    SAT(false),
    SUN(false);

    double overtimeHourlyRate = 20;
    double normalHourlyRate =15;
    double saturdayHourlyRate =25;
    double sundayHourlyRate = 35;

    private boolean weekday;

    Rates(boolean weekday) {
        this.weekday = weekday;
    }

    public boolean isWeekday() {
        return weekday;
    }

    public double getOvertimeHourlyRate() {
        return overtimeHourlyRate;
    }

    public void setOvertimeHourlyRate(double overtimeHourlyRate) {
        this.overtimeHourlyRate = overtimeHourlyRate;
    }

    public double getNormalHourlyRate() {
        return normalHourlyRate;
    }

    public void setNormalHourlyRate(double normalHourlyRate) {
        this.normalHourlyRate = normalHourlyRate;
    }

    public double getSaturdayHourlyRate() {
        return saturdayHourlyRate;
    }

    public void setSaturdayHourlyRate(double saturdayHourlyRate) {
        this.saturdayHourlyRate = saturdayHourlyRate;
    }

    public double getSundayHourlyRate() {
        return sundayHourlyRate;
    }

    public void setSundayHourlyRate(double sundayHourlyRate) {
        this.sundayHourlyRate = sundayHourlyRate;
    }
}
