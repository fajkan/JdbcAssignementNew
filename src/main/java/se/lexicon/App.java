package se.lexicon;

import se.lexicon.data.People;
import se.lexicon.data.PeopleImpl;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PeopleImpl pimpl = new PeopleImpl();
       // Person person = new Person("Hello", "World");
       // Person personResult = pimpl.create(person);

       // System.out.println(personResult);

       Collection<Person> personList = pimpl.findAll();

       // System.out.println(personList);
       // pimpl.deleteById(4);
       //Collection<Person> personList2 = pimpl.findByName("Hello");


        //System.out.println(person);

        System.out.println(personList);

        //pimpl.deleteById(5);




    }
}
