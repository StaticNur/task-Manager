package org.example.newTaskManager.service;

import javafx.application.Platform;
import org.example.newTaskManager.controller.TaskManagerController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskRunner implements Runnable {
    private final TaskStore<ScheduledTask> taskStore;
    private boolean running = true;
    private final Lock lock =  new ReentrantLock();
    private final TaskManagerController ui = TaskManagerController.getINSTANCE();
    public TaskRunner(TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void run() {
        long executionTime = 0;
        ScheduledTask scheduledTask = taskStore.poll();
        var progressData = ui.setProgress(scheduledTask.execute());
        Platform.runLater(() -> ui.deleteQueueText(scheduledTask.context.execute()));
        while (running) {
            double progress;
            System.out.println(Thread.currentThread().getName());
            //lock.lock();
            try {
                if (scheduledTask != null && executionTime >= scheduledTask.getNextExecutionTime()) {
                    scheduledTask.execute();
                    running = false;
                    System.out.println("Прогресс-бар полностью заполнен!");
                    //Platform.runLater(() -> ui.deleteProgress(scheduledTask.execute().getTask()));
                }
                executionTime++;
                progress = (double) executionTime / scheduledTask.getNextExecutionTime();
            } finally {
                //lock.unlock();
            }
            Platform.runLater(() -> progressData.setProgressProperty(progress));
            sleep();
            ui.getStop().setOnAction(event -> {
                stop();
            });

            ui.getContinueButton().setOnAction(event -> {
                running();
            });
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
