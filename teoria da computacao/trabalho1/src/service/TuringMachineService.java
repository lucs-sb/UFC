package service;

import entities.State;
import entities.Transition;
import entities.TuringMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TuringMachineService {

    public static void deterministicTuringMachineSimulator() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int op;
        TuringMachine turingMachine = getConfigurationByFile();
        do{
            System.out.println("\n\t\t\t\tSIMULADOR DA MÁQUINA DE TURING\n");
            System.out.println("\t\t\t\t1  - CRIAR UMA NOVA CONFIGURAÇÃO\n\t\t\t\t2  - INICIAR\n\t\t\t\t3  - VER CONFIGURAÇÃO ATUAL\n\t\t\t\t4  - VER LOG DA ÚLTIMA EXECUÇÃO\n\t\t\t\t0  - SAIR");
            System.out.print("\nINFORME SUA OPCAO: ");
            op = scanner.nextInt();

            switch(op) {
                case 1:
                    turingMachine = getConfigurationByFile();
                    break;
                case 2:
                    System.out.print("\nINFORME A ENTRADA: ");
                    String input = scanner.next();
                    startDeterministicTuringMachine(turingMachine, "$ " + input + " ");
                    break;
                case 3:
                    printConfiguration(turingMachine);
                    break;
                case 4:
                    printLog(turingMachine.log);
                    break;
                case 0:
                    System.out.println("\nENCERRANDO PROGRAMA");
                    break;
                default:
                    System.out.println("\nOPCAO INCORRETA, TENTAR NOVAMENTE.");
                    break;
            }
        }
        while(op != 0);
    }

    private static void printLog(List<String> log) {
        log.forEach(System.out::println);
    }

    private static TuringMachine getConfigurationByFile() throws IOException {
        Path path = Paths.get("C:/Users/soare/OneDrive/Área de Trabalho/configuracao maquina de turing.txt");
        List<String> linhasArquivo = Files.lines(path).toList();
        TuringMachine turingMachine = new TuringMachine();
        List<State> states = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();

        String[] symbols = linhasArquivo.get(1).split(",");

        List<String> alphabet = new ArrayList<>(Arrays.asList(symbols));

        State initialState = new State(linhasArquivo.get(3), true, false);
        states.add(initialState);

        for (String s : linhasArquivo.get(5).split(",")) {
            State state = new State(s, false, true);
            states.add(state);
        }

        for (String s : linhasArquivo.get(7).split(",")) {
            State state = new State(s, false, false);
            states.add(state);
        }

        String[] newTransitions = linhasArquivo.get(9).split(";");

        for (String s : newTransitions) {
            String[] newTransition = s.split(",");
            State origin = getState(states, newTransition[0]);
            State destination = getState(states, newTransition[2]);
            Transition transition = new Transition(origin, newTransition[1], destination, newTransition[3]);
            transitions.add(transition);
        }

        turingMachine.alphabet = alphabet;
        turingMachine.initialState = initialState;
        turingMachine.states = states;
        turingMachine.transitions = transitions;

        return turingMachine;
    }

    private static TuringMachine createNewConfiguration(){
        TuringMachine turingMachine = new TuringMachine();
        List<String> alphabet = new ArrayList<>();
        List<State> states = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n\t\t\t\tCONFIGURANDO MÁQUINA DE TURING\n");
            System.out.print("\tCONFIGURANDO ALFABETO\n");
            System.out.println("\tOBS: NÃO PRECISA COLOCAR O MARCADOR DE FITA OU VAZIO\n");
            System.out.print("INFORME O ALFABETO SEPARADO POR '-': ");
            String input = scanner.nextLine();

            String[] symbols = input.split("-");

            alphabet.add("$");
            alphabet.add(" ");
            alphabet.addAll(Arrays.asList(symbols));

            System.out.println("\tCONFIGURAR ESTADOS OU DIGITAR O ALFABETO NOVAMENTE ?\n");
            System.out.println("\tDIGITE AVANCAR PARA CONFIGURAR OS ESTADOS OU QUALQUER TECLA PARA CONTINUAR EDITANDO\n");
            input = scanner.next();
            if ("AVANCAR".equals(input))
                break;
        }

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n\t\t\t\tCONFIGURANDO MÁQUINA DE TURING\n");
            System.out.print("\tCONFIGURANDO ESTADOS\n");
            System.out.print("\tDIGITE O NOME DO ESTADO, SE ELE É INICIAL E SE É FINAL SEPARADO POR '-'\n");
            System.out.print("\tE OS ESTADOS SEPARADO POR ';'. EX: q0-s-n;q1-n-n;h-n-s\n");
            System.out.print("INFORME O ESTADO: ");
            String input = scanner.nextLine();

            String[] newStates = input.split(";");

            for (String s : newStates) {
                String[] newState = s.split("-");
                boolean isInitial = "s".equals(newState[1]);
                boolean isFinal = "s".equals(newState[2]);
                State state = new State(newState[0], isInitial, isFinal);
                if (isInitial)
                    turingMachine.initialState = state;
                states.add(state);
            }

            System.out.println("\tCONFIGURAR FUNÇÕES DE ESTADOS OU DIGITAR OS ESTADOS NOVAMENTE ?\n");
            System.out.println("\tDIGITE AVANCAR PARA CONFIGURAR AS FUNÇÕES DE TRANSIÇÃO OU QUALQUER TECLA PARA CONTINUAR EDITANDO\n");
            input = scanner.next();
            if ("AVANCAR".equals(input))
                break;
        }

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n\t\t\t\tCONFIGURANDO MÁQUINA DE TURING\n");
            System.out.print("\tCONFIGURANDO TRANSIÇÃO DE ESTADOS\n");
            System.out.print("\tDIGITE A FUNÇÃO DE TRANSIÇÃO SEPARADO POR '-'\n");
            System.out.print("\tE AS FUNÇÕES SEPARADO POR ';'. EX: q0-a-q1->;q1-a-q0-b\n");
            System.out.print("INFORME O ESTADO: ");
            String input = scanner.nextLine();

            String[] newTransitions = input.split(";");

            for (String s : newTransitions) {
                String[] newTransition = s.split("-");
                State origin = getState(states, newTransition[0]);
                State destination = getState(states, newTransition[2]);
                Transition transition = new Transition(origin, newTransition[1], destination, newTransition[3]);
                transitions.add(transition);
            }

            System.out.println("\tVOLTAR PARA O MENU PRINCIPAL OU DIGITAR AS FUNÇÕES DE ESTADOS NOVAMENTE ?\n");
            System.out.println("\tDIGITE AVANCAR PARA RETORNAR AO INICIO OU QUALQUER TECLA PARA CONTINUAR EDITANDO\n");
            input = scanner.next();
            if ("AVANCAR".equals(input))
                break;
        }

        turingMachine.alphabet = alphabet;
        turingMachine.states = states;
        turingMachine.transitions = transitions;

        return turingMachine;
    }

    private static void startDeterministicTuringMachine(TuringMachine turingMachine, String input){
        if (turingMachine == null) {
            System.out.println("\n\n\tMÁQUINA NÃO CONFIGURADA! APERTE 1 PARA CONFIGURAR.\n\n");
            return;
        }

        for (char c : input.toCharArray()) {
            boolean flag = true;
            for (String x : turingMachine.alphabet)
                if (c == x.charAt(0)) {
                    flag = false;
                }
            if (flag) {
                System.out.println("\n\n\tENTRADA NÃO CORRESPONDE COM O ALFABETO CONFIGURADO.\n\n");
                return;
            }
        }

        List<String> log = new ArrayList<>();
        long transitionCounter = 0;
        long maximumNumberOfTransitions = 100;

        char[] tape = input.toCharArray();
        int headPosition = 1;
        State currentState = turingMachine.initialState;

        String log1 = "\n\tTransição atual: " + transitionCounter + "; Estado atual: " + currentState.name + "; Estado atual da fita: " + Arrays.toString(tape);

        log.add(log1);

        while (!currentState.isFinal) {
            for (Transition transition : turingMachine.transitions){
                if (transition.origin.name.equals(currentState.name) && transition.originSymbol.charAt(0) == tape[headPosition]){

                    currentState = transition.destination;

                    if (currentState.isFinal)
                        break;

                    if (">".equals(transition.destinationSymbol))
                        headPosition++;
                    else if ("<".equals(transition.destinationSymbol))
                        headPosition--;
                    else
                        tape[headPosition] = transition.destinationSymbol.charAt(0);

                    String log2 = "\n\tTransição atual: " + transitionCounter + "; Estado atual: " + currentState.name + "; Estado atual da fita: " + Arrays.toString(tape);

                    log.add(log2);

                    break;
                }
            }

            transitionCounter++;

            if (transitionCounter == maximumNumberOfTransitions && !currentState.isFinal){
                Scanner scanner = new Scanner(System.in);
                System.out.print("\n\tJÁ FORAM EXECUTADOS " + transitionCounter + " TRANSIÇÕES, DESEJA EXCUTAR MAIS " + maximumNumberOfTransitions + " ?");
                System.out.print("\n\tDIGITE 1 PARA CONTINUAR E 0 PARA PARAR");
                System.out.print("\n\tINFORME SUA OPCAO: ");
                int choice = scanner.nextInt();
                if (choice == 1)
                    maximumNumberOfTransitions += maximumNumberOfTransitions;
                else
                    break;
            }
        }

        String finish = "\n\tEstado que parou: " + currentState.name + "; Estado final da fita: " + Arrays.toString(tape);

        log.add(finish);

        turingMachine.log = log;

        System.out.println(finish);
    }

    private static void printConfiguration(TuringMachine turingMachine) {

        if (turingMachine == null) {
            System.out.println("\n\nMÁQUINA NÃO CONFIGURADA! APERTE 1 PARA CONFIGURAR.\n\n");
            return;
        }

        StringBuilder alphabet = new StringBuilder();
        StringBuilder states = new StringBuilder();
        StringBuilder transitions = new StringBuilder();

        turingMachine.alphabet.forEach(symbol -> alphabet.append(symbol).append(", "));

        turingMachine.states.forEach(state -> {
            states.append(state.name);
            if (state.isInitial)
                states.append(" - estado inicial");
            if (state.isFinal)
                states.append(" - estado final");
            states.append("\n");
        });

        turingMachine.transitions.forEach(transition -> {
            transitions
                    .append("&(").append(transition.origin.name).append(",").append(transition.originSymbol).append(")")
                    .append(" = (").append(transition.destination.name).append(",").append(transition.destinationSymbol).append(")")
                    .append("\n");
        });

        System.out.println("\n\t\t\t\tCONFIGURAÇÃO DA MÁQUINA DE TURING\n");
        System.out.println("\nALFABETO: " + alphabet);
        System.out.println("\nESTADOS: \n" + states);
        System.out.println("\nTRANSIÇÕES: \n" + transitions);
    }


    private static State getState(List<State> states, String name){
        for (State state : states)
            if (state.name.equals(name))
                return state;

        return null;
    }

    public static void nonDeterministicTuringMachineSimulator() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int op;
        TuringMachine turingMachine = getConfigurationByFile();
        do{
            System.out.println("\n\t\t\t\tSIMULADOR DA MÁQUINA DE TURING\n");
            System.out.println("\t\t\t\t1  - CRIAR UMA NOVA CONFIGURAÇÃO\n\t\t\t\t2  - INICIAR\n\t\t\t\t3  - VER CONFIGURAÇÃO ATUAL\n\t\t\t\t4  - VER LOG DA ÚLTIMA EXECUÇÃO\n\t\t\t\t0  - SAIR");
            System.out.print("\nINFORME SUA OPCAO: ");
            op = scanner.nextInt();

            switch(op) {
                case 1:
                    turingMachine = getConfigurationByFile();
                    break;
                case 2:
                    System.out.print("\nINFORME A ENTRADA: ");
                    String input = scanner.next();
                    startNonDeterministicTuringMachine(turingMachine, "$ " + input + " ");
                    break;
                case 3:
                    printConfiguration(turingMachine);
                    break;
                case 4:
                    printLog(turingMachine.log);
                    break;
                case 0:
                    System.out.println("\nENCERRANDO PROGRAMA");
                    break;
                default:
                    System.out.println("\nOPCAO INCORRETA, TENTAR NOVAMENTE.");
                    break;
            }
        }
        while(op != 0);
    }

    private static void startNonDeterministicTuringMachine(TuringMachine turingMachine, String input){
        if (turingMachine == null) {
            System.out.println("\n\n\tMÁQUINA NÃO CONFIGURADA! APERTE 1 PARA CONFIGURAR.\n\n");
            return;
        }

        for (char c : input.toCharArray()) {
            boolean flag = true;
            for (String x : turingMachine.alphabet)
                if (c == x.charAt(0)) {
                    flag = false;
                }
            if (flag) {
                System.out.println("\n\n\tENTRADA NÃO CORRESPONDE COM O ALFABETO CONFIGURADO.\n\n");
                return;
            }
        }

        List<String> log = new ArrayList<>();
        long transitionCounter = 0;
        long maximumNumberOfTransitions = 100;

        char[] tape = input.toCharArray();
        int headPosition = 1;
        State currentState = turingMachine.initialState;

        String log1 = "\n\tTransição atual: " + transitionCounter + "; Estado atual: " + currentState.name + "; Estado atual da fita: " + Arrays.toString(tape);

        log.add(log1);

        while (!currentState.isFinal) {
            for (Transition transition : turingMachine.transitions){
                if (transition.origin.name.equals(currentState.name) && transition.originSymbol.charAt(0) == tape[headPosition]){

                    currentState = transition.destination;

                    if (currentState.isFinal)
                        break;

                    if (">".equals(transition.destinationSymbol))
                        headPosition++;
                    else if ("<".equals(transition.destinationSymbol))
                        headPosition--;
                    else
                        tape[headPosition] = transition.destinationSymbol.charAt(0);

                    String log2 = "\n\tTransição atual: " + transitionCounter + "; Estado atual: " + currentState.name + "; Estado atual da fita: " + Arrays.toString(tape);

                    log.add(log2);

                    break;
                }
            }

            transitionCounter++;

            if (transitionCounter == maximumNumberOfTransitions && !currentState.isFinal){
                Scanner scanner = new Scanner(System.in);
                System.out.print("\n\tJÁ FORAM EXECUTADOS " + transitionCounter + " TRANSIÇÕES, DESEJA EXCUTAR MAIS " + maximumNumberOfTransitions + " ?");
                System.out.print("\n\tDIGITE 1 PARA CONTINUAR E 0 PARA PARAR");
                System.out.print("\n\tINFORME SUA OPCAO: ");
                int choice = scanner.nextInt();
                if (choice == 1)
                    maximumNumberOfTransitions += maximumNumberOfTransitions;
                else
                    break;
            }
        }

        String finish = "\n\tEstado que parou: " + currentState.name + "; Estado final da fita: " + Arrays.toString(tape);

        log.add(finish);

        turingMachine.log = log;

        System.out.println(finish);
    }
}
