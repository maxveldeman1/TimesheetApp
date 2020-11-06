package be.vDAB.timeSheetApp.utility;

/**
 * Utility class which simply formats a number to the form: nn,nn.
 * <pre>
 *     NumberFormatter nf = new NumberFormatter();
 *     nf.toTwoDecimals(12.35456)
 *
 *     this returns: 12.35 in a string
 * </pre>
 */
public class NumberFormatter {

    public String toTwoDecimals(double number) {
        return String.format("%.2f", number);
    }
}
