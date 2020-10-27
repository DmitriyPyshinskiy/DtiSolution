package ru.pyshinskiy.task1;

import ru.pyshinskiy.entity.Person;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.next();
        String lastName = scanner.next();

        Person person = new Person(firstName, lastName);
        System.out.println(person);
    }
}