package be.vDAB.timeSheetApp.rates;

public enum Rates {
    MONDAY(20,15,1),
    TUESDAY(20,15,2),
    WEDNESDAY(20,15,3),
    THURSDAY(20,15,4),
    FRIDAY(20,15,5),
    SATURDAY(25,25,6),
    SUNDAY(35,35,7);

    double overtimeHourlyRate = 20;
    double normalHourlyRate =15;


    int dayOfWeek;

    Rates(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    Rates(double overtimeHourlyRate,double normalHourlyRate, int dayOfWeek){
        this(dayOfWeek);
        this.normalHourlyRate = normalHourlyRate;
        this.overtimeHourlyRate = overtimeHourlyRate;

    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getOvertimeHourlyRate() {
        return overtimeHourlyRate;
    }


    public double getNormalHourlyRate() {
        return normalHourlyRate;
    }



}
