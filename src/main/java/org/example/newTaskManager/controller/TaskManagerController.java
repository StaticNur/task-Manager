package org.example.newTaskManager.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.newTaskManager.objects.Entity;
import org.example.newTaskManager.objects.ProgressData;
import org.example.newTaskManager.service.TaskRunner;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskManagerController implements Initializable {
    private static final TaskManagerController INSTANCE = new TaskManagerController();
    @FXML private Button exit;
    @FXML private Button stop;
    @FXML private Button continueButton;
    @FXML private TableView<ProgressData> progressBarTable;
    @FXML private TableColumn<ProgressData, Double> progressBarColumn;
    @FXML private TableColumn<Entity, String> nothing;
    @FXML private TableView<Entity> taskTable;
    @FXML private TableColumn<Entity, String> nameTaskColumn;

    private TaskManagerController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBarColumn.setCellValueFactory(new PropertyValueFactory<>("progressBar"));
        nothing.setCellValueFactory(new PropertyValueFactory<>("task"));
        nameTaskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
        exit.setOnAction(event -> {
            System.out.println("Button clicked! Exit");
            Platform.exit(); // Завершение приложения
            System.exit(404);
        });
        /*stop.setOnAction(event -> {
            System.out.println("Button clicked! stop");

        });
        continueButton.setOnAction(event -> {
            System.out.println("Button clicked! continue");
            notifyAll();

        });*/
        if (taskTable != null) {
            TaskDelegation.extract();
        }

    }

    public synchronized ProgressData setProgress(Entity entity) {
        ProgressData data = new ProgressData(new SimpleDoubleProperty(0.0),
                new ProgressBar(), entity);
        progressBarTable.getItems().add(data);
        return data;
    }

    public synchronized void deleteProgress(String task) {
        ProgressData data = new ProgressData(new SimpleDoubleProperty(0.0),
                new ProgressBar(), new Entity(LocalDate.now(), task, false));
        progressBarTable.getItems().remove(data);
    }

    public synchronized void setQueueText(Entity data) {
        taskTable.getItems().add(data);
    }
    public synchronized Entity getQueueText(int i) {
        return taskTable.getItems().get(i);
    }
    public synchronized void deleteQueueText(Entity task) {
        taskTable.getItems().remove(task);
    }

    public static TaskManagerController getINSTANCE() {
        return INSTANCE;
    }

    public Button getStop() {
        return stop;
    }

    public Button getContinueButton() {
        return continueButton;
    }
}
