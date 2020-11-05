package be.vDAB.timeSheetApp;


import be.vDAB.timeSheetApp.menu.Menu;


public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.bootUpMenu();
        menu.askMenu();
        menu.endMenu();
    }

}






