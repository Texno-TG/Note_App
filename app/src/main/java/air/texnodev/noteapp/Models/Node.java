package air.texnodev.noteapp.Models;

public class Node {

    private String name, time, full_text;

    public Node(String name, String time, String full_text) {
        this.name = name;
        this.time = time;
        this.full_text = full_text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
}
