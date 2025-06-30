package model;

import util.TaskStatus;

import java.util.ArrayList;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, ArrayList<String> description, TaskStatus taskStatus, int epicID) {
        super(name, description, taskStatus);
        this.epicId = epicID;
    }

    public SubTask(SubTask other) {
        super(other);
        this.epicId = other.epicId;
    }

    public int getEpicId() {
        if (epicId == this.getId()) {
            throw new IllegalArgumentException("Подзадача не может содержать свой Эпик");
        }
        return epicId;
    }

    public void setEpicId(int epicId) {
        if (epicId == this.getId()) {
            throw new IllegalArgumentException("SubTask cannot be its own Epic.");
        }
        this.epicId = epicId;
    }
}
