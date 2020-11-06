package be.vDAB.timeSheetApp.utility;


/**
 * Utility class that converts an amount of minutes to hours in a decimal form.
 * <pre>
 *     Processor pr = new Processor;
 *     pr.goFromMinutesToHours( 426);
 *
 *     this returns 7.1
 * </pre>
 */
public class Processor {
    public double goFromMinutesToHours(double minutes) {
        return minutes / 60;
    }
}
//TODO: I can use my NumberFormatter inside this class so I don't have to use it both in other classes!

