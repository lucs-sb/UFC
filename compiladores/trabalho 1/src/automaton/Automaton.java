package automaton;

import java.util.HashMap;
import java.util.HashSet;

public class Automaton {
    private HashMap<Integer, State> states = new HashMap<>();
    private HashMap<Integer, State> finalState = new HashMap<>();
    private HashSet<Transition> transitions = new HashSet<>();
    private State startState;

    private static int startStateLimit = 0;

    public Automaton() {
    }

    public void setState(int id, String label) {
        this.states.put(id, new State(id, String.valueOf(id), label));
    }

    public State getFinalState(int id) {
        return finalState.get(id);
    }

    public void setFinalState(int id) {
        this.finalState.put(id, this.states.get(id));
    }

    public Transition getTransition(int origin, char symbol) {
        for (Transition transition : this.transitions) {
            for (char s : transition.getSymbols().toCharArray())
                if (transition.getOrigin().getId() == origin && symbol == s)
                    return transition;
        }
        return null;
    }

    public void setTransition(int origin, int destiny, String symbols) {
        this.transitions.add(new Transition(this.states.get(origin), this.states.get(destiny), symbols));
    }

    public State getState(int id) {
        return this.states.get(id);
    }

    public State getStartState() {
        return this.startState;
    }

    public void setStartState(int id) {
        this.startState = this.states.get(id);
    }

    public boolean isFinalState(int id) {
        return this.finalState.get(id) != null;
    }

    public int getFinalStateSize() {
        return this.finalState.size();
    }
}
