package ru.pyshinskiy.task6;

import ru.pyshinskiy.entity.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ru.pyshinskiy.util.Utils.excludeRepeatedByLastName;
import static ru.pyshinskiy.util.Utils.saveToFile;

public class Main6 {
    private static final List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1.Add");
        System.out.println("2.Show");
        System.out.println("3.Exit");
        System.out.println("4.Show sorted unique");
        System.out.println("5.Save to file");

        label:
        while (true) {
            System.out.println("Enter you command: ");
            String action = scanner.nextLine();
            switch (action) {
                case "Add":
                    System.out.println("Enter first name: ");
                    String firstName = scanner.next();
                    System.out.println("Enter last name: ");
                    String lastName = scanner.next();
                    personList.add(new Person(firstName, lastName));
                    System.out.println("[successfully]");
                    break;
                case "Show":
                    if(personList.size() == 0) System.out.println("Sorry, person list is empty");
                    personList.forEach(System.out::println);
                    break;
                case "Show sorted unique":
                    if (personList.size() == 0) System.out.println("Sorry, person list is empty");
                    List<Person> finishedPersonList = excludeRepeatedByLastName(personList);
                    finishedPersonList.sort(Comparator.comparing(Person::getLastName));
                    finishedPersonList.forEach(System.out::println);
                    break;
                case "Save to file":
                    System.out.println(saveToFile(personList) ? "[successfully saved]" : "[saving failed]");
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
}