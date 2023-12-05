package org.example.newTaskManager;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main extends Application {
    private final ExecutorConfig executorConfig = new ExecutorConfig(5);

    public static void main(String[] args) throws InterruptedException {
        Application.launch(args);
        //extract();
        //new TaskScheduler(executorConfig, queueTaskStore);
    }

    private void extract(TableView<ProgressData> tableView, ListView<String> textListView,Button button) {
        var entityList = parse();
        var queueTaskStore = new PriorityBlockingQueueTaskStore(
                new Comparator<ScheduledTask>() {
                    @Override
                    public int compare(ScheduledTask o1, ScheduledTask o2) {
                        if (o1.getNextExecutionTime() > o2.getNextExecutionTime()) {
                            return -1;
                        } else if (o1.getNextExecutionTime() > o2.getNextExecutionTime()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                },
                15);
        PriorityBlockingQueueTaskStore.setQueueSize(15);

        queueTaskStore.add(new OneTimeTask(entityList.get(1), 200));
        queueTaskStore.add(new OneTimeTask(entityList.get(1), 100));
        queueTaskStore.add(new OneTimeTask(entityList.get(1), 155));
        queueTaskStore.add(new OneTimeTask(entityList.get(1), 99));
        queueTaskStore.add(new OneTimeTask(entityList.get(0), 500));
        queueTaskStore.add(new OneTimeTask(entityList.get(0), 375));
        queueTaskStore.add(new OneTimeTask(entityList.get(0), 295));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 465));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 395));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 455));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 15));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 95));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 105));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 152));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 215));
        new TaskScheduler(executorConfig, queueTaskStore, tableView, textListView, button);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView<String> textListView = new ListView<>();
        // Добавляем текстовые элементы в ListView
        textListView.getItems().addAll("\t\t\t  Очередь");


        TableView<ProgressData> tableView = new TableView<>();
        //textListView.property().bind(tableView);

        // Создаем столбец с ProgressBar
        TableColumn<ProgressData, ProgressBar> progressBarColumn = new TableColumn<>("Progress");
        progressBarColumn.setCellValueFactory(new PropertyValueFactory<>("progressBar"));
        // Добавляем столбец в TableView
        tableView.getColumns().add(progressBarColumn);
        // Добавляем данные для отображения
        for (int i = 0; i < 15; i++) {
            UiTaskManager.setProgress(tableView);
        }
        // Создаем кнопки
        Button button1 = new Button("Exit");
        Button button2 = new Button("Пауза");
        Button button3 = new Button("Продолжить");
        // Создаем обработчик события для кнопки
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button clicked! Exit");
                System.exit(404);
            }
        });
        /*button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button clicked! Пауза");

            }
        });*/
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button clicked! Продолжить");
            }
        });

        // Создаем вертикальный контейнер для кнопок
        HBox buttonsVBox = new HBox(10); // 10 - это расстояние между кнопками
        buttonsVBox.getChildren().addAll(button1, button2, button3);
        buttonsVBox.setAlignment(Pos.CENTER); // Размещаем кнопки в центре

        // Создаем главный контейнер
        BorderPane root = new BorderPane();
        root.setRight(textListView); // Размещаем ListView с текстовыми элементами слева
        root.setCenter(tableView); // Размещаем TableView в центре
        root.setBottom(buttonsVBox); // Размещаем кнопки внизу

        // Устанавливаем отступы
        BorderPane.setMargin(tableView, new Insets(0, 0, 10, 0)); // Отступы для TableView
        BorderPane.setMargin(textListView, new Insets(0, 0, 10, 0)); // Отступы для TableView
        BorderPane.setMargin(buttonsVBox, new Insets(10, 0, 20, 10)); // Отступы для VBox с кнопками


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();

        extract(tableView, textListView, button2);
    }

    private static List<Entity> parse() {
        List<Entity> entityList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            String separator = File.separator;
            File inputFile = new File("src" + separator + "main" + separator + "resources" + separator + "tasks.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList taskList = doc.getElementsByTagName("task");

            for (int temp = 0; temp < taskList.getLength(); temp++) {
                Element taskNode = (Element) taskList.item(temp);
                String dateString = taskNode.getElementsByTagName("date").item(0).getTextContent();
                String title = taskNode.getElementsByTagName("title").item(0).getTextContent();
                String completed = taskNode.getElementsByTagName("completed").item(0).getTextContent();

                System.out.println("Date: " + dateString + ", Title: " + title + ", Completed: " + completed);
                LocalDate date = LocalDate.parse(dateString, formatter);
                entityList.add(new Entity(date, title, completed.equals("true")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityList;
    }
}