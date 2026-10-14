package fr.insarouen.iti.prog.sgbd.exception;


public class TableAlreadyExistsException extends SgbdException {
    
    public TableAlreadyExistsException(String message) {
        super(message);
    }
    
    public TableAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
