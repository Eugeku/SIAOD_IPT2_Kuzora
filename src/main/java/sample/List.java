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
//Find Max priority in list
        ListElement<E> listElement = head;
        do {
            if (listElement.getPriority().getValueOfPriority() >= highestPriority.getValueOfPriority()) {
                highestPriority = listElement.getPriority();
            }
        } while ((listElement = listElement.next) != null);

//        if (head.data == data) {    //если первый элемент - тот, что нам нужен
//            head = head.next;       //переключаем указатель начала на второй элемент
//            return;                 //и выходим
//        }
//
//        ListElement t = head;       //иначе начинаем искать
//        while (t.next != null) {    //пока следующий элемент существует
//            if (t.next.data == data) {  //проверяем следующий элемент
//                if(tail == t.next)      //если он последний
//                {
//                    tail = t;           //то переключаем указатель на последний элемент на текущий
//                }
//                t.next = t.next.next;   //найденный элемент выкидываем
//                return;                 //и выходим
//            }
//            t = t.next;                //иначе ищем дальше

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




