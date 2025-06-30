package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    void setup() {
        manager = new InMemoryTaskManager();
    }

    @Test
    void testAddAndGetTasksById() {
        Task task = new Task("Задача 1", new ArrayList<>(), TaskStatus.NEW);
        manager.addTask(task);

        Epic epic = new Epic("Эпик 1", new ArrayList<>());
        manager.addEpic(epic);

        SubTask subTask = new SubTask("SubTask 1", new ArrayList<>(), TaskStatus.NEW, epic.getId());
        manager.addSubTask(subTask);

        assertEquals(task, manager.getTask(task.getId()));
        assertEquals(epic, manager.getEpic(epic.getId()));
        assertEquals(subTask, manager.getSubTask(subTask.getId()));
    }

    @Test
    void testNoIdConflictBetweenManualAndGeneratedIds() {

        Task manualIdTask = new Task("Устанавливаем ИД вручную", new ArrayList<>(), TaskStatus.NEW);
        manualIdTask.setId(1000);
        manager.addTask(manualIdTask);


        Task generatedIdTask = new Task("Генерируем ИД", new ArrayList<>(), TaskStatus.NEW);
        manager.addTask(generatedIdTask);

        assertNotNull(manager.getTask(1000));
        assertNotNull(manager.getTask(generatedIdTask.getId()));
        assertNotEquals(manualIdTask.getId(), generatedIdTask.getId());
        assertEquals(manualIdTask, manager.getTask(1000));
        assertEquals(generatedIdTask, manager.getTask(generatedIdTask.getId()));


        Epic manualIdEpic = new Epic("Устанавливаем ИД вручную", new ArrayList<>());
        manualIdEpic.setId(2000);
        manager.addEpic(manualIdEpic);

        Epic generatedIdEpic = new Epic("Генерируем ИД", new ArrayList<>());
        manager.addEpic(generatedIdEpic);

        assertNotNull(manager.getEpic(2000));
        assertNotNull(manager.getEpic(generatedIdEpic.getId()));
        assertNotEquals(manualIdEpic.getId(), generatedIdEpic.getId());
        assertEquals(manualIdEpic, manager.getEpic(2000));
        assertEquals(generatedIdEpic, manager.getEpic(generatedIdEpic.getId()));


        SubTask manualIdSubTask = new SubTask("Устанавливаем ИД вручную", new ArrayList<>(), TaskStatus.NEW, 0);
        manualIdSubTask.setId(3000);
        manager.addSubTask(manualIdSubTask);

        SubTask generatedIdSubTask = new SubTask("Генерируем ИД", new ArrayList<>(), TaskStatus.NEW, 0);
        manager.addSubTask(generatedIdSubTask);

        assertNotNull(manager.getSubTask(3000));
        assertNotNull(manager.getSubTask(generatedIdSubTask.getId()));
        assertNotEquals(manualIdSubTask.getId(), generatedIdSubTask.getId());
        assertEquals(manualIdSubTask, manager.getSubTask(3000));
        assertEquals(generatedIdSubTask, manager.getSubTask(generatedIdSubTask.getId()));
    }

    @Test
    void testTaskFieldsRemainUnchangedAfterAdd() {
        Task originalTask = new Task("Оригинальная задача", new ArrayList<>(), TaskStatus.NEW);
        originalTask.setId(42);

        String originalName = originalTask.getName();
        TaskStatus originalStatus = originalTask.getStatus();
        int originalId = originalTask.getId();

        manager.addTask(originalTask);

        Task retrievedTask = manager.getTask(originalId);

        assertNotNull(retrievedTask);
        assertEquals(originalName, retrievedTask.getName(), "Имя задачи изменилось");
        assertEquals(originalStatus, retrievedTask.getStatus(), "Статус задачи изменился");
        assertEquals(originalId, retrievedTask.getId(), "ИД задачи изменился");
    }

    @Test
    void testEpicWithSubTasksFieldsRemainUnchangedAfterAdd() {

        Epic originalEpic = new Epic("Оригинальный Эпик", new ArrayList<>());
        originalEpic.setId(100);

        SubTask subTask1 = new SubTask("Подзадача 1", new ArrayList<>(), TaskStatus.NEW, originalEpic.getId());
        subTask1.setId(101);
        SubTask subTask2 = new SubTask("Подзадача 2", new ArrayList<>(), TaskStatus.IN_PROGRESS, originalEpic.getId());
        subTask2.setId(102);


        manager.addEpic(originalEpic);
        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);

        String originalEpicName = originalEpic.getName();
        int originalEpicId = originalEpic.getId();
        ArrayList<Integer> originalSubTaskIds = new ArrayList<>(originalEpic.getSubTaskIds());

        String subTask1Name = subTask1.getName();
        TaskStatus subTask1Status = subTask1.getStatus();
        int subTask1Id = subTask1.getId();
        int subTask1EpicId = subTask1.getEpicId();

        String subTask2Name = subTask2.getName();
        TaskStatus subTask2Status = subTask2.getStatus();
        int subTask2Id = subTask2.getId();
        int subTask2EpicId = subTask2.getEpicId();

        // Получаем из менеджера
        Epic retrievedEpic = manager.getEpic(originalEpicId);
        SubTask retrievedSubTask1 = manager.getSubTask(subTask1Id);
        SubTask retrievedSubTask2 = manager.getSubTask(subTask2Id);

        assertNotNull(retrievedEpic);
        assertEquals(originalEpicName, retrievedEpic.getName(), "Имя эпика изменилось");
        assertEquals(originalEpicId, retrievedEpic.getId(), "ID эпика изменился");
        assertEquals(originalSubTaskIds, retrievedEpic.getSubTaskIds(), "Список подзадач эпика изменился");

        assertNotNull(retrievedSubTask1);
        assertEquals(subTask1Name, retrievedSubTask1.getName(), "Имя подзадачи 1 изменилось");
        assertEquals(subTask1Status, retrievedSubTask1.getStatus(), "Статус подзадачи 1 изменился");
        assertEquals(subTask1Id, retrievedSubTask1.getId(), "ID подзадачи 1 изменился");
        assertEquals(subTask1EpicId, retrievedSubTask1.getEpicId(), "ID эпика подзадачи 1 изменился");

        assertNotNull(retrievedSubTask2);
        assertEquals(subTask2Name, retrievedSubTask2.getName(), "Имя подзадачи 2 изменилось");
        assertEquals(subTask2Status, retrievedSubTask2.getStatus(), "Статус подзадачи 2 изменился");
        assertEquals(subTask2Id, retrievedSubTask2.getId(), "ID подзадачи 2 изменился");
        assertEquals(subTask2EpicId, retrievedSubTask2.getEpicId(), "ID эпика подзадачи 2 изменился");
    }
}