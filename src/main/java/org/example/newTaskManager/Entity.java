package org.example.newTaskManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entity implements ExecutionContext{
    private LocalDate date;
    private String task;
    private boolean finished;

    public Entity(LocalDate date, String task, boolean finished) {
        this.date = date;
        this.task = task;
        this.finished = finished;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        //SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        /*return "\u001B[44m "+formatter.format(date) +
                " \u001B[42m "+ task + (finished ? " Выполнено \u001B[0m" : " \u001B[41m НЕ выполнено \u001B[0m");*/
        return formatter.format(date)+"     ||     " + task;
    }

    @Override
    public void execute() {
        System.out.println(this.toString());
    }
}
