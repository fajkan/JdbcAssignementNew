package se.lexicon.data;

import se.lexicon.model.Person;

import java.util.Collection;

public interface People {

    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    Person update(Person person,int id);
    boolean deleteById(int id);
}
