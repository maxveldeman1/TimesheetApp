package be.vDAB.timeSheetApp.menu;

import be.vDAB.timeSheetApp.utility.Keyboard;

import java.util.Scanner;

public class Menu {
    Scanner keyboard = new Scanner(System.in);
//opsplisten: askmenu, bootup menu, endmenu
    public void runMenu() {
        System.out.println("Welcome to our Time Sheet App" + "\n"+
                "We are booting up the system for you" +"\n"+
                ".   .   . Done" +"\n"+
//                = bootup menu
                "+++++   =====   +++++" +"\n"+
                "+++++   =====   +++++" +"\n"+
                "What do you want to do?" +"\n"+
                "Type in the number that corresponds with your choice:" +"\n"+
                "1. Show the different Hourly rates." +"\n"+
                "2. Start a new workweek." +"\n"+
                "3. Add a Worked Moment or Break." +"\n"+
                "4. Remove a Worked Moment or Break." +"\n"+
                "5. Reset." +"\n"+
                "6. Print PayCheck." +"\n"+
                "7. Print Detailed PayCheck." +"\n"+
//                askmenu
                "8. Quit Application." +"\n"+
//                endmenu
                "Your Choice: ");

    }
    String choice =keyboard.next();


}
