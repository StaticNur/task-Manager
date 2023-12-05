package org.example.taskManager.taskNikita;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Entity> entityList = parse();
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (Entity entity : entityList) {
            service.submit(new ManagerTask(entity));
        }
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);
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