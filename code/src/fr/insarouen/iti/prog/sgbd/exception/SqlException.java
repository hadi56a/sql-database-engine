package fr.insarouen.iti.prog.sgbd.exception;

public class SqlException extends RuntimeException {
    
    public SqlException(String message) {
        super(message);
    }
    
    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }
}