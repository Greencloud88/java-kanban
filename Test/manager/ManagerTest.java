package manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void testGetDefaultReturnsNonNull() {
        TaskManager taskManager = Manager.getDefault();
        assertNotNull(taskManager, "TaskManager не должен быть null");
    }

    @Test
    void testGetDefaultHistoryReturnsNonNull() {
        HistoryManager historyManager = Manager.getDefaultHistory();
        assertNotNull(historyManager, "HistoryManager не должен быть null");
        // Проверяем, что это InMemoryHistoryManager
        assertEquals(InMemoryHistoryManager.class, historyManager.getClass());
    }

    @Test
    void testTaskManagerIsReadyToUse() {
        TaskManager taskManager = Manager.getDefault();
        assertNotNull(taskManager);
    }

    @Test
    void testHistoryManagerIsReadyToUse() {
        HistoryManager historyManager = Manager.getDefaultHistory();
        assertNotNull(historyManager);
    }
}