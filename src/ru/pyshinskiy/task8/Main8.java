package ru.pyshinskiy.task8;

import ru.pyshinskiy.entity.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ru.pyshinskiy.util.Utils.*;

public class Main8 {
    private static final List<Person> personList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);

        completeMenu(menu);
        System.out.println(showMenu(menu));

        while(true) {
            System.out.println("Enter you command: ");
            String inputAction = scanner.nextLine();

            boolean isActionExist = menu.items.stream()
                    .anyMatch(item -> item.name.equalsIgnoreCase(inputAction));
            if(isActionExist) {
                for(MenuItem item : menu.items) {
                    if(item.name.equalsIgnoreCase(inputAction)) {
                        item.exec.exec(personList);
                    }
                }
            } else {
                System.out.println("You input wrong command, try again.");
            }
        }
    }



    private interface Exec {
        void exec(List<Person> data) throws Exception;
    }

    private static class MenuItem {
        private final String name;
        private final Exec exec;

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }
    }

    private static class Menu {
        private final List<MenuItem> items = new ArrayList<>();
        private final Scanner scanner;

        public Menu(Scanner scanner) {
            this.scanner = scanner;
        }
    }



    private static void completeMenu(Menu menu) {
        menu.items.add(new MenuItem("Add", data -> {
            System.out.println("Enter first name: ");
            String firstName = menu.scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = menu.scanner.nextLine();
            data.add(new Person(firstName, lastName));
            System.out.println("[successfully]"); }));

        menu.items.add(new MenuItem("Show", data -> {
            if(data.size() == 0) System.out.println("Sorry, person list is empty");
            data.forEach(System.out::println); }));

        menu.items.add(new MenuItem("Show sorted unique", data -> {
            if (data.size() == 0) System.out.println("Sorry, person list is empty");
            List<Person> finishedPersonList = excludeRepeatedByLastName(data);
            finishedPersonList.sort(Comparator.comparing(Person::getLastName));
            finishedPersonList.forEach(System.out::println); }));

        menu.items.add(new MenuItem("Save to file", data ->
                System.out.println(saveToFile(data) ? "[successfully saved]" : "[saving failed]")));
        menu.items.add(new MenuItem("Read from file", data -> { readFromFile(personList);
            System.out.println("Data was successfully read from file");}));

        menu.items.add(new MenuItem("Clear data in memory", data -> {clearDataInMemory(data);
            System.out.println("Data was successfully clean");}));

        menu.items.add(new MenuItem("Exit", data -> { System.out.println("Good bay, my friend!");
            System.exit(0); }));

    }

    private static String showMenu(Menu menu) {
        StringBuilder listMenu = new StringBuilder("Menu:\n");
        for(int i = 0; i < menu.items.size(); i++) {
            listMenu.append(i+1).append(".").append(menu.items.get(i).name).append("\n");
        }
        return listMenu.toString();
    }

    private static void clearDataInMemory(List<Person> personList) {
        personList.clear();
    }
}