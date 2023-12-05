package org.example.taskManager;

import java.util.List;

public class PrintTask {
    private List<Entity> entityList;
    public PrintTask(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public void printTasks() {
        entityList.stream().forEach(System.out::println);
    }
}
