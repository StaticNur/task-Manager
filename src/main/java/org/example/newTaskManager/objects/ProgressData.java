package org.example.newTaskManager.objects;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ProgressBar;

import java.util.Objects;

public class ProgressData {// Класс для хранения данных
    private DoubleProperty progressProperty;
    private ProgressBar progressBar;
    private Entity task;

    public ProgressData(DoubleProperty progressProperty, ProgressBar progressBar, Entity task) {
        this.progressProperty = progressProperty;
        this.progressBar = progressBar;
        this.task = task;
        progressBar.progressProperty().bind(progressProperty);
        progressBar.setPrefWidth(200);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public double getProgressProperty() {
        return progressProperty.get();
    }

    public DoubleProperty progressPropertyProperty() {
        return progressProperty;
    }

    public void setProgressProperty(double progressProperty) {
        this.progressProperty.set(progressProperty);
    }

    public Entity getTask() {
        return task;
    }

    public void setTask(Entity task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressData that = (ProgressData) o;
        return Objects.equals(task.getTask(), that.task.getTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(task.getTask());
    }
}
