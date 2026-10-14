package fr.insarouen.iti.prog.sgbd.exception;

public class TableNotFoundException extends SgbdException {
    
    public TableNotFoundException(String message) {
        super(message);
    }
    
    public TableNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
