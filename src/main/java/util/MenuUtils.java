package main.java.util;


import entity.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuUtils {

    public static boolean checkName(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

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

    public static void clearDataInMemory(List<Person> personList) {
        personList.clear();
    }
}
