package exec;

import entity.Person;
import util.MenuUtils;

import java.util.Comparator;
import java.util.List;

import static util.MenuUtils.*;

public enum ExecEnum {
    ADD(data -> {
        System.out.println("Enter first name: ");
        String firstName = scanner().nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner().nextLine();
        if(checkNameAndSurname(firstName, lastName)) {
            data.add(new Person(firstName, lastName));
            System.out.println("[successfully]");
        } else {
            System.out.println("[Wrong data. Person not added]");
        }}),
    SHOW(data -> {
        if(data.size() == 0) System.out.println("Sorry, person list is empty");
        data.forEach(System.out::println); }),
    SHOW_SORTED_UNIQUE(data -> {
        if (data.size() == 0) System.out.println("Sorry, person list is empty");
        List<Person> finishedPersonList = excludeRepeatedByLastName(data);
        finishedPersonList.sort(Comparator.comparing(Person::getLastName));
        finishedPersonList.forEach(System.out::println); }),
    SAVE_TO_FILE(MenuUtils::saveToFile),
    READ_FROM_FILE(MenuUtils::readFromFile),
    CLEAR_DATA(MenuUtils::clearDataInMemory),
    EXIT(data -> { System.out.println("Good bay, my friend!");
        System.exit(0); });


    private final Exec exec;

    public Exec getExec() {
        return this.exec;
    }

    ExecEnum(Exec exec) {
        this.exec = exec;
    }
}