package dataAccess;

import entity.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryPersonDao implements PersonDao {
	
	  private List<Person> personList;

	    public InMemoryPersonDao() {
	        personList = new ArrayList<>();
	        personList.add(new Person(1,"Hazal","Güçlü","123456","xx Sitesi 4/8",29)); //örnek bir person
	    }


	@Override
	public void addPerson(Person person) {
        personList.add(person);
	}

	@Override
	public void updatePerson(Person person) {
        for (Person p : personList) {
            if (p.getName().equals(person.getName()) && p.getSurname().equals(person.getSurname())) {
                p.setPhone(person.getPhone());
                p.setAddress(person.getAddress());
                p.setAge(person.getAge());
                break;
            }
        }
    }

	@Override
	public List<Person> getAllPersons() {
		 return personList;
	}

	@Override
	 public Person getPersonByNameAndSurname(String name, String surname) {
        for (Person p : personList) {
            if (p.getName().equals(name) && p.getSurname().equals(surname)) {
                return p;
            }
        }
        return null;
    }
	
	 @Override
	    public Person getPersonById(int id) {
	        for (Person person : personList) {
	            if (person.getId() == id) {
	                return person;
	            }
	        }
	        return null;
	    }
	 
	 @Override
	    public void deletePerson(int id) {
	        Iterator<Person> iterator = personList.iterator();
	        while (iterator.hasNext()) {
	            Person person = iterator.next();
	            if (person.getId() == id) {
	                iterator.remove();
	                break;
	            }
	        }
	    }
  
}
