//package dataAccess;
//
//import entity.Person;
//import java.util.List;
//
//public interface PersonDao {
//    void addPerson(Person person);
//    void updatePerson(Person person);
//    void deletePerson(String name, String surname);
//    List<Person> getAllPersons();
//    Person getPersonByNameAndSurname(String name, String surname);
//}

package dataAccess;

import entity.Person;
import java.util.List;

public interface PersonDao {
    void addPerson(Person person);
    void updatePerson(Person person);
    //void deletePerson(String name, String surname);
    List<Person> getAllPersons();
    Person getPersonByNameAndSurname(String name, String surname);
    Person getPersonById(int id);
    void deletePerson(int id);
}
