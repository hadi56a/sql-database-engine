package fr.insarouen.iti.prog.sgbd.exception;

public class NumberValuesAndAttributsException extends SqlException {
    
    public NumberValuesAndAttributsException(String message) {
        super(message);
    }
    
    public NumberValuesAndAttributsException(String message, Throwable cause) {
        super(message, cause);
    }
}