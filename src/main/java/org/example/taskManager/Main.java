package org.example.taskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static List<Entity> entityList = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //Thread thread = new Thread(new Notification(entityList));
        //thread.setDaemon(true);
        //thread.start();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Notification(entityList, lock));

        PrintTask printThread = new PrintTask(entityList);
        taskService taskManagement = new taskService(entityList, lock);
        int input;
        boolean flag = true;
        while (flag){
            printMenu();
            //lock.lock();
            input = scanner.nextInt();
            //lock.unlock();
            switch (input) {
                case 1 -> taskManagement.create();
                case 2 -> printThread.printTasks();
                case 3 -> taskManagement.delete();
                case 4 -> taskManagement.edit();
                case 5 -> flag = false;
            }
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Вы закрыли 'планировщик задач'");
    }
    private static void printMenu(){
        System.out.println("1-Добавить");
        System.out.println("2-Посмотреть");
        System.out.println("3-Удалить");
        System.out.println("4-Редактировать");
        System.out.println("5-Закрыть программу");
    }
}