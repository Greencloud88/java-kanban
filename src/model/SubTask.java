package model;

import util.TaskStatus;

import java.util.ArrayList;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, ArrayList<String> description, TaskStatus taskStatus, int epicID) {
        super(name, description, taskStatus);
        this.epicId = epicID;
    }

    public int getEpicId() {
        return epicId;
    }
}
