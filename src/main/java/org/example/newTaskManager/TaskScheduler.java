package org.example.newTaskManager;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskScheduler {
    private final List<Runnable> threads;
    private final TaskStore<ScheduledTask> taskStore;
    public TaskScheduler(ExecutorConfig executorConfig,
                         TaskStore<ScheduledTask> taskStore,
                         TableView<ProgressData> tableView,
                         ListView<String> textListView, Button button) {
        this.threads = new ArrayList<>();
        this.taskStore = taskStore;
        ExecutorService executorService = Executors.newFixedThreadPool(executorConfig.getNumThreads());
        for (int i = 0; i < PriorityBlockingQueueTaskStore.getQueueSize(); i++) {
            //Thread thread = new Thread(new TaskRunner(taskStore, tableView.getItems().get(i), textListView));
            //thread.setName("Thread-"+i);
            //thread.start();
            //threads.add(thread);
            executorService.submit(new TaskRunner(taskStore, tableView.getItems().get(i), textListView, button));
        }
    }
    public void stop() {
        threads.forEach(t -> ((TaskRunner) t).stop());
    }
}
