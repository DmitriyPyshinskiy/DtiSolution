package ru.pyshinskiy.task3;

import ru.pyshinskiy.entity.Person;

import java.util.*;

public class Main3 {
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

        personList.sort(Comparator.comparing(Person::getLastName));
        personList.forEach(System.out::println);
    }
}
