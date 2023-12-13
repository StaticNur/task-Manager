package org.example.newTaskManager.controller;

import org.example.newTaskManager.objects.Entity;
import org.example.newTaskManager.service.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskDelegation {
    private static final ExecutorConfig executorConfig = new ExecutorConfig(5);

    public static void extract() {
        var entityList = parse();
        var queueTaskStore = new PriorityBlockingQueueTaskStore(
                new Comparator<ScheduledTask>() {
                    @Override
                    public int compare(ScheduledTask o1, ScheduledTask o2) {
                        if (o1.getNextExecutionTime() < o2.getNextExecutionTime()) {
                            return -1;
                        } else if (o1.getNextExecutionTime() > o2.getNextExecutionTime()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                },
                15);
        //PriorityBlockingQueueTaskStore.setQueueSize(15);

        queueTaskStore.add(new OneTimeTask(entityList.get(0), 200));
        queueTaskStore.add(new OneTimeTask(entityList.get(1), 100));
        queueTaskStore.add(new OneTimeTask(entityList.get(2), 155));
        queueTaskStore.add(new OneTimeTask(entityList.get(3), 99));
        queueTaskStore.add(new OneTimeTask(entityList.get(4), 500));
        queueTaskStore.add(new OneTimeTask(entityList.get(5), 375));
        queueTaskStore.add(new OneTimeTask(entityList.get(6), 295));
        queueTaskStore.add(new OneTimeTask(entityList.get(7), 465));
        queueTaskStore.add(new OneTimeTask(entityList.get(8), 395));
        queueTaskStore.add(new OneTimeTask(entityList.get(9), 455));
        queueTaskStore.add(new OneTimeTask(entityList.get(10), 15));
        queueTaskStore.add(new OneTimeTask(entityList.get(11), 95));
        queueTaskStore.add(new OneTimeTask(entityList.get(12), 105));
        queueTaskStore.add(new OneTimeTask(entityList.get(13), 152));
        queueTaskStore.add(new OneTimeTask(entityList.get(14), 215));
        queueTaskStore.viewQueue();
        new TaskScheduler(executorConfig, queueTaskStore);
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
