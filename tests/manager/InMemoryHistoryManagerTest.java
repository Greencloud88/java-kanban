package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class InMemoryHistoryManagerTest {
    private HistoryManager historyManager;
    private Task task1;
    private Task task2;
    private SubTask subTask;
    private Epic epic;

    @BeforeEach
    void setup() {
        historyManager = Manager.getDefaultHistory();
        task1 = new Task("Task1", new ArrayList<>(), TaskStatus.NEW);
        task1.setId(1);
        task2 = new Task("Task2", new ArrayList<>(), TaskStatus.DONE);
        task2.setId(2);

        epic = new Epic("Epic1", new ArrayList<>());
        epic.setStatus(TaskStatus.IN_PROGRESS);
        epic.setId(3);

        subTask = new SubTask("SubTask1", new ArrayList<>(), TaskStatus.NEW, epic.getId());
        subTask.setId(4);
    }

    @Test
    void shouldAddTasksToHistory() {
        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
    }

    @Test
    void shouldMoveTaskToEndIfReadded() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1); // Повторное добавление

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task2, history.get(0));
        assertEquals(task1, history.get(1));
    }

    @Test
    void shouldHandleDifferentTaskTypes() {
        historyManager.add(task1);
        historyManager.add(epic);
        historyManager.add(subTask);

        List<Task> history = historyManager.getHistory();
        assertEquals(List.of(task1, epic, subTask), history);
    }

    @Test
    void shouldRemoveTaskFromHistoryById() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(1);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task2, history.get(0));
    }

    @Test
    void shouldReturnEmptyListIfHistoryIsEmpty() {
        assertTrue(historyManager.getHistory().isEmpty());
    }

    @Test
    void shouldHandleRemoveNonExistentTaskGracefully() {
        historyManager.add(task1);
        historyManager.remove(999); // id не существует
        assertEquals(1, historyManager.getHistory().size());
    }
}

