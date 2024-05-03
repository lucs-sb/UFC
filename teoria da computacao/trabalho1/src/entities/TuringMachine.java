package entities;

import entities.State;
import entities.Transition;

import java.util.ArrayList;
import java.util.List;

public class TuringMachine {
    public List<State> states = new ArrayList<>();
    public List<String> alphabet = new ArrayList<>();
    public List<Transition> transitions = new ArrayList<>();
    public State initialState;
    public List<String> log = new ArrayList<>();
}
