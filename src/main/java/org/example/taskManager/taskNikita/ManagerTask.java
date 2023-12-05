package org.example.taskManager.taskNikita;

import java.util.List;

public class ManagerTask implements Runnable{
    private final Entity entity;

    public ManagerTask(Entity entity) {
        this.entity = entity;
    }
    @Override
    public void run() {
        System.out.println("Дурак?");
    }
}
