package model;

import util.TaskStatus;
import java.util.ArrayList;
import java.util.Objects;

public class Task {
    private int id;
    private String name;
    private ArrayList<String> description;
    private TaskStatus status;


    public Task(String name, ArrayList<String> description, TaskStatus status) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Task(Task other) {
        this.id = other.id;
        this.name = other.name;
        this.status = other.status;
        if (other.description != null) {
            this.description = new ArrayList<>(other.description);
        } else {
            this.description = new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int idTask) {
        this.id = idTask;
    }


    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false; // ключевое отличие — instanceof
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "name='" + name + '\'' +
                ", description=" + description +
                ", status=" + status +
                ", idTask=" + id +
                '}';
    }
}
