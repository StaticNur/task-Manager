package org.example.newTaskManager.service;

import org.example.newTaskManager.controller.TaskManagerController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskScheduler {
    private final List<Runnable> threads;
    private final TaskStore<ScheduledTask> taskStore;

    public TaskScheduler(ExecutorConfig executorConfig,
                         TaskStore<ScheduledTask> taskStore) {
        this.threads = new ArrayList<>();
        this.taskStore = taskStore;
        ExecutorService executorService = Executors.newFixedThreadPool(executorConfig.getNumThreads());
        for (int i = 0; i < PriorityBlockingQueueTaskStore.getQueueSize(); i++) {
            executorService.submit(new TaskRunner(taskStore));
        }
    }

    /*public void stop() {
        threads.forEach(t -> ((TaskRunner) t).stop());
    }*/
}
