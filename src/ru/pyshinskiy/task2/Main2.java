package ru.pyshinskiy.task2;

import ru.pyshinskiy.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String firstName = scanner.next();
            if("exit".equalsIgnoreCase(firstName)) break;

            String lastName = scanner.next();
            if("exit".equalsIgnoreCase(lastName)) break;

            personList.add(new Person(firstName, lastName));
        }

        personList.forEach(System.out::println);
    }
}
