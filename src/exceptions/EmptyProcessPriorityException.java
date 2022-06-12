package exceptions;

public class EmptyProcessPriorityException extends Exception{

    public EmptyProcessPriorityException(){
        super("El proceso debe tener una prioridad");
    }
}
