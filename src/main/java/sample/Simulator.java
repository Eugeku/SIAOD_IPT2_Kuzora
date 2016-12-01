package sample;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.io.*;

/**
 * Created by Eugene13 on 24.11.2016.
 * Class Simulator:
 * 1) Класс реализует интерфейс Runnable, для реализации многопоточного процесса;
 * 2) Класс хранит общий ресурс(integerList для всех потоков), который хранит очередь, с извлекаемыми элементами;
 * 3) В классе реализованы методы для серилизации (saveListToFile) и десерилизации (loadListFromFile);
 */
public class Simulator implements Runnable {
    private String threadName;
    static List<String> integerList = new List<>();
    private String message, message2;
    private TextArea textArea2, textArea1;

    Simulator(String threadName, TextArea textArea2, TextArea textArea1) {
        this.threadName = threadName;
        this.textArea1 = textArea1;
        this.textArea2 = textArea2;
        message = "Поток " + threadName + "";
        message2 = "";
    }

    void statr() {
        Thread t;
        t = new Thread(this, threadName);
        switch (threadName) {
            case "Core 0":
                t.setPriority(10);
                break;
            case "Core 1":
                t.setPriority(9);
                break;
            case "Core 2":
                t.setPriority(9);
                break;
            case "Core 3":
                t.setPriority(7);
                break;
            case "Core 4":
                t.setPriority(6);
                break;
            case "Core 5":
                t.setPriority(5);
                break;
            case "Core 6":
                t.setPriority(4);
                break;
            case "Core 7":
                t.setPriority(3);
                break;
            case "Core 8":
                t.setPriority(2);
                break;
            case "Core 9":
                t.setPriority(1);
                break;
            case "Core 10":
                t.setPriority(1);
                break;
            case "Core 11":
                t.setPriority(1);
                break;
            case "Core 12":
                t.setPriority(1);
                break;
            case "Core 13":
                t.setPriority(1);
                break;
            case "Core 14":
                t.setPriority(1);
                break;
            case "Core 15":
                t.setPriority(1);
                break;
        }
        t.start();
    }

    static void loadListFromFile(String nameOfFile) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(nameOfFile))) {
            Object i = stream.readObject();
            integerList = (List<java.lang.String>) i;
        } catch (Exception e) {
        }
    }

    static void saveListToFile(String nameOfFile) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(nameOfFile))) {
            stream.writeObject(integerList);
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        ListElement<String> listElement;
        long time;
        while (integerList.hasListElements()) {
            time = (long) (100 + Math.random() * 1000);
            listElement = integerList.takeElement();
            message2 = message + " начал " + listElement.getProcessName() + " (При-т: " + listElement.getPriority().getValueOfPriority() + "): " + time + " (мс)\n";
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    textArea2.appendText(message2);
                    textArea1.clear();
                    textArea1.setText(integerList.viewList());
                }
            });
            try {
                Thread.sleep(time);
            } catch (Exception e) {
            }
            message2 = getMessage(listElement, time) + "\n";
        }
    }

    String getMessage(ListElement<String> listElement, long time) {
        return message + " завершил " + listElement.getProcessName() + ", длительность " + time + " (мс)";
    }
}
