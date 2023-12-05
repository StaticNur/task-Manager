package org.example.taskManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

public class taskService implements Runnable{
    private List<Entity> entityList;
    ExecutorService executor = Executors.newFixedThreadPool(100);
    private Lock lock;
    Scanner scanner = new Scanner(System.in);
    public taskService(List<Entity> entityList, Lock lock) {
        this.entityList = entityList;
        this.lock = lock;
    }

    public void create() {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("пример: 2023.11.10 18:30, Купить хлеб");
                lock.lock();
                String input = scanner.nextLine();
                lock.unlock();
                String[] dateAndTask = input.split(",");
                Date date = parseDate(dateAndTask[0]);
                entityList.add(new Entity(date, dateAndTask[1].trim(), entityList.size()));
            }
        });
    }
    public void delete(){
        Entity entity = getEntity();
        if (entity == null) {
            System.out.println("Error");
            throw new NullPointerException();
        }
        entityList.remove(entity);
    }
    public void edit(){
        Entity entity = getEntity();
        if (entity == null) {
            System.out.println("Error");
            throw new NullPointerException();
        }
        System.out.println("Выбрали: " + entity.toString());
        System.out.println("Что хотите изменить? 1-время 2-задачу 3-выполненность");
        scanner.nextLine();// Очищаем буфер после ввода числа
        lock.lock();
        String input = scanner.nextLine();
        lock.unlock();
        switch (input) {
            case "1" -> {
                System.out.println("Введите время, пример: 2023.11.06 17:30");
                lock.lock();
                input = scanner.nextLine();
                lock.unlock();
                Date date = parseDate(input);
                entity.setDate(date);
            }
            case "2" -> {
                System.out.println("Введите задачу, пример: Купить хлеб");
                lock.lock();
                input = scanner.nextLine();
                lock.unlock();
                entity.setTask(input);
            }
            case "3" -> {
                System.out.println("Введите выполненность, пример: Выполнено/НЕ выполнено");
                lock.lock();
                input = scanner.nextLine();
                lock.unlock();
                if (input.equals("Выполнено")) {
                    entity.setFinished(true);
                } else if (input.equals("НЕ выполнено")) {
                    entity.setFinished(false);
                }
            }
        }
    }
    private Entity getEntity(){
        if(entityList.isEmpty()){
            System.out.println("У вас нет задач!");
            return null;
        }
        System.out.println("Введите id задачи: ");
        entityList.stream().forEach(System.out::println);
        lock.lock();
        int id = scanner.nextInt();
        lock.unlock();
        try {
            Entity entity = entityList.get(id);
            return entity;
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }
    private Date parseDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date date;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    @Override
    public void run() {

    }
}
