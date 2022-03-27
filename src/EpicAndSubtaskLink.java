import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class EpicAndSubtaskLink {

    public static String subtaskStatus;
    public static int epicKey;
    public static int command;
    public static ArrayList<Integer> ListEpic;
    public static Scanner scanner = new Scanner(System.in);

    public static void EpicAndSubtaskLink(String title, String status, int id) { // привязка подзадачи к эпику
        subtaskStatus = status;

        if (Manager.EpicHashMap.size() == 0) {
            System.out.println("Отсутствуют эпики. Создание подзадачи не возможно");
            Manager.SubtaskHashMap.remove(id);
            return;
        }

        System.out.println("Укажите номер id к какому эпику относится подзадача id:" + id + ", название: " + title);

        for (Map.Entry<Integer, Epic> entryEpic : Manager.EpicHashMap.entrySet()) {
            epicKey = entryEpic.getKey();
            String epicTitle = entryEpic.getValue().getTitle();
            System.out.println("Эпик id:" + epicKey + ", название: " + epicTitle);
        }
        command = scanner.nextInt();

        Link(id);
    }

    public static void Link(int id) { // привязка эпика к подзадаче
        ListEpic = new ArrayList<>();

        Subtask subtask = Manager.SubtaskHashMap.get(id);
        subtask.setIdEpic(command);

        Epic epic = Manager.EpicHashMap.get(command);
        if (epic.getEpicArrayList() == null) {
            ListEpic.add(id);
            epic.setEpicArrayList(ListEpic);
        } else {
            ListEpic = epic.getEpicArrayList();
            ListEpic.add(id);
            epic.setEpicArrayList(ListEpic);
        }
        Epic.statusEpic(command);
    }
}
