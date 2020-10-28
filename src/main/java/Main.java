

import entity.Person;

import menu.Menu;
import menu.MenuFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Menu menu = MenuFactory.getMenuInstance();
    private static final List<Person> personList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(menu.showMenu());

        while(true) {
            System.out.println("Enter you command: ");
            String action = scanner.nextLine().trim();

            boolean isActionExist = menu.getItems().stream()
                    .anyMatch(item -> item.getName().equalsIgnoreCase(action));
            if(isActionExist) {
                for(Menu.MenuItem item : menu.getItems()) {
                    if(item.getName().equalsIgnoreCase(action)) {
                        item.getExec().exec(personList);
                    }
                }
            } else {
                System.out.println("You entered wrong command, try again.");
            }
        }
    }
}