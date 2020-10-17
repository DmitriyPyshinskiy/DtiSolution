package ru.pyshinskiy.task7;

import ru.pyshinskiy.entity.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main7 {
    public static void main(String[] args) throws Exception {
        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);

        completeMenu(menu);
        System.out.println(showMenu(menu));

        while(true) {
            System.out.println("Enter you command: ");
            String inputAction = scanner.nextLine();
            if("exit".equalsIgnoreCase(inputAction)) {
                System.out.println("Good bay, my friend!");
                break;
            }

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
        // Наименование пункта меню
        private String name;
        // Доступное действие
        private Exec exec;

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }
    }

    private static class Menu {
        private List<MenuItem> items = new ArrayList<>();
        private Scanner scanner;

        public Menu(Scanner scanner) {
            this.scanner = scanner;
        }
    }




    private static void completeMenu(Menu menu) {
        menu.items.add(new MenuItem("Add", data -> {
            System.out.println("Enter first and last name: ");
            String firstName = menu.scanner.nextLine();
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
        menu.items.add(new MenuItem("Save to file", data -> {
            System.out.println(saveToFile(data) ? "[successfully saved]" : "[saving failed]");}));
        menu.items.add(new MenuItem("Exit", data -> { }));

    }

    private static String showMenu(Menu menu) {
        StringBuilder listMenu = new StringBuilder("Menu:\n");
        for(int i = 0; i < menu.items.size(); i++) {
            listMenu.append(i+1).append(".").append(menu.items.get(i).name).append("\n");
        }
        return listMenu.toString();
    }

    private static List<Person> excludeRepeatedByLastName(List<Person> personList) {
        Map<String, String> personMap = new HashMap<>();
        List<Person> processedPersonList = new ArrayList<>();
        personList.forEach(person -> personMap.put(person.getLastName(),person.getFirstName()));
        personMap.forEach((k,v) -> processedPersonList.add(new Person(v, k)));
        return processedPersonList;
    }

    private static boolean saveToFile(List<Person> personList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt")))
        {
            personList.forEach(person -> {
                try {
                    oos.writeObject(person);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }

}
