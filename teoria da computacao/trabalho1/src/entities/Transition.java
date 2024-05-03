package entities;

import entities.State;

public class Transition {
    public State origin;
    public String originSymbol;
    public State destination;
    public String destinationSymbol;

    public Transition(State origin, String originSymbol, State destination, String destinationSymbol) {
        this.origin = origin;
        this.originSymbol = originSymbol;
        this.destination = destination;
        this.destinationSymbol = destinationSymbol;
    }
}
