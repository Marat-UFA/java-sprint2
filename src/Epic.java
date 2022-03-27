import java.util.ArrayList;

public class Epic extends Task {

    private String statusEpic;
    private ArrayList<Integer> EpicArrayList;
    public static int indexId;

    public String getStatusEpic() {
        return this.statusEpic;
    }
    public void setStatusEpic(String statusEpic) {
        this.statusEpic = statusEpic;
    }
    public ArrayList<Integer> getEpicArrayList() {
        return EpicArrayList;
    }
    public void setEpicArrayList(ArrayList<Integer> epicArrayList) {
        EpicArrayList = epicArrayList;
    }
    public Epic(String title, String description, String statusEpic, ArrayList<Integer> EpicArrayList) {
        setTitle(title);
        setDescription(description);
        setStatusEpic(statusEpic);
        EpicArrayList = new ArrayList<Integer>();
    }

    public static void statusEpic(int epicKey) { // определение статуса эпика

        String determinStatusEpic = "Not";
        String status;
        Epic epic = Manager.EpicHashMap.get(epicKey);

        for (int i = 0; i < epic.getEpicArrayList().size(); i++) {
            indexId = epic.getEpicArrayList().get(i);
            Subtask subtask = Manager.SubtaskHashMap.get(indexId);
            status = subtask.getStatus();
            if (determinStatusEpic.equals("Not")) {
                epic.setStatusEpic(status);
                determinStatusEpic = status;
            } else if (epic.getStatusEpic().equals("IN_PROGRESS")) {
                continue;
            } else if (epic.getStatusEpic().equals(subtask.getStatus())) {
                continue;
            } else {
                epic.setStatusEpic("IN_PROGRESS");
            }
        }
        if (epic.getEpicArrayList() == null || epic.getEpicArrayList().size() == 0) {
            epic.setStatusEpic("NEW");
        }
        Epic epics = Manager.EpicHashMap.get(epicKey);
    }
}