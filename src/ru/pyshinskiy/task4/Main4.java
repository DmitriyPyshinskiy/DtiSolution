package ru.pyshinskiy.task4;

import ru.pyshinskiy.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1.Add");
        System.out.println("2.Show");
        System.out.println("3.Exit");

        while(true) {
            System.out.println("Enter you command: ");
            String action = scanner.next();
            if("Add".equalsIgnoreCase(action)) {
                System.out.println("Enter first and last name: ");
                String firstName = scanner.next();
                String lastName = scanner.next();
                personList.add(new Person(firstName, lastName));
                System.out.println("[successfully]");
            } else if("Show".equalsIgnoreCase(action)) {
                if(personList.size() == 0) System.out.println("Sorry, person list is empty");
                personList.forEach(System.out::println);
            } else if("Exit".equalsIgnoreCase(action)) {
                System.out.println("Good bay, my friend!");
                break;
            } else {
                System.out.println("You input wrong command, try again.");
            }
        }
    }
}

