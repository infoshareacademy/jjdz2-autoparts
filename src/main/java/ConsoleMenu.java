import org.json.JSONException;

import java.io.IOException;

public class ConsoleMenu {

    public static void main(String[] args) {
        // Local variable
        int swValue;

        // Display menu graphics
        System.out.println("============================");
        System.out.println("|       MENU AUTOPARTS     |");
        System.out.println("============================");
        System.out.println("| Options:                 |");
        System.out.println("|        1. Baza RU        |");
        System.out.println("|        2. Atena          |");
        System.out.println("|        3. Exit           |");
        System.out.println("============================");
        swValue = Keyin.inInt(" Select option: ");

        // Switch construct
        switch (swValue) {
            case 1:
                //System.out.println("Option 1 selected");
                try {
                    new Searcher().search();
                } catch (IOException e) {
                    System.out.println("Błąd odczytu!");
                } catch (JSONException jE) {
                    System.out.println("Błędny plik JSON1");
                }
                break;
            case 2:
                System.out.println("Option 2 selected");
                break;
            case 3:
                System.out.println("Exit selected");
                break;
            default:
                System.out.println("Invalid selection");
                break; // This break is not really necessary
        }
    }
}
