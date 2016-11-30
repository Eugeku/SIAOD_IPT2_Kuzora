package sample;

import java.io.*;

/**
 * Created by Eugene13 on 24.11.2016.
 */
public class Simulator implements Runnable {
    Thread t;
    private String threadName;
    static List<String> integerList = new List<>();
    private String message;
    static volatile String message2 = "";

    Simulator(String threadName) {
        this.threadName = threadName;
        message = "Поток " + threadName + "";
    }

    void statr() {
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
            listElement = integerList.takeElement();
            message2 += message + " начал " + listElement.getProcessName()+" (Приоритет: "+listElement.getPriority().getValueOfPriority()+")" + "\n";
            time = (long) (100 + Math.random() * 1000);
            try {
                Thread.sleep(time);
            } catch (Exception e) {
            }
            message2 += getMessage(listElement, time) + "\n";
        }
    }

    String getMessage(ListElement<String> listElement, long time) {
        return message + " завершил " + listElement.getProcessName() + ", длительность " + time + " (мс)";
    }

    static void clearMessage2() {
        message2 = "";
    }
}
