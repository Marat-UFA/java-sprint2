
public class Task {

    private String title;
    private String description;
    private String status;

    public Task() {
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        Task.this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        Task.this.description = description;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        Task.this.status = status;
    }

    public Task(String title, String description, String status) {
        setTitle(title);
        setDescription(description);
        setStatus(status);
    }
}
