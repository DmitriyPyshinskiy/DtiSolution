package menu;

import entity.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static main.java.util.MenuUtils.*;


public class MenuFactory {
    private static final Scanner scanner = new Scanner(System.in);

    public static Menu getMenuInstance() {
        return new Menu.MenuBuilder()
                .withItem(new Menu.MenuItem("Add", data -> {
                    System.out.println("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name: ");
                    String lastName = scanner.nextLine();
                    if(checkName(firstName) && checkName(lastName)) {
                        data.add(new Person(firstName, lastName));
                        System.out.println("[successfully]");
                    } else {
                        System.out.println("Wrong data. You entered not name or not last name.");
                    }}))
                .withItem(new Menu.MenuItem("Show", data -> {
                    if(data.size() == 0) System.out.println("Sorry, person list is empty");
                    data.forEach(System.out::println); }))
                .withItem(new Menu.MenuItem("Show sorted unique", data -> {
                    if (data.size() == 0) System.out.println("Sorry, person list is empty");
                    List<Person> finishedPersonList = excludeRepeatedByLastName(data);
                    finishedPersonList.sort(Comparator.comparing(Person::getLastName));
                    finishedPersonList.forEach(System.out::println); }))
                .withItem(new Menu.MenuItem("Save to file", data ->
                        System.out.println(saveToFile(data) ? "[successfully saved]" : "[saving failed]")))
                .withItem(new Menu.MenuItem("Exit", data -> { System.out.println("Good bay, my friend!");
                    System.exit(0); }))
                .withItem(new Menu.MenuItem("Read from file", data -> { readFromFile(data);
                    System.out.println("Data was successfully read from file");}))
                .withItem(new Menu.MenuItem("Clear data in memory", data -> {clearDataInMemory(data);
                    System.out.println("Data was successfully clean");}))
                .build();
    }
}
