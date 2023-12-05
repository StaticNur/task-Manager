package org.example.newTaskManager;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;

public class ProgressData {// Класс для хранения данных
    private final DoubleProperty progress;

    public ProgressData() {
        this.progress = new SimpleDoubleProperty(0.0);
    }

    public ProgressBar getProgressBar() {
        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(progress);
        progressBar.setPrefWidth(200);
        return progressBar;
    }

    public double getProgress() {
        return progress.get();
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public DoubleProperty progressProperty() {
        return progress;
    }
}
