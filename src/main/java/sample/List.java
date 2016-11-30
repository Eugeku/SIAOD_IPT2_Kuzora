package sample;

import java.io.Serializable;

/**
 * Created by Eugene13 on 24.11.2016.
 */
class List<E> implements Serializable {
    private ListElement<E> head;
    private ListElement<E> tail;

    synchronized void addInTail(ListElement<E> element) {
        if (tail == null) {
            head = element;
            tail = element;
        } else {
            tail.next = element;
            tail = element;
        }
    }

    synchronized ListElement<E> takeElement() {
        ListElement<E> ret = null;
        Priority highestPriority = Priority.LowPriority;
//If list is empty
        if (head == null) return null;
//If list contains only 1 element
        if (head == tail) {
            ret = head;
            head = null;
            tail = null;
            return ret;
        }
//Find Max priority in list and get first value in queue with this priority
        ListElement<E> listElement = head;
        do {
            if (listElement.getPriority().getValueOfPriority() >= highestPriority.getValueOfPriority()) {
                highestPriority = listElement.getPriority();
            }
        } while ((listElement = listElement.next) != null);
        if (head.getPriority().getValueOfPriority() == highestPriority.getValueOfPriority()) {
            ret = head;
            head = head.next;
            return ret;
        }
        listElement = head;
        while (listElement.next != null) {
            if (listElement.next.getPriority().getValueOfPriority() == highestPriority.getValueOfPriority()) {
                if (tail == listElement.next) {
                    tail = listElement;
                }
                ret = listElement.next;
                listElement.next = listElement.next.next;
                return ret;
            }
            listElement = listElement.next;
        }
        return ret;
    }


    synchronized boolean hasListElements() {
        return (head != null);
    }

    synchronized String viewList() {
        String list = "Очередь процессов:\n";
        if (head == null) return list = "Приоритетная очередь пуста.\n";
        ListElement<E> listElement = head;
        do {
            list = list + "Имя процесса: " + listElement.getProcessName() + ", приоритет: " + listElement.getPriority().getValueOfPriority() + ", значение: " + listElement.getSomeValue() + "\n";

        } while ((listElement = listElement.next) != null);
        return list;
    }

}




