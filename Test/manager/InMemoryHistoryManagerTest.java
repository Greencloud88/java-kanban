package manager;

import manager.HistoryManager;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
class InMemoryHistoryManagerTest {
    private HistoryManager history;

    @BeforeEach
    void setup() {
        history = new InMemoryHistoryManager();
    }

    @Test
    void testAddStoresCopyNotReference() {

        ArrayList<String> description = new ArrayList<>();
        description.add("Описание");
        Task originalTask = new Task("Тестовая задача", description, TaskStatus.NEW);
        originalTask.setId(1);

        history.add(originalTask);

        originalTask.setName("смена имени задачи");
        originalTask.getDescription().add("дополнительная информация");
        originalTask.setStatus(TaskStatus.DONE);

        Task taskFromHistory = history.getHistory().get(0);

        assertEquals("Тестовая задача", taskFromHistory.getName());
        assertEquals(1, taskFromHistory.getDescription().size());
        assertEquals("Описание", taskFromHistory.getDescription().get(0));
        assertEquals(TaskStatus.NEW, taskFromHistory.getStatus());
    }


}