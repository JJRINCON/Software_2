package exceptions;

public class RepeatedPriorityException extends Exception{

    public RepeatedPriorityException(int priority){
        super("La prioridad " + priority + " ya esta asignada a un proceso");
    }
}
