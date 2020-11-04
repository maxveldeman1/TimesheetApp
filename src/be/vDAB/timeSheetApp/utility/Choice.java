package be.vDAB.timeSheetApp.utility;

public class Choice {
    Keyboard keyboard = new Keyboard();
    public int choiceForDay(String text) {
        int choice = keyboard.askForInt(text);
        if (choice > 7 || choice < 1) {
            System.out.println("Choose a number between 1 and 7");
            choice =choiceForDay(text);
            return choice;
        } else {
        return choice;}
}
    public int choiceForMenu(String text) {
        int choice = keyboard.askForInt(text);
        if (choice > 8|| choice < 1) {
            System.out.println("Choose a number between 1 and 8");
            choice=choiceForMenu(text);
            return choice;
        } else {
            return choice;}


    }

}
