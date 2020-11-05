package be.vDAB.timeSheetApp.utility;

import java.util.Formatter;

public class numberFormatter {
    Formatter formatter = new Formatter();
    public String toTwoDecimals(double number){
        return String.format("%.2f",number);
    }
}
