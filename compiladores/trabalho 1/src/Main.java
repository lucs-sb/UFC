import automaton.Automaton;
import automaton.State;
import automaton.Transition;

public class Main {
    public static void main(String[] args) {

        String symbol = "44444";

        TAR(symbol);
    }

    public static void TAR(String input){

        if (input == null || input.isBlank())
            return;

        StringBuilder lexema = new StringBuilder("" + input.charAt(0));

        for (int i = 1; i < input.length(); i++){
            if (isSameToken(lexema.toString()+input.charAt(i)))
                lexema.append(input.charAt(i));
            else
                break;
        }

        String token = getToken(lexema.toString());

        System.out.println("Token: " + token + "," + "\t\tLexema: " + lexema);

        input = input.substring(lexema.length());

        TAR(input);
    }

    public static boolean isSameToken(String input){
        Automaton automaton = new Automaton();

        automaton.setState(0, "");
        automaton.setState(1, "ID");
        automaton.setState(2, "INTEIRO");
        automaton.setState(3, "OPERADOR");
        automaton.setState(4, "");
        automaton.setState(5, "FLUTUANTE");
        automaton.setState(6, "MORTE");

        automaton.setStartState(0);

        automaton.setFinalState(1);
        automaton.setFinalState(2);
        automaton.setFinalState(3);
        automaton.setFinalState(4);
        automaton.setFinalState(5);

        automaton.setTransition(0, 0, " ");
        automaton.setTransition(0, 1, "abcdefghijklmnopqrstuvxwyz");
        automaton.setTransition(0, 2, "0123456789");
        automaton.setTransition(0, 3, "+-*/");
        automaton.setTransition(1, 1, "abcdefghijklmnopqrstuvxwyz0123456789");
        automaton.setTransition(1, 6, "+-*/ ");
        automaton.setTransition(2, 2, "0123456789");
        automaton.setTransition(2, 4, ".");
        automaton.setTransition(4, 5, "0123456789");
        automaton.setTransition(5, 5, "0123456789");
        automaton.setTransition(3, 3, "+-*/");
        automaton.setTransition(2, 6, "abcdefghijklmnopqrstuvxwyz+-*/ ");
        automaton.setTransition(5, 6, "abcdefghijklmnopqrstuvxwyz+-*/ ");
        automaton.setTransition(3, 6, "abcdefghijklmnopqrstuvxwyz0123456789 ");
        automaton.setTransition(4, 6, "abcdefghijklmnopqrstuvxwyz+-*/ ");

        int origin = 0;

        for (int i = 0; i < input.length(); i++) {
            Transition transition = automaton.getTransition(origin, input.charAt(i));
            if (transition != null) {
                State destiny = transition.getDestiny();
                origin = destiny.getId();
            }
        }

        return automaton.isFinalState(origin);
    }

    public static String getToken(String input){
        Automaton automaton = new Automaton();

        automaton.setState(0, "");
        automaton.setState(1, "ID");
        automaton.setState(2, "INTEIRO");
        automaton.setState(3, "OPERADOR");
        automaton.setState(4, "");
        automaton.setState(5, "FLUTUANTE");
        automaton.setState(6, "MORTE");

        automaton.setStartState(0);

        automaton.setFinalState(1);
        automaton.setFinalState(2);
        automaton.setFinalState(3);
        automaton.setFinalState(4);
        automaton.setFinalState(5);

        automaton.setTransition(0, 0, " ");
        automaton.setTransition(0, 1, "abcdefghijklmnopqrstuvxwyz");
        automaton.setTransition(0, 2, "0123456789");
        automaton.setTransition(0, 3, "+-*/");
        automaton.setTransition(1, 1, "abcdefghijklmnopqrstuvxwyz0123456789");
        automaton.setTransition(1, 6, "+-*/ ");
        automaton.setTransition(2, 2, "0123456789");
        automaton.setTransition(2, 4, ".");
        automaton.setTransition(4, 5, "0123456789");
        automaton.setTransition(5, 5, "0123456789");
        automaton.setTransition(3, 3, "+-*/");
        automaton.setTransition(2, 6, "+-*/ ");
        automaton.setTransition(5, 6, "+-*/ ");
        automaton.setTransition(3, 6, "abcdefghijklmnopqrstuvxwyz0123456789 ");

        int origin = 0;

        for (int i = 0; i < input.length(); i++) {
            Transition transition = automaton.getTransition(origin, input.charAt(i));
            if (transition != null) {
                State destiny = transition.getDestiny();
                origin = destiny.getId();
            }
        }

        if ("MORTE".equals(automaton.getState(origin).getLabel()) || automaton.getState(origin).getLabel().isBlank())
            return "NÃO RECONHECIDO";

        return automaton.getState(origin).getLabel();
    }
}