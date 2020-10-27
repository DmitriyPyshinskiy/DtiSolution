package ru.pyshinskiy.util;

import ru.pyshinskiy.entity.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static List<Person> excludeRepeatedByLastName(List<Person> personList) {
        Map<String, String> personMap = new HashMap<>();
        List<Person> processedPersonList = new ArrayList<>();
        personList.forEach(person -> personMap.put(person.getLastName(),person.getFirstName()));
        personMap.forEach((k,v) -> processedPersonList.add(new Person(v, k)));
        return processedPersonList;
    }

    public static boolean saveToFile(List<Person> personList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt")))
        {
            oos.writeObject(personList);
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }

    public static List<Person> readFromFile(List<Person> personList) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt")))
        {
            personList.addAll((List<Person>) ois.readObject());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return personList;
    }
}