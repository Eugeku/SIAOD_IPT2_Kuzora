package sample;

import java.io.Serializable;

/**
 * Created by Eugene13 on 24.11.2016.
 * enum Priority:
 * 1) Перечисления для задания приоритетов элементам очереди;
 */
enum Priority implements Serializable {
    LowPriority(1), Two(2), Three(3), Four(4), MiddlePriority(5), Six(6), Seven(7), Eight(8), Nine(9), HighPriority(10);
    private int f;

    Priority(int f) {
        this.f = f;
    }

    int getValueOfPriority() {
        return f;
    }
}
