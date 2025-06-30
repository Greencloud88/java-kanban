package manager;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private final LinkedList<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            Task copy;
            if (task instanceof SubTask) {
                copy = new SubTask((SubTask) task);
            } else if (task instanceof Epic) {
                copy = new Epic((Epic) task);  // Предполагается, что у Epic есть конструктор копирования
            } else {
                copy = new Task(task);
            }
            history.add(copy);
        }
        if (history.size() > 10) {
            history.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(history);
    }

}
