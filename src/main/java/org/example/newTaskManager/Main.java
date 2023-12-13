package org.example.newTaskManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.newTaskManager.controller.TaskManagerController;

public class Main extends Application {
    public static void main(String[] args) throws InterruptedException {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // сначала формируем загрузчик и привязываем его к файлу
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/taskManagerUI.fxml"));
        // а затем уже непосредственно вызываем загрузку дерева разметки из файла
        fxmlLoader.setController(TaskManagerController.getINSTANCE());
        Parent root = fxmlLoader.load();
        // тут мы вытаскиваем контроллер которые был создан при вызове метода load
        // и сохраняем ссылку на него в переменную
        TaskManagerController controller = fxmlLoader.getController();
        // устанавливает scene внутрь stage. cодержимое scene является макет нашей программе котороый формировали в файле simulator.fxml
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(root));
        // делает видимым объект stage
        primaryStage.show();
    }
}