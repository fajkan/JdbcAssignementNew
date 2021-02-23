package se.lexicon.data;

import se.lexicon.connection.MySqlConnection;
import se.lexicon.model.Person;
import se.lexicon.model.ToDoIt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ToDoImpl implements ToDoItems{

    @Override
    public ToDoIt create(ToDoIt todo) {
        String query = "INSERT INTO todo_item(title,description,deadline,done) values (?,?,?,?)";
        try {

            PreparedStatement preparedStatement=  MySqlConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,todo.getTitle());
            preparedStatement.setString(2,todo.getDescription());
            preparedStatement.setString(3,todo.getDeadLine().toString());
            preparedStatement.setInt(4,todo.getDone());


            int temp = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                todo.setTodoId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;

    }

    @Override
    public Collection<ToDoIt> findAll() {
        String query = "SELECT * FROM todo_item";
        List<ToDoIt> toDoList = new ArrayList<>();
        try {
            Statement statement = MySqlConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                toDoList.add(new ToDoIt(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        LocalDate.parse(resultSet.getString(4)),
                        resultSet.getInt(5),
                        resultSet.getInt(6)));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return toDoList;
    }

    @Override
    public ToDoIt findById(int number) {
        String query = "select * from todo_item where todo_id = ?";
        ToDoIt toDoIt = new ToDoIt();
        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                toDoIt.setTodoId(resultSet.getInt(1));
                toDoIt.setTitle(resultSet.getString(2));
                toDoIt.setDescription(resultSet.getString(3));
                toDoIt.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                toDoIt.setDone(resultSet.getInt(5));
                toDoIt.setAssigneeId(resultSet.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoIt;
    }

    @Override
    public Collection<ToDoIt> findByDoneStatus(boolean status) {
        String query = "select * from todo_item where done = ?";
        List<ToDoIt> toDoList = new ArrayList<>();
        if(status==true){
            int tinyInt = 1;
            try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,tinyInt);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ToDoIt toDoIt = new ToDoIt();
                toDoIt.setTodoId(resultSet.getInt(1));
                toDoIt.setTitle(resultSet.getString(2));
                toDoIt.setDescription(resultSet.getString(3));
                toDoIt.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                toDoIt.setDone(resultSet.getInt(5));
                toDoIt.setAssigneeId(resultSet.getInt(6));
                toDoList.add(toDoIt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
        }
        else if(status==false){
            int tinyInt = 0;
            try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1,tinyInt);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    ToDoIt toDoIt = new ToDoIt();
                    toDoIt.setTodoId(resultSet.getInt(1));
                    toDoIt.setTitle(resultSet.getString(2));
                    toDoIt.setDescription(resultSet.getString(3));
                    toDoIt.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                    toDoIt.setDone(resultSet.getInt(5));
                    toDoIt.setAssigneeId(resultSet.getInt(6));
                    toDoList.add(toDoIt);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return toDoList;
        }

        return null;
    }

    @Override
    public Collection<ToDoIt> findByAssignee(int assignee) {
        String query = "select * from todo_item where assignee_id = ?";
        List<ToDoIt> toDoList = new ArrayList<>();


            try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1,assignee);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    ToDoIt toDoIt = new ToDoIt();
                    toDoIt.setTodoId(resultSet.getInt(1));
                    toDoIt.setTitle(resultSet.getString(2));
                    toDoIt.setDescription(resultSet.getString(3));
                    toDoIt.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                    toDoIt.setDone(resultSet.getInt(5));
                    toDoIt.setAssigneeId(resultSet.getInt(6));
                    toDoList.add(toDoIt);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return toDoList;
    }

    @Override
    public Collection<ToDoIt> findByAssignee(Person person) {
        String query = "select * from todo_item where assignee_id = ?";
        List<ToDoIt> toDoList = new ArrayList<>();


        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,person.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ToDoIt toDoIt = new ToDoIt();
                toDoIt.setTodoId(resultSet.getInt(1));
                toDoIt.setTitle(resultSet.getString(2));
                toDoIt.setDescription(resultSet.getString(3));
                toDoIt.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                toDoIt.setDone(resultSet.getInt(5));
                toDoIt.setAssigneeId(resultSet.getInt(6));
                toDoList.add(toDoIt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
    }


    @Override
    public Collection<ToDoIt> findByUnassignedTodoItems() {
        String query = "select * from todo_item where assignee_id = ?";
        List<ToDoIt> toDoList = new ArrayList<>();


        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1,null);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ToDoIt toDoIt = new ToDoIt();
                toDoIt.setTodoId(resultSet.getInt(1));
                toDoIt.setTitle(resultSet.getString(2));
                toDoIt.setDescription(resultSet.getString(3));
                toDoIt.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                toDoIt.setDone(resultSet.getInt(5));
                toDoIt.setAssigneeId(resultSet.getInt(6));
                toDoList.add(toDoIt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
    }


    @Override
    public ToDoIt update(ToDoIt todo,int todoId) {
        String query = "update todo_item set title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? where todo_id = ?";
        ToDoIt updatedToDo = new ToDoIt();
        try(PreparedStatement preparedStatement= MySqlConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,todoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                int updateRows = preparedStatement.executeUpdate("update todo_item set title = '"+todo.getTitle()+"', description = '"+todo.getDescription()+"', deadline = '"+todo.getDeadLine().toString()+"', assigne_id = "+todo.getAssigneeId() +" where todo_id = "+todoId);

                updatedToDo.setTodoId(resultSet.getInt(1));
                updatedToDo.setTitle(resultSet.getString(2));
                updatedToDo.setDescription(resultSet.getString(3));
                updatedToDo.setDeadLine(LocalDate.parse(resultSet.getString(4)));
                updatedToDo.setDone(resultSet.getInt(5));
                updatedToDo.setAssigneeId(resultSet.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return updatedToDo;
    }


    @Override
    public boolean deleteById(int id) {
        String query = "delete from todo_item where todo_id = ?";
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
