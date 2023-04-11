package alec.financetracker.Exceptions;



public class InvalidAttributeException extends RuntimeException{

    private static final long serialVersionUID = 0;
    public InvalidAttributeException(){
        super();
    }
    public InvalidAttributeException(String message){
        super(message);
    }
    public InvalidAttributeException(Throwable cause){
        super(cause);
    }
    public InvalidAttributeException(Throwable cause, String message){
        super(message, cause);
    }
}

