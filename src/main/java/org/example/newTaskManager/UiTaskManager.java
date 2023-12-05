package org.example.newTaskManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UiTaskManager {
    public static void setProgress(TableView<ProgressData> tableView){
        // Добавляем данные для отображения
        ProgressData data = new ProgressData();
        tableView.getItems().add(data);
    }
    public static void setTextListView(ListView<String> textListView, String str){
        // Добавляем текстовые элементы в ListView
        textListView.getItems().addAll(str);
    }
}
