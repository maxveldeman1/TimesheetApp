package be.vDAB.timeSheetApp.utility;

/**
 * This class is a utility class where we take an integer, created by Keyboard, and check if the int isn't
 * out of bounds compared to the int max we are given in the param.
 * <pre>
 *     Choice ch = new Choice();
 *     ch.choice("Which day do you want to choose?", 8)
 * </pre>
 *
 * @see Keyboard;
 */
public class Choice {
    Keyboard keyboard = new Keyboard();

    public int choice(String text, int max) {
        int choice = keyboard.askForInt(text);
        if (choice > max || choice < 1) {
            System.out.println("Choose a number between 1 and " + max);
            choice = choice(text, max);
            return choice;
        } else {
            return choice;
        }
    }

}
