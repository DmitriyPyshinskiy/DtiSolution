package ru.pyshinskiy.task5;

import ru.pyshinskiy.entity.Person;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1.Add");
        System.out.println("2.Show");
        System.out.println("3.Exit");
        System.out.println("4.Show sorted unique");

        label:
        while (true) {
            System.out.println("Enter you command: ");
            String action = scanner.nextLine();
            switch (action) {
                case "Add":
                    System.out.println("Enter first and last name: ");
                    String firstName = scanner.nextLine();
                    String lastName = scanner.nextLine();
                    personList.add(new Person(firstName, lastName));
                    System.out.println("[successfully]");
                    break;
                case "Show":
                    if (personList.size() == 0) System.out.println("Sorry, person list is empty");
                    personList.forEach(System.out::println);
                    break;
                case "Show sorted unique":
                    if (personList.size() == 0) System.out.println("Sorry, person list is empty");
                    List<Person> finishedPersonList = excludeRepeatedByLastName(personList);
                    finishedPersonList.sort(Comparator.comparing(Person::getLastName));
                    finishedPersonList.forEach(System.out::println);
                    break;
                case "Exit":
                    System.out.println("Good bay, my friend!");
                    break label;
                default:
                    System.out.println("You input wrong command, try again.");
                    break;
            }
        }
    }

    private static List<Person> excludeRepeatedByLastName(List<Person> personList) {
        Map<String, String> personMap = new HashMap<>();
        List<Person> processedPersonList = new ArrayList<>();
        personList.forEach(person -> personMap.put(person.getLastName(),person.getFirstName()));
        personMap.forEach((k,v) -> processedPersonList.add(new Person(v, k)));
        return processedPersonList;
    }
}
