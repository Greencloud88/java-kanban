import manager.Manager;
import manager.TaskManager;
import model.Task;
import model.Epic;
import model.SubTask;
import util.TaskStatus;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = Manager.getDefault();

        ArrayList<String> descriptions = new ArrayList<>();
        descriptions.add("Начало задачи");
        descriptions.add("Конец задачи");
        Task task1 = new Task("Задача 1", descriptions, TaskStatus.NEW);
        manager.addTask(task1);
        manager.getTask(task1.getId());

        ArrayList<String> descriptionsEpic = new ArrayList<>();
        descriptionsEpic.add("Начало Эпика");
        descriptionsEpic.add("Конец Эпика");
        Epic epic = new Epic("Эпик 1", descriptionsEpic);
        manager.addEpic(epic);
        manager.getEpic(epic.getId());

        ArrayList<String> descriptionsSubTask = new ArrayList<>();
        descriptionsSubTask.add("Начало Подзадачи");
        descriptionsSubTask.add("Конец Подзадачи");
        SubTask subTask = new SubTask("Подзадача", descriptionsSubTask, TaskStatus.NEW, epic.getId());
        manager.addSubTask(subTask);
        manager.getSubTask(subTask.getId());

        printAllTasks(manager);
    }
        private static void printAllTasks(TaskManager manager) {
            System.out.println("Задачи:");
            for (Task task : manager.getAllTasks()) {
                System.out.println(task);
            }
            System.out.println("Эпики:");
            for (Task epic : manager.getAllEpics()) {
                System.out.println(epic);

                for (Task task : manager.getSubTasksOfEpic(epic.getId())) {
                    System.out.println("--> " + task);
                }
            }
            System.out.println("Подзадачи:");
            for (Task subtask : manager.getAllSubTasks()) {
                System.out.println(subtask);
            }

            System.out.println("История:");
            for (Task task : manager.getHistory()) {
                System.out.println(task);
            }
        }



}
