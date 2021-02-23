package se.lexicon.data;

import se.lexicon.model.Person;
import se.lexicon.model.ToDoIt;

import java.util.Collection;

public interface ToDoItems {
    ToDoIt create(ToDoIt todo);
    Collection<ToDoIt> findAll();
    ToDoIt findById(int number);
    Collection<ToDoIt> findByDoneStatus(boolean status);
    Collection<ToDoIt> findByAssignee(int assignee);
    Collection<ToDoIt> findByAssignee(Person person);
    Collection<ToDoIt> findByUnassignedTodoItems();
    ToDoIt update(ToDoIt todo, int todoId);
    boolean deleteById(int id);
}
