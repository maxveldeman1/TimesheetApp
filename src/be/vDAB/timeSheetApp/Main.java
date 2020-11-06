package be.vDAB.timeSheetApp;

import be.vDAB.timeSheetApp.menu.Menu;

/**
 * This is the main class of the program, from here we go to our Menu and from there all the classes come together.
 * This creates a menu which only has 3 commands.
 *
 * @author maxve
 * @version 1.0
 * @see Menu;
 */
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.bootUpMenu();
        menu.askMenu();
        menu.endMenu();
    }

}






