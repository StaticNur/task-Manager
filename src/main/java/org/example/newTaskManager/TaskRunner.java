package org.example.newTaskManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskRunner implements Runnable {
    private final TaskStore<ScheduledTask> taskStore;
    private boolean running = true;
    private final Lock lock =  new ReentrantLock();
    private final ListView<String> textListView;
    private final ProgressData progressData;
    private final Button button;
    public TaskRunner(TaskStore<ScheduledTask> taskStore, ProgressData progressData, ListView<String> textListView, Button button) {
        this.taskStore = taskStore;
        this.progressData = progressData;
        this.textListView = textListView;
        this.button = button;
    }

    @Override
    public void run() {
        long executionTime = 0;
        ScheduledTask scheduledTask = taskStore.poll();

        //textListView.getItems().remove(scheduledTask);

        System.out.println("Количество потоков");
        while (running) {
            double progress;
            System.out.println(Thread.currentThread().getName());
            //lock.lock();
            try {
                if (scheduledTask != null && executionTime >= scheduledTask.getNextExecutionTime()) {
                    scheduledTask.execute();
                    running = false;
                }
                executionTime++;
                progress = (double) executionTime / scheduledTask.getNextExecutionTime();
            } finally {
                //lock.unlock();
            }
            Platform.runLater(() -> progressData.setProgress(progress));
            if (scheduledTask != null && scheduledTask.isRecurring()) {
                taskStore.add(scheduledTask.nextScheduledTask());
            }

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Button clicked! Пауза");
                    if (running) {
                        stop();
                    } else {
                        running();
                    }
                }
            });
            sleep();
        }
    }
    public void stop() {
        this.running = false;
    }
    public void running() {
        this.running = true;
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            //wait(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
