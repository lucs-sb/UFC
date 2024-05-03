package automaton;

public class State {
    private int id;
    private String name;
    private String label;

    public State(int id, String name, String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
