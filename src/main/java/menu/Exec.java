package menu;


import entity.Person;

import java.util.List;

public interface Exec {
    void exec(List<Person> data) throws Exception;
}
