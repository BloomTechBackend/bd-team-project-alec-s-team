package alec.financetracker.Exceptions;


public class ExpenditureNotFoundException extends InvalidAttributeException{

    public ExpenditureNotFoundException(){
        super();
    }
    public ExpenditureNotFoundException(String message){
        super(message);
    }
    public ExpenditureNotFoundException(Throwable cause){
        super(cause);
    }
    public ExpenditureNotFoundException(String message, Throwable cause){
        super(cause, message);
    }
}