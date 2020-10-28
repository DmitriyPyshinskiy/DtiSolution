package util;


import entity.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean checkNameAndSurname(String name, String surname) {
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ'][a-zA-Z-а-яА-ЯёЁ' ]+[a-zA-Zа-яА-ЯёЁ']?$");
        Matcher m1 = pattern.matcher(name);
        Matcher m2 = pattern.matcher(surname);
        return m1.find() && m2.find();
    }

    public static List<Person> excludeRepeatedByLastName(List<Person> personList) {
        Map<String, String> personMap = new HashMap<>();
        List<Person> processedPersonList = new ArrayList<>();
        personList.forEach(person -> personMap.put(person.getLastName(),person.getFirstName()));
        personMap.forEach((k,v) -> processedPersonList.add(new Person(v, k)));
        return processedPersonList;
    }

    public static void saveToFile(List<Person> personList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt")))
        {
            oos.writeObject(personList);
        }
        catch(Exception ex) {
            System.out.println("[saving failed]");
        }
        System.out.println("[successfully saved]");
    }

    public static void readFromFile(List<Person> personList) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt")))
        {
            personList.addAll((List<Person>) ois.readObject());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("[successfully read]");
    }

    public static void clearDataInMemory(List<Person> personList) {
        personList.clear();
        System.out.println("[successfully cleaned]");
    }

    public static Scanner scanner() { return scanner;
    }
}
