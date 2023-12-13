package org.example.newTaskManager.service;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import org.example.newTaskManager.controller.TaskManagerController;
import org.example.newTaskManager.objects.StatusProgressBar;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTaskStore implements TaskStore<ScheduledTask> {
    private final PriorityBlockingQueue<ScheduledTask> taskQueue;
    private static Integer queueSize;
    private final TaskManagerController ui = TaskManagerController.getINSTANCE();
    private static StatusProgressBar statusProgressBar = StatusProgressBar.WAIT;
    private static final ListView<String> textListView = new ListView<>();
    private final Set<ScheduledTask> tasks;

    public PriorityBlockingQueueTaskStore(Comparator<ScheduledTask> comparator, Integer queueSize) {
        this.taskQueue = new PriorityBlockingQueue<>(queueSize, comparator);
        this.tasks = new HashSet<>();
    }

    @Override
    public void add(ScheduledTask task) {
        Platform.runLater(() -> ui.setQueueText(task.execute()));
        taskQueue.offer(task);
        if (queueSize == null) {
            queueSize = 1;
        } else queueSize++;
    }

    @Override
    public ScheduledTask poll() {
        return taskQueue.poll();
    }

    @Override
    public ScheduledTask peek() {
        return taskQueue.peek();
    }

    @Override
    public boolean remove(ScheduledTask task) {
        if (tasks.contains(task)) {
            taskQueue.remove(task);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public synchronized boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public static void setQueueSize(Integer size) {
        queueSize = size;
    }

    public static Integer getQueueSize() {
        return queueSize;
    }
    public void viewQueue(){
        System.out.println("Очередь");
        for (ScheduledTask e: taskQueue){
            System.out.println(e.context.execute());
        }
    }
    public static StatusProgressBar getStatusProgressBar() {
        return statusProgressBar;
    }

    public static void setStatusProgressBar(StatusProgressBar statusProgressBar) {
        PriorityBlockingQueueTaskStore.statusProgressBar = statusProgressBar;
    }
}
