package se.lexicon.data;

import se.lexicon.connection.MySqlConnection;
import se.lexicon.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleImpl implements People{

    @Override
    public Person create(Person person) {

            String query = "INSERT INTO person(first_name,last_name) values (?,?)";
            try {

                PreparedStatement preparedStatement=  MySqlConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,person.getFirstName());
                preparedStatement.setString(2,person.getLastName());

                int temp = preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    person.setId(id);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return person;
    }

    @Override
    public Collection<Person> findAll() {
        String query = "SELECT * FROM person";
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = MySqlConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                personList.add(new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));}

        } catch (SQLException e){
            e.printStackTrace();
        }
        return personList;

    }

    @Override
    public Person findById(int id) {
        String query = "select * from person where person_id = ?";
        Person person = new Person();
        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                person.setId(resultSet.getInt(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Collection<Person> findByName(String name) {
        String query = "select * from person where first_name = ?";
        List<Person> personList = new ArrayList<>();
        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                personList.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;

    }

    @Override
    public Person update(Person person,int id) {
        String query = "update person set first_name = ?, last_name = ? where person_id = ?";
        Person updatedPerson = new Person();
        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                int updateRows = preparedStatement.executeUpdate("update person set first_name = '"+person.getFirstName()+"', last_name = '"+person.getLastName()+"'where person_id="+id);

                updatedPerson.setId(resultSet.getInt(1));
                updatedPerson.setFirstName(resultSet.getString(2));
                updatedPerson.setLastName(resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return updatedPerson;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "delete from person where person_id = ?";
        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,id);

            int temp = preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}
