import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Manager {

    public static int id;
    public static int indexId;

    public static ArrayList<Integer> EpicArrayList;
    public static HashMap<Integer, Task> TaskHashMap = new HashMap<>();
    public static HashMap<Integer, Epic> EpicHashMap = new HashMap<>();
    public static HashMap<Integer, Subtask> SubtaskHashMap = new HashMap<>();


    public static void task(String title, String description, String status) { // создание новой задачи
        id++;
        Task task = new Task(title, description, status);
        TaskHashMap.put(id, task);
    }

    public static void epic(String title, String description) { // создание нового эпика
        id++;
        String statusEpic = "NEW";
        Epic epic = new Epic(title, description, statusEpic, EpicArrayList);
        EpicHashMap.put(id, epic);
    }

    public static void subtask(String title, String description, String status) { // создание новой подзадачи
        id++;
        Subtask subtask = new Subtask(title, description, status, 0);
        SubtaskHashMap.put(id, subtask);
        EpicAndSubtaskLink.EpicAndSubtaskLink(title, status, Manager.id);
    }

    public static void taskList() { // Получение списка всех задач

        if (TaskHashMap.isEmpty()) {
            System.out.println("Список задач пуст");
        }
        if (EpicHashMap.isEmpty()) {
            System.out.println("Список эпиков пуст");
        }
        if (SubtaskHashMap.isEmpty()) {
            System.out.println("Список подзадач пуст");
        }

        for (Map.Entry<Integer, Task> entry : TaskHashMap.entrySet()) {
            System.out.println("Задача id: " + entry.getKey() + " название: " + entry.getValue().getTitle());
        }
        for (Map.Entry<Integer, Epic> entry : EpicHashMap.entrySet()) {
            System.out.println("Эпик: id: " + entry.getKey() + " название: " + entry.getValue().getTitle());
        }
        for (Map.Entry<Integer, Subtask> entry : SubtaskHashMap.entrySet()) {
            System.out.println("Поздача: id: " + entry.getKey() + " название: " + entry.getValue().getTitle());
        }
    }

    public static void clearTask() { // Удаление всех задач
        TaskHashMap.clear();
        EpicHashMap.clear();
        SubtaskHashMap.clear();
    }

    public static void listId() { // формирование списка по id

        System.out.println("-----список-----");

        for (Map.Entry<Integer, Task> entry : TaskHashMap.entrySet()) {
            System.out.println("Задача id: " + entry.getKey());
        }
        for (Map.Entry<Integer, Epic> entry : EpicHashMap.entrySet()) {
            System.out.println("Эпик id: " + entry.getKey());
        }
        for (Map.Entry<Integer, Subtask> entry : SubtaskHashMap.entrySet()) {
            System.out.println("Подзадача id: " + entry.getKey());
        }
    }

    public static void selectById(Scanner scanner) { // Получение по идентификатору

        if (TaskHashMap.isEmpty() && EpicHashMap.isEmpty() && SubtaskHashMap.isEmpty()){
            System.out.println("список пуст");
            return;
        }

        listId();

        System.out.println("Укажите номер id для вывода данных");
        int command = scanner.nextInt();

        for (Map.Entry<Integer, Task> entry : TaskHashMap.entrySet()) {
            if (command == entry.getKey()) {
                System.out.println("Тип данных: Задача.  id: " + entry.getKey());
                System.out.println("Название: " + entry.getValue().getTitle());
                System.out.println("Описание: " + entry.getValue().getDescription());
                System.out.println("Статус: " + entry.getValue().getStatus());
            }
        }
        for (Map.Entry<Integer, Epic> entry : EpicHashMap.entrySet()) {
            if (command == entry.getKey()) {
                System.out.println("Тип данных: Эпик.  id: " + entry.getKey());
                System.out.println("Название: " + entry.getValue().getTitle());
                System.out.println("Описание: " + entry.getValue().getDescription());
                System.out.println("Статус: " + entry.getValue().getStatusEpic());
            }
        }
        for (Map.Entry<Integer, Subtask> entry : SubtaskHashMap.entrySet()) {
            if (command == entry.getKey()) {
                System.out.println("Тип данных: Подзадача.  id: " + entry.getKey());
                System.out.println("Название: " + entry.getValue().getTitle());
                System.out.println("Описание: " + entry.getValue().getDescription());
                System.out.println("Статус: " + entry.getValue().getStatus());
            }
        }
    }

    public static void deleteById(Scanner scanner) { // Удаление по идентификатору

        if (TaskHashMap.isEmpty() && EpicHashMap.isEmpty() && SubtaskHashMap.isEmpty()){
            System.out.println("список пуст");
            return;
        }

        listId();

        System.out.println("Укажите номер id для удаления");
        int command = scanner.nextInt();

        for (Map.Entry<Integer, Task> entry : TaskHashMap.entrySet()) {
            if (command == entry.getKey()) {
                TaskHashMap.remove(command);
                return;
            }
        }
        for (Map.Entry<Integer, Epic> entry : EpicHashMap.entrySet()) {
            if (command == entry.getKey()) {
                for (int i = 0; i < entry.getValue().getEpicArrayList().size(); i++) {
                    Epic epic = Manager.EpicHashMap.get(command);
                    indexId = epic.getEpicArrayList().get(i);
                    SubtaskHashMap.remove(indexId);
                }
                EpicHashMap.remove(command);
                return;
            }
        }
        for (Map.Entry<Integer, Subtask> entry : SubtaskHashMap.entrySet()) {
            if (command == entry.getKey()) {
                EpicAndSubtaskLink.ListEpic = new ArrayList<>();
                Subtask subtask = Manager.SubtaskHashMap.get(command);
                indexId = subtask.getIdEpic();
                Epic epic = Manager.EpicHashMap.get(indexId);
                for (int i = 0; i < epic.getEpicArrayList().size(); i++) {
                    EpicAndSubtaskLink.ListEpic = epic.getEpicArrayList();
                    if (command == epic.getEpicArrayList().get(i)) {
                        EpicAndSubtaskLink.ListEpic.remove(i);
                        epic.setEpicArrayList(EpicAndSubtaskLink.ListEpic);
                    }
                }
                SubtaskHashMap.remove(command);
                Epic.statusEpic(indexId);

                return;
            }
        }
    }


    public static void listOfEpicSubtasks(Scanner scanner) { // Получение списка всех подзадач определённого эпика


        if (EpicHashMap.isEmpty()){
            System.out.println("список пуст");
            return;
        }

        for (Map.Entry<Integer, Epic> entry : EpicHashMap.entrySet()) {
            System.out.println("Эпик id: " + entry.getKey() + ", название: " + entry.getValue().getTitle());
        }
        System.out.println("--------");
        System.out.println("Введите id выбранного эпика:");
        int command = scanner.nextInt();
        Epic epic = EpicHashMap.get(command);

        if (epic.getEpicArrayList() == null || epic.getEpicArrayList().size() == 0) {
            System.out.println("Подзадачи отсутствуют");
        } else {
            for (int i = 0; i < epic.getEpicArrayList().size(); i++) {
                //Epic epics = Manager.EpicHashMap.get(command);
                indexId = epic.getEpicArrayList().get(i);
                //SubtaskHashMap.get(indexId).getTitle();
                System.out.println(SubtaskHashMap.get(indexId).getTitle());
            }
        }

    }

    public static void dataUpdate(Scanner scanner) { // Обновление данных

        if (TaskHashMap.isEmpty() && EpicHashMap.isEmpty() && SubtaskHashMap.isEmpty()){
            System.out.println("список пуст");
            return;
        }

        taskList();
        System.out.println("Укажите номер id для обновления данных");
        int command = scanner.nextInt();
        String name;// = scanner.next();

        for (Map.Entry<Integer, Task> entry : TaskHashMap.entrySet()) {
            if (command == entry.getKey()) {
                System.out.println("Тип данных: Задача.  id: " + entry.getKey());
                System.out.println("Введите новое название: ");
                name = scanner.next();
                entry.getValue().setTitle(name);
                System.out.println("Введите новое описание: ");
                name = scanner.next();
                entry.getValue().setDescription(name);
                System.out.println("Укажите новый статус (NEW, IN_PROGRESS, DONE): ");
                name = scanner.next();
                entry.getValue().setStatus(name);
            }
        }
        for (Map.Entry<Integer, Epic> entry : EpicHashMap.entrySet()) {
            if (command == entry.getKey()) {
                System.out.println("Тип данных: Эпик.  id: " + entry.getKey());
                System.out.println("Введите новое название: ");
                name = scanner.next();
                entry.getValue().setTitle(name);
                System.out.println("Введите новое описание: ");
                name = scanner.next();
                entry.getValue().setDescription(name);
            }
        }
        for (Map.Entry<Integer, Subtask> entry : SubtaskHashMap.entrySet()) {
            if (command == entry.getKey()) {
                System.out.println("Тип данных: Подзадача.  id: " + entry.getKey());
                System.out.println("Введите новое название: ");
                name = scanner.next();
                entry.getValue().setTitle(name);
                System.out.println("Введите новое описание: ");
                name = scanner.next();
                entry.getValue().setDescription(name);
                System.out.println("Укажите новый статус (NEW, IN_PROGRESS, DONE): ");
                name = scanner.next();
                entry.getValue().setStatus(name);
                System.out.println("Изменить статус привязки к эпику? y n");
                name = scanner.next();
                if (name.equals("y")) {
                    indexId = entry.getValue().getIdEpic();
                    Epic epic = Manager.EpicHashMap.get(indexId);
                    for (int i = 0; i < epic.getEpicArrayList().size(); i++) {
                        EpicAndSubtaskLink.ListEpic = epic.getEpicArrayList();
                        if (command == epic.getEpicArrayList().get(i)) {
                            EpicAndSubtaskLink.ListEpic.remove(i);
                            epic.setEpicArrayList(EpicAndSubtaskLink.ListEpic);
                        }
                    }
                    EpicAndSubtaskLink.EpicAndSubtaskLink(entry.getValue().getTitle(), entry.getValue().getStatus(), entry.getKey());
                    Epic.statusEpic(indexId);
                } else {
                    System.out.println("Статус привязки к эпику не изменен");
                    indexId = entry.getValue().getIdEpic();
                    Epic.statusEpic(indexId);
                }
            }
        }
    }
}
