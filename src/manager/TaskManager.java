package manager;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    //Tasks
    ArrayList<Task> getAllTasks();
    void deleteAllTasks();
    Task getTask(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);
    //Epics
    ArrayList<Epic> getAllEpics();
    void deleteAllEpics();
    Epic getEpic(int id);
    void addEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpic(int id);
    //SubTasks
    ArrayList<SubTask> getAllSubTasks();
    void deleteAllSubtasks();
    SubTask getSubTask(int id);
    void addSubTask(SubTask subTask);
    void updateSubTask(SubTask subTask);
    void deleteSubtask(int id);
    ArrayList<SubTask> getSubTasksOfEpic(int epicId);
    void updateEpicStatus(int epicId);
    //History
    List<Task> getHistory();
}
