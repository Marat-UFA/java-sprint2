
public class Subtask extends Task {

    private int idEpic;

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    public Subtask (String title, String description, String status, int idEpik){
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setIdEpic(idEpic);
    }

}


