package se.lexicon.model;

import java.time.LocalDate;

public class ToDoIt {
    private int todoId;
    private String title;
    private String description;
    private LocalDate deadLine;
    private int done;
    private int assigneeId;


    public ToDoIt() {
    }

    public ToDoIt(int todoId, String title, String description, LocalDate deadLine, int done, int assigneeId) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    public ToDoIt(String title, String description, LocalDate deadLine, int done) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public String toString() {
        return "ToDoIt{" +
                "todoId=" + todoId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", assigneeId=" + assigneeId +
                '}';
    }
}
