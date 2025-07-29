package model;

import org.junit.jupiter.api.Test;
import util.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void йtasksShoulbeEqualsIfIdsAreEqual() {
        ArrayList<String> descriptions1 = new ArrayList<>();
        descriptions1.add("1");
        descriptions1.add("2");
        Task task1 = new Task("Task1", descriptions1, TaskStatus.NEW);
        task1.setId(3);
        ArrayList<String> descriptions2 = new ArrayList<>();
        descriptions2.add("1");
        descriptions2.add("2");
        Task task2 = new Task("Task2", descriptions2, TaskStatus.NEW);
        task2.setId(3);

        assertEquals(task1, task2, "Задачи с одинаковым ID должны совпадать");
        assertEquals(task1.hashCode(), task2.hashCode(), "Хэш-коды должны совпадать");
    }

}