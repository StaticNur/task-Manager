package org.example.taskManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entity {
    private final int id;
    private Date date;
    private String task;
    private boolean finished;

    public Entity(Date date, String task, int id) {
        this.id = id;
        this.date = date;
        this.task = task;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return "\u001B[47m "+id+" \u001B[44m "+formatForDateNow.format(date) +
                " \u001B[42m "+ task + (finished ? " Выполнено \u001B[0m" : " \u001B[41m НЕ выполнено \u001B[0m");
    }
}
