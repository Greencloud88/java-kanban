package model;

import util.TaskStatus;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, ArrayList<String> description) {

        super(name, description, TaskStatus.NEW);

    }

    public Epic(Epic other) {
        super(other);
        this.subTaskIds.addAll(other.subTaskIds);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void addSubTaskId(int id) {
        if (id == this.getId()) {
            throw new IllegalArgumentException("Эпик не может содержать себя , как подзадачу");
        }
        subTaskIds.add(id);
    }

    @Override
    public void setId(int idTask) {
        super.setId(idTask);
    }

    public void removeSubTaskId(int id) {
        subTaskIds.remove((Integer) id);
    }

    public void clearSubTaskIds() {
        subTaskIds.clear();
    }

    @Override
    public void setStatus(TaskStatus status) {
    }
}
