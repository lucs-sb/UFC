package automaton;

public class Transition {
    private State origin;
    private State destiny;
    private String symbols;

    public Transition(State origin, State destiny, String symbols) {
        this.origin = origin;
        this.destiny = destiny;
        this.symbols = symbols;
    }

    public State getOrigin() {
        return origin;
    }

    public void setOrigin(State origin) {
        this.origin = origin;
    }

    public State getDestiny() {
        return destiny;
    }

    public void setDestiny(State destiny) {
        this.destiny = destiny;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }
}
