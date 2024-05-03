package entities;

public class State {
    public String name;
    public boolean isInitial;
    public boolean isFinal;

    public State(String name, boolean isInitial, boolean isFinal) {
        this.name = name;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
    }
}
