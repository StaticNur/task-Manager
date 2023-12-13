package org.example.newTaskManager.service;

public interface TaskStore <T extends ScheduledTask> {
    T peek();
    T poll();
    void add(T task);
    boolean remove(T task);
    boolean isEmpty();
}
