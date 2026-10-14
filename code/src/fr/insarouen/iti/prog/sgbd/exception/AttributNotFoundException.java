package fr.insarouen.iti.prog.sgbd.exception;

public class AttributNotFoundException extends SqlException {
    
    public AttributNotFoundException(String message) {
        super(message);
    }
    
    public AttributNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}