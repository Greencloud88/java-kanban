import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, ArrayList<String> description) {

        super(name, description, TaskStatus.NEW);

    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }
    public void addSubTaskId(int id) {
        subTaskIds.add(id);
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
