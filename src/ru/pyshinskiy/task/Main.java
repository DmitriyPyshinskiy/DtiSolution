package ru.pyshinskiy.task;

import ru.pyshinskiy.entity.Person;

public class Main {

    public static void main(String[] args) {

        if(args.length < 2) {
            System.out.println("You don`t input parameters for Person");
        } else {
            Person person = new Person(args[0], args[1]);
            System.out.println(person);
        }
    }
}
