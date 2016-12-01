package sample;

import java.io.Serializable;

/**
 * Created by Eugene13 on 24.11.2016.
 * Class ListElement<E>:
 * 1) Класс реализует интерфес Serializable для последующей сериализации;
 */
class ListElement<E> implements Serializable {
    private String processName;
    private E someValue;
    private Priority priority;
    ListElement<E> next;

    //    default constructor
    private ListElement() {
        this.next = null;
        this.someValue = null;
        this.processName = "default process";
        this.priority = Priority.MiddlePriority;
    }

    //  user constructor
    ListElement(String processName, Priority priority, E someValue) {
        this();
        this.someValue = someValue;
        this.processName = processName;
        this.priority = priority;
    }

    //    getters
    String getProcessName() {
        return this.processName;
    }

    Priority getPriority() {
        return this.priority;
    }

    E getSomeValue() {
        return this.someValue;
    }
}
