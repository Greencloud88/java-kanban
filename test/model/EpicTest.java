package model;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    private Epic epic;
    private InMemoryTaskManager manager;

    @BeforeEach
    void create() {
        ArrayList<String> descriptionsEpic1 = new ArrayList<>();
        descriptionsEpic1.add("1");
        descriptionsEpic1.add("2");
        epic = new Epic("Эпик 1", descriptionsEpic1);
        manager = new InMemoryTaskManager();
        manager.addEpic(epic);
    }

    @Test
    void testEpicAddAndGet() {
        Epic getEpic = manager.getEpic(epic.getId());
        assertNotNull(getEpic);
        assertEquals(epic, getEpic);
    }

    @Test
    void testDifferentTypesEqualsIfSameId() {
        ArrayList<String> description = new ArrayList<>();

        Epic epic = new Epic("Epic", description);
        SubTask subTask = new SubTask("Sub", description, TaskStatus.NEW, 0);

        epic.setId(100);
        subTask.setId(100);

        assertEquals(epic, subTask); // Сравнение объектов разных подклассов
        assertEquals(epic.hashCode(), subTask.hashCode());
    }

    @Test
    void testEpicCannotContainItself() {
        epic.setId(42); // важно задать ID перед проверкой
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            epic.addSubTaskId(42); // добавление самого себя
        });
        assertEquals("Эпик не может содержать себя , как подзадачу", exception.getMessage());
    }

}