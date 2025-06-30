package model;

import org.junit.jupiter.api.Test;
import util.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {
    @Test
    void testSubTaskCannotBeOwnEpic() {
        SubTask subTask = new SubTask("SubTask", new ArrayList<>(), TaskStatus.NEW, 0);
        subTask.setId(42);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            subTask.setEpicId(42); // пытаемся сделать эпиком самого себя
        });
        assertEquals("SubTask cannot be its own Epic.", exception.getMessage());
    }
}