package org.example.newTaskManager;

import javafx.scene.control.ListView;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTaskStore implements TaskStore<ScheduledTask> {
    private final PriorityBlockingQueue<ScheduledTask> taskQueue;
    private static Integer queueSize;



    private static StatusProgressBar statusProgressBar = StatusProgressBar.WAIT;
    private static final ListView<String> textListView = new ListView<>();
    //private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Set<ScheduledTask> tasks;
    public PriorityBlockingQueueTaskStore(Comparator<ScheduledTask> comparator, Integer queueSize) {
        this.taskQueue = new PriorityBlockingQueue<>(queueSize, comparator);
        this.tasks = new HashSet<>();
    }
    @Override
    public void add(ScheduledTask task) {
        //executorService.submit(() -> {
            textListView.getItems().add(task.context.toString());

            taskQueue.offer(task);
        //});
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
    public static void setQueueSize(Integer size){
        queueSize = size;
    }
    public static Integer getQueueSize(){
        return queueSize;
    }

    public static StatusProgressBar getStatusProgressBar() {
        return statusProgressBar;
    }

    public static void setStatusProgressBar(StatusProgressBar statusProgressBar) {
        PriorityBlockingQueueTaskStore.statusProgressBar = statusProgressBar;
    }
}
